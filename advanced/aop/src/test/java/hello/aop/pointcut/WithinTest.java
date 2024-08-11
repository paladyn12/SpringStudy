package hello.aop.pointcut;

import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class WithinTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    public void printMethod() {
        //java.lang.String hello.aop.member.MemberServiceImpl.hello(java.lang.String)
        log.info("helloMethod={}", helloMethod);
    }
    
    @Test
    public void withinExact() {
        pointcut.setExpression("within(hello.aop.member.MemberServiceImpl)");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void withinStar() {
        pointcut.setExpression("within(hello.aop.member.*Service*)");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void withinSubPackage() {
        pointcut.setExpression("within(hello.aop..*)"); //hello.aop 내의 모든 타입
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("타겟의 타입에만 직접 적용, 인터페이스를 선정하면 안됨")
    public void withinSuperTypeFalse() {
        pointcut.setExpression("within(hello.aop.member.MemberService)"); //인터페이스 불가
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    @DisplayName("execution은 타입 기반으로 인터페이스 선정 가능")
    public void executionSuperTypeTrue() {
        pointcut.setExpression("execution(* hello.aop.member.MemberService.*(..))"); //execution은 인터페이스 가능
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }
}