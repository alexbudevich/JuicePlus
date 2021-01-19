package com.itechart.juiceplus.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "cart_item")
@EqualsAndHashCode(exclude = {"count", "cart", "item"})
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cart;

    @OneToOne
    @JoinColumn(name = "cart_item_id", referencedColumnName = "id")
    private Item item;

    @Column(name = "count")
    private Integer count = 0;

}
