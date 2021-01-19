package com.itechart.juiceplus.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class CartDto {

    private Long id;
    private UserDto user;
    private Set<CartItemDto> items;
    private BigDecimal totalCartPrice;
}
