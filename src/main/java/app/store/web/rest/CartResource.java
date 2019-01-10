package app.store.web.rest;

import app.store.service.CartService;
import app.store.service.ProductService;
import app.store.service.UserService;
import app.store.service.dto.CartDto;
import app.store.web.rest.error.BadRequestAlertException;
import app.store.web.rest.error.CartNotFoundException;
import app.store.web.rest.error.CommodityNotFoundException;
import app.store.web.rest.error.UserNotFoundException;
import app.store.web.rest.util.HeaderUtil;
import app.store.web.rest.util.PaginationUtil;
import app.store.web.rest.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CartResource {
    private final Logger log = LoggerFactory.getLogger(CartResource.class);

    private final CartService cartService;
    private final UserService userService;
    private final ProductService productService;

    public CartResource(CartService cartService, UserService userService, ProductService productService) {

        this.cartService = cartService;
        this.userService = userService;
        this.productService = productService;
    }

    @PostMapping("/cart")
    public ResponseEntity<String> createCart(@Valid @RequestBody CartDto cartDto) throws URISyntaxException {
        log.debug("REST request to save Cart : {}", cartDto);
        if (cartDto.getUserId() != null) {
            if (!userService.isExists(cartDto.getUserId())) {
                throw new UserNotFoundException();
            }
            throw new UserNotFoundException();
        } else if (cartDto.getProductIdList() != null) {
            for (String cart : cartDto.getProductIdList()) {
                if (!productService.isExists(cart))
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

    @GetMapping("/cart/{id}")
    public ResponseEntity<CartDto> getCart(@PathVariable String id) {
        log.debug("REST request to get Cart : {}", id);
        Optional<CartDto> cart = cartService.getCart(id);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createAlert("cart.get", cart.get().getId()))
                .body(cart.get());
    }

    @PutMapping("/cart/{id}")
    public ResponseEntity<CartDto> updateCart(@Valid @RequestBody CartDto cartDto, @PathVariable String id) {
        log.debug("REST request to update Cart : {} with id : {}", cartDto, id);
        if (id == null)
            throw new BadRequestAlertException("Card ID is null", "CartDto", "updateCart");
        else if (!cartService.isExists(id))
            throw new CartNotFoundException();
        else if (cartDto == null)
            throw new CartNotFoundException();
        else {
            Optional<CartDto> resultCartDto = cartService.updateCart(cartDto, id);
            return ResponseUtil.wrapOrNotFound(resultCartDto,
                    HeaderUtil.createAlert("cart.updated", id));
        }
    }


    @DeleteMapping("/cart/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable String id) {
        log.debug("REST request to delete Cart: {}", id);
        cartService.deleteCart(id);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("cart.deleted", id)).build();
    }

    @GetMapping("/carts/{id}")
    public ResponseEntity<List<CartDto>> getAllUsers(@Valid @PathVariable String id, Pageable pageable) {
        final Page<CartDto> page = cartService.getAllCart(id, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cart");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
