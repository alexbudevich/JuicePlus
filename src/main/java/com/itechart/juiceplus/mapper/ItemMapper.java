package com.itechart.juiceplus.mapper;

import com.itechart.juiceplus.dto.ItemDto;
import com.itechart.juiceplus.entity.Item;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDto mapToDto(Item dto);

    Item mapToEntity(ItemDto entity);

}
