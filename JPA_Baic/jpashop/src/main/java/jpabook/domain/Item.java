package jpabook.domain;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Item {

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    @ManyToMany(mappedBy = "items")
    private ArrayList<Category> categories = new ArrayList<>();

    private String name;
    private int price;
    private int stockQuantity;
}
