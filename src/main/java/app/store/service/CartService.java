package app.store.service;

import app.store.persistence.domain.Cart;
import app.store.persistence.domain.Product;
import app.store.persistence.domain.enums.CartStatus;
import app.store.persistence.repository.CartRepository;
import app.store.persistence.repository.ProductRepository;
import app.store.service.dto.CartDto;
import app.store.service.mapper.CartMapper;
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
import java.util.concurrent.atomic.AtomicReference;

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
        log.debug("Save Information for Cart: {}", cartDto);
        return Optional.of(cartMapper.toEntity(cartDto))
                .map(cart -> {

                    cart.setTotal(getTotalPrice(cart.getProductIdList()));
                    Cart result = cartRepository.save(cart);
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
                .map(cartMapper::toDto);
    }

    public Optional<CartDto> updateCart(CartDto cartDto, String id) {
        return Optional.of(cartRepository
                .findById(new ObjectId(id)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(cart -> {
                    cart.setStatus(CartStatus.valueOf(cartDto.getStatus()));
                    cart.setTotal(cartDto.getTotal());

                    List<String> cartDtoProductList = cartDto.getProductIdList();
                    List<ObjectId> prepare = new ArrayList<>();
                    for (String cartId : cartDtoProductList) {
                        prepare.add(new ObjectId(cartId));
                    }
                    cart.setProductIdList(prepare);

                    cart.setUserId(new ObjectId(cartDto.getUserId()));
                    cart.setQuantity(cartDto.getQuantity());

                    cart.setTotal(getTotalPrice(cart.getProductIdList()));
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

    

    private Double getTotalPrice(List<ObjectId> products) {
        AtomicReference<Double> total = new AtomicReference<>((double) 0);
        products.forEach(
                productId -> {
                    if (productRepository.existsById(productId)) {
                        Optional<Product> oneById = productRepository.findOneById(productId);
                        oneById.ifPresent(
                                product -> {
                                    total.updateAndGet(v -> (double) (v + product.getPrice().getPrice()));
                                });
                    }
                });
        return total.get();
    }

    public Page<CartDto> getAllCart(String id, Pageable pageable) {
        Page<Cart> allByUserIdAndStatus = cartRepository.findAllByUserIdAndStatus(pageable, new ObjectId(id), CartStatus.COMPLETE);
        List<CartDto> cartDtos = cartMapper.toDto(allByUserIdAndStatus.getContent());
        return new PageImpl<CartDto>(cartDtos);
    }


}
