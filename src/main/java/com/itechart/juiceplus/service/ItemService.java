package com.itechart.juiceplus.service;

import com.itechart.juiceplus.entity.Item;
import com.itechart.juiceplus.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class ItemService {

    @Autowired
    private ItemRepository productRepository;

    public Item create(Item item) {
        return productRepository.save(item);
    }

    public Item get(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("User id:%s not found.", id)));
    }
}
