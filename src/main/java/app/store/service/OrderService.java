package app.store.service;

import app.store.persistence.domain.Invoice;
import app.store.persistence.domain.Order;
import app.store.persistence.domain.Shipping;
import app.store.persistence.domain.enums.CartStatus;
import app.store.persistence.domain.enums.OrderStatus;
import app.store.persistence.repository.CartRepository;
import app.store.persistence.repository.OrderRepository;
import app.store.service.dto.OrderDto;
import app.store.service.mapper.OrderMapper;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class OrderService {
    private final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CartRepository cartRepository;
    private final InvoiceService invoiceService;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, CartRepository cartRepository, InvoiceService invoiceService) {

        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.cartRepository = cartRepository;
        this.invoiceService = invoiceService;
    }

    public Optional<String> createOrder(OrderDto orderDto) {
        return Optional.of(orderMapper.toEntity(orderDto))
                .map(order -> {

                    order.setStatus(OrderStatus.CREATED);
                    order.setInvoiceId(null);
                    order.setPaymentId(null);
                    order.setCashOnDelivery(null);

                    Order save = orderRepository.save(order);
                    log.debug("Save Information for Order: {}", save);
                    return save.getId().toString();
                });
    }

    @Async
    public void cartStatusChange(ObjectId userId) {
        cartRepository.findOneByUserIdAndStatus(userId, CartStatus.COMPLETE)
                .ifPresent(cart -> {
                    cart.setStatus(CartStatus.FINISH);
                    cartRepository.save(cart);
                });
    }


    public Optional<Order> getOrderById(String orderId) {
        log.debug("get Order by id: {}", orderId);
        return orderRepository.findById(new ObjectId(orderId));
    }


    public Optional<OrderDto> updateOrder(OrderDto orderDto, String id) {
        return Optional.of(orderRepository.findById(new ObjectId(id)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(order -> {


                    order.setCheckout(Instant.now());
                    if (order.getShipping() == null) {

                    }


                    if (orderDto.getInvoiceId() != null) {
                        order.setInvoiceId(new ObjectId(orderDto.getInvoiceId()));
                        //todo how generate Invoice and factor
//                        Optional<Invoice> invoice = invoiceService.updateInvoice(order.getUserId(), order.getPaymentId(), order.getCheckout());

                    } else {
                        //todo create invoice
                        Optional<Invoice> invoice = invoiceService.createInvoice(order.getUserId(), order.getId());
                        cartRepository.findOneByUserIdAndStatus(order.getUserId(), CartStatus.ACTIVE)
                                .ifPresent(cart -> {
                                    cart.setStatus(CartStatus.COMPLETE);
                                    cartRepository.save(cart);
                                });


                    }

                    if (orderDto.getPaymentId() != null) {
                        order.setPaymentId(new ObjectId(orderDto.getPaymentId()));
                        order.setCheckout(Instant.now());
                        order.setStatus(OrderStatus.ISSUED);

                        cartStatusChange(order.getUserId());
                    } else if (orderDto.getCashOnDelivery()) {
                        cartStatusChange(order.getUserId());
                    }

                    Order save = orderRepository.save(order);
                    log.debug("Changed Information for Order: {}", save);
                    return save;
                }).map(orderMapper::toDto);
    }

    public Optional<OrderDto> findById(String id) {
        log.debug("get Order by id: {}", id);
        return Optional.of(orderRepository.findById(new ObjectId(id)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(orderMapper::toDto);
    }

    public Optional<OrderDto> loadByUser(String userId) {
        log.debug("get Order by userId: {}", userId);
        return orderRepository.findByUserIdAndStatus(new ObjectId(userId), OrderStatus.CREATED).map(orderMapper::toDto);
    }

    @Async
    public void doneBySystem(String userId) {
        orderRepository.findByUserIdAndStatus(new ObjectId(userId), OrderStatus.DELIVERED)
                .ifPresent(order -> {
                    order.setStatus(OrderStatus.DONE);
                    orderRepository.save(order);
                });
    }

    public Page<OrderDto> loadAllByUser(String userId, Pageable pageable) {
        log.debug("get Order by userId: {} and 'DONE' OrderStatus", userId);
        return orderRepository.findAllByUserIdAndStatus(new ObjectId(userId), OrderStatus.DONE, pageable)
                .map(orderMapper::toDto);
    }
}
