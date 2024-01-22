package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig { // 실제 동작에 필요한 구현 객체들을 생성, 생성자를 통해 주입(생성자 주입)

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()

    //memberService, orderService, memberRepository를 호출 했을 경우

    //call AppConfig.memberService
    //call AppConfig.MemberRepository
    //call AppConfig.MemberRepository
    //call AppConfig.orderService
    //call AppConfig.MemberRepository >> X

    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.orderService >> O

    //스프링 빈이 CGLIB이라는 바이트코드 조작 라이브러리를 사용하여 AppConfig를 상속받은 클래스 생성
    //@Bean이 붙은 매서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고
    //스프링 빈이 없으면 스프링 빈으로 등록하는 방식으로 싱글턴을 보장

    //@Configuration을 적용하지 않으면?
    //CGLIB 기술이 적용되지 않아 싱글턴을 보장하는 로직이 적용되지 않음, 그냥 적용하자

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    } // MemberServiceImpl 입장에서 의존관계를 외부에서 주입하는 듯 보임 >> 의존관계 주입

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());
    }
}
