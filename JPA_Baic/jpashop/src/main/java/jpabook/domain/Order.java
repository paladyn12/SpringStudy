package jpabook.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id @GeneratedValue //입력 안하면 default는 AUTO
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) //order 생성시 orderItems 자동 저장
    private ArrayList<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //order 생성시 delivery 자동 저장
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;


    private Date orderDate;
    private OrderStatus status;

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
}
