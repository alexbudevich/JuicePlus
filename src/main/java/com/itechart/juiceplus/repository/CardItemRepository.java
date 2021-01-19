package com.itechart.juiceplus.repository;

import com.itechart.juiceplus.entity.Cart;
import com.itechart.juiceplus.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardItemRepository extends JpaRepository<CartItem, Long> {
}
