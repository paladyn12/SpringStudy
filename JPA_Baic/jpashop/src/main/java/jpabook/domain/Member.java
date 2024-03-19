package jpabook.domain;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @OneToMany(mappedBy = "member")
    private ArrayList<Order> orders = new ArrayList<>();

    private String name;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Embedded
    private Address address;

    public Member(){}

}
