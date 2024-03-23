package jpabook.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //입력 안하면 default는 AUTO
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private int orderAmount;

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) //order 생성시 orderItems 자동 저장
    private ArrayList<OrderItem> orderItems = new ArrayList<>();




    public Long getId() {
        return id;
    }



    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //order 생성시 delivery 자동 저장
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;


    private Date orderDate;
    private OrderStatus status;


}
