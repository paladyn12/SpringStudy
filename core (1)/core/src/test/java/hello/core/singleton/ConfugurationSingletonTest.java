package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfugurationSingletonTest {

    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl ms = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl os = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean(MemberRepository.class);


        //Spring Container는 두 서비스의 memoryRepository를 자동적으로 unique하게 만들어줌(Singleton Container)
        Assertions.assertThat(ms.getMemberRepository()).isSameAs(os.getMemberRepository());
        Assertions.assertThat(ms.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
    }
}
