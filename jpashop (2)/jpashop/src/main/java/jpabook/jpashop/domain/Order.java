package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id") //DB 테이블 이름
    private Long id;

    @ManyToOne //Order와 Member는 다대일 관계, Order가 다 이므로 @ManyToOne 애노테이션
    @JoinColumn(name = "member_id") //이 FK의 이름이 member_id
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne //한 주문당 한 배송정보, FK는 아무 곳이나 둬도 되지만 주로 access를 자주 하는 곳(주문)에 놓는게 좋음
    @JoinColumn(name = "delivery_id") //연관관계의 주인은 Order, FK를 가짐
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문 상태 [ORDER, CANCEL]
}
