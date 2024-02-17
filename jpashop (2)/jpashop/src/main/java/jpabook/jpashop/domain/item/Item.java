package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
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

    //==비즈니스 로직==//
    //데이터를 가진 쪽에서 로직을 갖는 것이 객체지향적임

    //stock 증가
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    //stock 감소
    public void removeStock(int quantity){
        int resStock = this.stockQuantity - quantity;
        if(resStock < 0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = resStock;
    }
}
