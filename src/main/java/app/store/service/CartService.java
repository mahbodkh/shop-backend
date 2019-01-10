package app.store.service;

import app.store.persistence.domain.Cart;
import app.store.persistence.domain.enums.ProductStatus;
import app.store.persistence.repository.CartRepository;
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

@Service
public class CartService {
    private final Logger log = LoggerFactory.getLogger(CartService.class);


    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    public CartService(CartRepository cartRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
    }

    public Optional<CartDto> saveCart(CartDto cartDto) {
        Cart cart = cartRepository.save(cartMapper.toEntity(cartDto));
        CartDto resultCartDto = cartMapper.toDto(cart);
        return Optional.of(resultCartDto);
    }

    public Boolean isExists(String cartId) {
        return cartRepository.existsById(new ObjectId(cartId));
    }

    public Optional<CartDto> getCart(String id) {
        Optional<Cart> cart = cartRepository.findById(new ObjectId(id));
        Cart result = cart.get();
        return Optional.of(cartMapper.toDto(result));
    }

    public Optional<CartDto> updateCart(CartDto cartDto, String id) {
        return Optional.of(cartRepository
                .findById(new ObjectId(id)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(result -> {
                    result.setId(new ObjectId(id));
                    result.setStatus(ProductStatus.valueOf(cartDto.getStatus()));
                    result.setTotal(cartDto.getTotal());

                    List<String> cartDtoProductList = cartDto.getProductIdList();
                    List<ObjectId> prepare = new ArrayList<>();
                    for (String cart : cartDtoProductList) {
                        prepare.add(new ObjectId(cart));
                    }
                    result.setProductIdList(prepare);

                    result.setUserId(new ObjectId(cartDto.getUserId()));
                    result.setQuantity(cartDto.getQuantity());

                    cartRepository.save(result);
                    CartDto resultCartDto = cartMapper.toDto(result);
                    log.debug("Changed Information for Cart: {}", resultCartDto);
                    return resultCartDto;
                });
    }

    public void deleteCart(String id) {
        cartRepository.deleteById(new ObjectId(id));
    }

    public Page<CartDto> getAllCart(String id, Pageable pageable) {
        Page<Cart> allByUserIdAndStatus = cartRepository.findAllByUserIdAndStatus(pageable, new ObjectId(id), ProductStatus.COMPLETE);
        List<CartDto> cartDtos = cartMapper.toDto(allByUserIdAndStatus.getContent());
        return new PageImpl<CartDto>(cartDtos);
    }
}
