package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
}
