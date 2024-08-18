package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
public class ProxyCastingTest {

    @Test
    public void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false); //JDK 동적 프록시

        //프록시를 인터페이스로 타입 캐스팅 성공 : JDK 동적 프록시는 interface를 구현해 만들기 때문에 캐스팅이 당연히 가능
        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        //프록시를 구체 클래스로 타입 캐스팅 실패 : JDK 동적 프록시는 구체 클래스에 대한 메타 정보가 없음
        Assertions.assertThrows(ClassCastException.class, () -> {
            MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
        });
    }
    
    @Test
    public void cglibProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false); //CGLIB 프록시

        //성공, 상속받은 구체 클래스의 interface는 당연히 캐스팅 가능
        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        //성공, CGLIB은 구체 클래스를 상속받아 생성되므로 가능
        MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;


    }
}
