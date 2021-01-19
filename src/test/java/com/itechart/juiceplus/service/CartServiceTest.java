package com.itechart.juiceplus.service;

import com.itechart.juiceplus.entity.Cart;
import com.itechart.juiceplus.entity.CartItem;
import com.itechart.juiceplus.entity.Item;
import com.itechart.juiceplus.repository.CardItemRepository;
import com.itechart.juiceplus.repository.CartRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@MockBean({
        UserService.class,
        CardItemRepository.class
})
public class CartServiceTest {
    long userId = 1L;

    @MockBean
    CartRepository cartRepository;

    @SpyBean
    CartService cartService;

    @Before
    public void setUp() {
        Item item = new Item();
        item.setPrice(new BigDecimal(1));
        CartItem cartItem = new CartItem();
        cartItem.setItem(item);
        cartItem.setCount(3);
        Cart cart = new Cart();
        Set<CartItem> cartItems = new HashSet<>();
        cartItems.add(cartItem);
        cart.setItems(cartItems);

        when(cartRepository.getByUserId(userId))
                .thenReturn(Optional.of(cart));
    }

    @Test
    public void testGetCart_totalCartPrice() {
        Cart cart = cartService.get(userId);
        assertNotNull(cart);
        assertEquals(new BigDecimal(3), cart.getTotalCartPrice());
    }
}