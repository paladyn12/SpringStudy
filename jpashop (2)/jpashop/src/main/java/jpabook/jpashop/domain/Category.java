package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany //다대다 관계에선 서로를 연결하는 테이블이 필요, 일대다 다대일로 풀어냄
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id") //한 부모로부터 여러 자식이 나오므로
    private Category parent;

    @OneToMany //한 부모로부터 여러 자식이 나오므로
    private List<Category> child = new ArrayList<>();

    //==연관관계 메서드==//
    public void addCategory(Category child){
        this.child.add(child);
        child.setParent(this);
    }
}
