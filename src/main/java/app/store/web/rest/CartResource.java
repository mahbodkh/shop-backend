package app.store.web.rest;

import app.store.service.CartService;
import app.store.service.CommodityService;
import app.store.service.UserService;
import app.store.service.dto.CartDto;
import app.store.web.rest.error.CartNotFoundException;
import app.store.web.rest.error.CommodityNotFoundException;
import app.store.web.rest.error.UserNotFoundException;
import app.store.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CartResource {
    private final Logger log = LoggerFactory.getLogger(CartResource.class);

    private final CartService cartService;
    private final UserService userService;
    private final CommodityService commodityService;

    public CartResource(CartService cartService, UserService userService, CommodityService commodityService) {

        this.cartService = cartService;
        this.userService = userService;
        this.commodityService = commodityService;
    }

    @PostMapping("/cart")
    public ResponseEntity<String> createCart(@Valid @RequestBody CartDto cartDto) throws URISyntaxException {
        log.debug("REST request to save Cart : {}", cartDto);
        if (cartDto.getUserId() != null) {
            if (!userService.isExists(cartDto.getUserId())) {
                throw new UserNotFoundException();
            }
            throw new UserNotFoundException();
        } else if (cartDto.getProductId() != null) {
            if (!commodityService.isExists(cartDto.getProductId())) {
                throw new CommodityNotFoundException();
            }
            throw new CommodityNotFoundException();
        } else if (!cartService.isExists(cartDto.getId())) {
            throw new CartNotFoundException();
        } else {
            Optional<CartDto> resultCartDto = cartService.saveCart(cartDto);
            return ResponseEntity.created(new URI("/api/cart/" + resultCartDto.get().getId()))
                    .headers(HeaderUtil.createAlert("cart.created", resultCartDto.get().getId()))
                    .body(resultCartDto.get().getId());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getCart(@PathVariable String id) {
        log.debug("REST request to get Cart : {}", id);
        Optional<CartDto> cart = cartService.getCart(id);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createAlert("cart.get", cart.get().getId()))
                .body(cart.get());
    }




}
