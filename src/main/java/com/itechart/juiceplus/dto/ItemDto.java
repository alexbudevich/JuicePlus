package com.itechart.juiceplus.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemDto {

    private Long id;

    private String name;

    private BigDecimal price;

}
