package app.store.service;

import app.store.persistence.domain.Order;
import app.store.persistence.domain.enums.OrderStatus;
import app.store.persistence.repository.OrderRepository;
import app.store.service.dto.OrderDto;
import app.store.service.mapper.OrderMapper;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class OrderService {
    private final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CartService cartService;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, CartService cartService) {

        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.cartService = cartService;
    }

    public Optional<String> saveOrder(OrderDto orderDto) {
        return Optional.of(orderMapper.toEntity(orderDto))
                .map(order -> {

                    order.setStatus(OrderStatus.ISSUED);
                    Order save = orderRepository.save(order);
                    log.debug("Save Information for Order: {}", save);
                    return save.getId().toString();
                });
    }


    public Optional<Order> getOrderById(String orderId) {
        return orderRepository.findById(new ObjectId(orderId));
    }


    public Optional<OrderDto> updateOrder(OrderDto orderDto, String id) {
        return Optional.of(orderRepository.findById(new ObjectId(id)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(order -> {
                    order.setId(new ObjectId(orderDto.getId()));
                    order.setCheckout(orderDto.getCheckout());
                    if (orderDto.getInvoiceId() != null) {
                        order.setInvoiceId(new ObjectId(orderDto.getInvoiceId()));
                        //todo how generate Invoice and factor

                    }

                    if (orderDto.getPaymentId() != null) {
                        order.setPaymentId(new ObjectId(orderDto.getPaymentId()));
                        order.setCheckout(Instant.now());
                        order.setStatus(OrderStatus.ISSUED);
                    }



                    Order save = orderRepository.save(order);
                    log.debug("Changed Information for Order: {}", save);
                    return save;
                }).map(orderMapper::toDto);
    }

    public Optional<OrderDto> findById(String id) {
        return Optional.of(orderRepository.findById(new ObjectId(id)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(orderMapper::toDto);
    }
}
