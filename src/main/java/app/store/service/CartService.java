package app.store.service;

import app.store.persistence.domain.Cart;
import app.store.persistence.domain.Product;
import app.store.persistence.domain.ProductCart;
import app.store.persistence.domain.enums.CartStatus;
import app.store.persistence.repository.CartRepository;
import app.store.persistence.repository.ProductRepository;
import app.store.service.dto.CartDto;
import app.store.service.dto.ProductCartDto;
import app.store.service.mapper.CartMapper;
import app.store.web.rest.error.ProductNotFoundException;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final Logger log = LoggerFactory.getLogger(CartService.class);


    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;


    public CartService(CartRepository cartRepository, ProductRepository productRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartMapper = cartMapper;
    }

    //todo it must test
    public Optional<String> saveCart(CartDto cartDto) {
        List<ProductCart> productCarts = new ArrayList<>();
        if (cartDto.getProductCarts() != null)
            cartDto.getProductCarts().forEach(pc -> {
                ProductCart productCart = new ProductCart();
                productCart.setProductId(new ObjectId(pc.getProductId()));
                productCart.setQuantity(pc.getQuantity());
                productCart.setProperty(pc.getProperty());
                productCarts.add(productCart);
            });
        return Optional.of(cartMapper.toEntity(cartDto))
                .map(cart -> {
                    cart.setProductCarts(productCarts);
//                    cart.setTotal(getTotalPrice(productCarts));
                    Cart result = cartRepository.save(cart);
                    log.debug("Save Information for Cart: {}", cartDto);
                    return result.getId().toString();
                });
    }

    public Boolean isExists(String cartId) {
        log.debug("Check Information for Cart id: {}", cartId);
        return cartRepository.existsById(new ObjectId(cartId));
    }

    public Optional<CartDto> getCart(String id) {
        return Optional.of(cartRepository.findById(new ObjectId(id)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(cart -> {
                    cart.getProductCarts().forEach(pc -> {
                        Optional<Product> product = productRepository.findOneById(pc.getProductId());
                        if (!product.isPresent()) {
                            throw new ProductNotFoundException();
                        }
                        //todo check price of card with the product ::>
                    });
                    cart.setTotal(getTotalPrice(cart.getProductCarts()));
                    return cart;
                }).map(cartMapper::toDto);
    }

    public Optional<CartDto> updateCart(CartDto cartDto, String id) {
        return Optional.of(cartRepository
                .findById(new ObjectId(id)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(cart -> {
                    cart.setStatus(Enum.valueOf(CartStatus.class, cartDto.getStatus().name()));

                    if (cartDto.getProductCarts() != null) {
                        List<ProductCart> productCarts = new ArrayList<>();
                        List<ProductCartDto> productList = cartDto.getProductCarts();
                        productList.forEach(pc -> {
                            ProductCart productCart = new ProductCart();
                            productCart.setProductId(new ObjectId(pc.getProductId()));
                            productCart.setProperty(pc.getProperty());
                            productCart.setQuantity(pc.getQuantity());
                            productCarts.add(productCart);
                        });
                        cart.setProductCarts(productCarts);
                        cart.setTotal(getTotalPrice(productCarts));
                    }

                    cart.setUserId(new ObjectId(cartDto.getUserId()));
                    cart.setQuantity(cartDto.getQuantity());
                    Cart result = cartRepository.save(cart);
                    log.debug("Changed Information for Cart: {}", cart);
                    return result;
                }).map(cartMapper::toDto);
    }

    public void deleteCart(String id) {
        cartRepository.findById(new ObjectId(id))
                .ifPresent(result -> {
                    cartRepository.delete(result);
                    log.debug("Deleted Keyword: {}", result);
                });
    }


    private Double getTotalPrice(List<ProductCart> products) {
        double[] total = {0d};
        for (ProductCart productId : products) {
            productRepository.findOneById(productId.getProductId())
                    .ifPresent(product -> {
                        total[0] += productId.getQuantity() * product.getPrice().getPrice();
                    });
        }
        return total[0];
    }

    public Page<CartDto> getAllCart(String userId, Pageable pageable) {
        Page<Cart> allByUserIdAndStatus = cartRepository.findAllByUserIdAndStatus(pageable, new ObjectId(userId), CartStatus.COMPLETE);
        List<CartDto> cartDtos = cartMapper.toDto(allByUserIdAndStatus.getContent());
        return new PageImpl<CartDto>(cartDtos);
    }


    public Page<CartDto> getAllCartByStatus(String userId, CartStatus status, Pageable pageable) {
        return cartRepository.findAllByUserIdAndStatus(pageable, new ObjectId(userId), status)
                .map(cartMapper::toDto);
    }

}
