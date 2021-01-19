package com.itechart.juiceplus.service;

import com.itechart.juiceplus.entity.Cart;
import com.itechart.juiceplus.entity.CartItem;
import com.itechart.juiceplus.entity.Item;
import com.itechart.juiceplus.entity.User;
import com.itechart.juiceplus.repository.CardItemRepository;
import com.itechart.juiceplus.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CardItemRepository cardItemRepository;

    public Cart get(Long userId) {
        Cart cart = cartRepository.getByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Cart for user id:%s not found.", userId)));
        cart.setTotalCartPrice(getTotalPrice(cart));
        return cart;
    }

    public Cart create(Long userId) {
        User user = userService.get(userId);
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    public Cart addItem(Long userId, Item entity) {
        Cart cart = get(userId);
        CartItem cartItem = cart.getItems().stream()
                .filter(e -> e.getItem().getId().equals(entity.getId()))
                .findFirst()
                .orElse(getDefaultCartItem(entity, cart));
        cartItem.setCount(cartItem.getCount() + 1);
        cart.getItems().add(cartItem);
        cart = cartRepository.save(cart);
        cart.setTotalCartPrice(getTotalPrice(cart));
        return cart;
    }

    public Cart removeItem(Long userId, Item entity) {
        Cart cart = get(userId);
        CartItem cartItem = cart.getItems().stream()
                .filter(e -> e.getItem().getId().equals(entity.getId()))
                .findFirst()
                .orElse(new CartItem());
        cartItem.setCount(cartItem.getCount() - 1);
        if (cartItem.getCount() > 0) {
            cart = cartRepository.save(cart);
        } else {
            cart.getItems().remove(cartItem);
            cartRepository.save(cart);
            cardItemRepository.delete(cartItem);
        }
        cart.setTotalCartPrice(getTotalPrice(cart));
        return cart;
    }

    private CartItem getDefaultCartItem(Item item, Cart cart) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setItem(item);
        return cartItem;
    }

    private BigDecimal getTotalPrice(Cart cart) {
        return cart.getItems().stream()
                .map(e -> e.getItem().getPrice().multiply(new BigDecimal(e.getCount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void checkout(Long userId) {
        cartRepository.getByUserId(userId)
        .ifPresent(cart -> cartRepository.delete(cart));
        log.info("Cart for user id:{} was checked out", userId);
    }
}
