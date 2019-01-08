package app.store.service;

import app.store.persistence.domain.Cart;
import app.store.persistence.repository.CartRepository;
import app.store.service.dto.CartDto;
import app.store.service.mapper.CartMapper;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

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
        Optional<Cart> cart = cartRepository.findById(new ObjectId(id));
        Cart result = cart.get();
        return Optional.of(cartMapper.toDto(result));
    }

    public void deleteCart(String id) {
        cartRepository.deleteById(new ObjectId(id));
    }

    public Page<CartDto> getAllCart(Pageable pageable) {

        return null;
    }
}
