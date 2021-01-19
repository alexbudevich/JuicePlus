package com.itechart.juiceplus.controller;

import com.itechart.juiceplus.dto.CartDto;
import com.itechart.juiceplus.dto.UserDto;
import com.itechart.juiceplus.entity.User;
import com.itechart.juiceplus.mapper.UserMapper;
import com.itechart.juiceplus.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/{id}")
    public UserDto get(@PathVariable("id") Long id){
        log.debug("Get user: {}", id);
        return userMapper.mapToDto(userService.get(id));
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto userDto) {
        User user = userMapper.mapToEntity(userDto);
        return userMapper.mapToDto(userService.create(user));
    }


}
