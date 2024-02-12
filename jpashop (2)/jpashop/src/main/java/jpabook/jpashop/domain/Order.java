package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id") //DB 테이블 이름
    private Long id;

    @ManyToOne(fetch = LAZY) //Order와 Member는 다대일 관계, Order가 다 이므로 @ManyToOne 애노테이션
    @JoinColumn(name = "member_id") //이 FK의 이름이 member_id
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();
    //cascade를 설정하지 않으면 모든 OrderItem을 persist 한 후 order를 persist 해야 함
    //cascade를 설정하면 order만 persist 해도 됨

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL) //한 주문당 한 배송정보, FK는 아무 곳이나 둬도 되지만 주로 access를 자주 하는 곳(주문)에 놓는게 좋음
    @JoinColumn(name = "delivery_id") //연관관계의 주인은 Order, FK를 가짐
    private Delivery delivery;
    //cascade를 설정하면 order 저장할 때 delivery도 persist됨

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문 상태 [ORDER, CANCEL]

    //==연관관계 메서드==//
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }
    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
