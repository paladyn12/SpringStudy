package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //SINGLE_TABLE : 한 테이블에 넣음
@DiscriminatorColumn(name = "dtype") //상속 종류에 따라 구분, 상속받는 클래스에서 B, A, M으로 지정함
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items") //Cateory 클래스의 items에 중간 테이블 설정을 해놓음
    private List<Category> categories = new ArrayList<>();
}
