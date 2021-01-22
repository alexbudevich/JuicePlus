package com.itechart.juiceplus.controller;


import com.itechart.juiceplus.dto.ItemDto;
import com.itechart.juiceplus.entity.Item;
import com.itechart.juiceplus.mapper.ItemMapper;
import com.itechart.juiceplus.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
@Slf4j
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemMapper itemMapper;

    @GetMapping("/{id}")
    public ItemDto get(@PathVariable("id") Long id){
        log.debug("Get item: {}", id);
        return itemMapper.mapToDto(itemService.get(id));
    }

    @PostMapping
    public ItemDto create(@RequestBody ItemDto itemDto) {
        log.debug("Create item: {}", itemDto);
        Item product = itemMapper.mapToEntity(itemDto);
        return itemMapper.mapToDto(itemService.create(product));
    }

}
