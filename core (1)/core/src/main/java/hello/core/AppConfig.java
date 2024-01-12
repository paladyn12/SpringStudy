package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig { // 실제 동작에 필요한 구현 객체들을 생성, 생성자를 통해 주입(생성자 주입)

    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    } // MemberServiceImpl 입장에서 의존관계를 외부에서 주입하는 듯 보임 >> 의존관계 주입

    public OrderService orderService(){
        return new OrderServiceImpl(
                new MemoryMemberRepository(),
                new FixDiscountPolicy());
    }
}
