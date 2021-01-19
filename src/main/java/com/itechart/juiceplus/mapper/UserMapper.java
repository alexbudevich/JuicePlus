package com.itechart.juiceplus.mapper;

import com.itechart.juiceplus.dto.UserDto;
import com.itechart.juiceplus.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto mapToDto(User dto);

    User mapToEntity(UserDto entity);

}
