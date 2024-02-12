package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

    @Embedded //내장타입
    private Address address;

    @Enumerated(EnumType.STRING) //EnumType.ORDINAL이라 지정하면 0, 1 등의 수로 구분, 유지보수시 큰 문제 생김
    private DeliveryStatus status; //READY, COMP
}
