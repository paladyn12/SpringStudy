package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable //어딘가에 내장될 수 있음
@Getter //값이 변경되면 안됨, 생성 시에 정해지고 끝까지 사용
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address(){} //JPA가 리플렉션, 프록시 기술을 써야 하는데 이 때 기본 생성자가 필요

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
