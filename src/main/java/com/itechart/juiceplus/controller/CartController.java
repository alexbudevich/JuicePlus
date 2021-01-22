package com.itechart.juiceplus.controller;

import com.itechart.juiceplus.dto.CartDto;
import com.itechart.juiceplus.dto.ItemDto;
import com.itechart.juiceplus.entity.Cart;
import com.itechart.juiceplus.mapper.CartMapper;
import com.itechart.juiceplus.mapper.ItemMapper;
import com.itechart.juiceplus.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@Slf4j
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ItemMapper itemMapper;

    @GetMapping
    public CartDto get(@RequestHeader("user-id") Long userId){
        log.debug("Get cart for user id: {}", userId);
        return cartMapper.mapToDto(cartService.get(userId));
    }

    @PostMapping
    public CartDto create(@RequestHeader("user-id") Long userId) {
        log.debug("Create cart for user id: {}", userId);
        return cartMapper.mapToDto(cartService.create(userId));
    }

    @PostMapping("addItem")
    public CartDto addItem(@RequestHeader("user-id") Long userId, @RequestBody ItemDto itemDto) {
        log.debug("Add item to cart for user id: {} for user id: {}", userId, itemDto.getId());
        Cart updatedCart = cartService.addItem(userId, itemMapper.mapToEntity(itemDto));
        return cartMapper.mapToDto(updatedCart);
    }

    @PostMapping("removeItem")
    public CartDto removeItem(@RequestHeader("user-id") Long userId, @RequestBody ItemDto itemDto) {
        log.debug("Remove item from cart for user id: {} for user id: {}", userId, itemDto.getId());
        Cart updatedCart = cartService.removeItem(userId, itemMapper.mapToEntity(itemDto));
        return cartMapper.mapToDto(updatedCart);
    }

    @PostMapping("checkout")
    public void checkout(@RequestHeader("user-id") Long userId) {
        log.debug("Checkout cart for user id: {}", userId);
        cartService.checkout(userId);
    }
}
