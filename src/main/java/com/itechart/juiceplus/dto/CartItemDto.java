package com.itechart.juiceplus.dto;

import lombok.Data;

@Data
public class CartItemDto {
    private ItemDto item;
    private Integer count;
}
