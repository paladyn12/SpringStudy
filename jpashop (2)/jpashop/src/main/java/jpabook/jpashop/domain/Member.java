package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded //내장타입, Adress 클래스는 @Embeddable 애노테이션이 붙음, 둘 중 하나만 써도 됨
    private Address address;

    //Order 테이블의 member 필드에 의해 매핑됨, FK는 Order 쪽에 있음
    @OneToMany(mappedBy = "member") //Order와 Member는 다대일 관계, Member가 일이므로 @OneToMany 애노테이션
    private List<Order> orders = new ArrayList<Order>();
}
