package com.itechart.juiceplus.mapper;

import com.itechart.juiceplus.dto.CartDto;
import com.itechart.juiceplus.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartDto mapToDto(Cart dto);

    Cart mapToEntity(CartDto entity);
}
