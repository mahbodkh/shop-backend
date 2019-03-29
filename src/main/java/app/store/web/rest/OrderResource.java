package app.store.web.rest;

import app.store.service.OrderService;
import app.store.service.ProductService;
import app.store.service.UserService;
import app.store.service.dto.OrderDto;
import app.store.web.rest.error.OrderAlreadyUsedException;
import app.store.web.rest.error.OrderInvalidException;
import app.store.web.rest.error.OrderNotFoundException;
import app.store.web.rest.error.UserNotFoundException;
import app.store.web.rest.util.HeaderUtil;
import app.store.web.rest.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class OrderResource {
    private final Logger log = LoggerFactory.getLogger(OrderResource.class);

    private final OrderService orderService;
    private final ProductService productService;
    private final UserService userService;

    public OrderResource(OrderService orderService, ProductService productService, UserService userService) {

        this.orderService = orderService;
        this.productService = productService;
        this.userService = userService;
    }

    @PostMapping(value = "/order")
    public ResponseEntity<String> createOrder(OrderDto orderDto) throws URISyntaxException {
        log.debug("REST request to save Order : {}", orderDto);
        if (orderDto == null) {
            throw new OrderInvalidException();
        } else if (orderService.getOrderById(orderDto.getId()).isPresent()) {
            throw new OrderAlreadyUsedException();
        } else if (!userService.getUser(orderDto.getUserId()).isPresent()) {
            throw new UserNotFoundException();
        }

        String orderId = orderService.saveOrder(orderDto).get();


        return ResponseEntity.created(new URI("/api/order/" + orderId))
                .headers(HeaderUtil.createAlert("order.created", orderId))
                .body(orderId);
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<OrderDto> updateProduct(@Valid @RequestBody OrderDto orderDto, @Valid @PathVariable String id) {
        log.debug("REST request to update OrderDto : {} with id : {}", orderDto, id);
        if (!orderService.findById(id).isPresent()) {
            throw new OrderNotFoundException();
        }
        Optional<OrderDto> result = orderService.updateOrder(orderDto, id);
        return ResponseUtil.wrapOrNotFound(result,
                HeaderUtil.createAlert("order.updated", result.get().getId()));
    }
}
