package jpabook.domain;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;

    @ManyToMany(mappedBy = "items")
    private ArrayList<Category> categories = new ArrayList<>();

    private String name;
    private int price;
    private int stockQuantity;

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }


}
