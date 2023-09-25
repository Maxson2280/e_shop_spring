package com.vyatsu.task14.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "view")
    private int view;

    @Column(name = "sold")
    private int sold;
    public Product() {}

    @Override
    public String toString() { return title + '-' + "цена: " + price; }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public int getSold() {
        return sold;
    }
}
