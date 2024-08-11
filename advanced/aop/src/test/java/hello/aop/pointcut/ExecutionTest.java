package hello.aop.pointcut;

import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ExecutionTest {

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
    public void exactMatch() {
        pointcut.setExpression("execution(public String hello.aop.member.MemberServiceImpl.hello(String))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void allMatch() {
        pointcut.setExpression("execution(* *(..))");   //반환 타입 : All. 메서드 이름 : All. 파라미터 : All. 접근제어자, 패키지 이름, 예외 생략
                                                        //가장 많이 생략한 포인트컷
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void nameMatch() {
        pointcut.setExpression("execution(* hello(..))"); // 메서드 이름 : hello
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void nameMatchStar1() {
        pointcut.setExpression("execution(* hel*(..))"); // 메서드 이름 : hel로 시작
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void nameMatchStar2() {
        pointcut.setExpression("execution(* *el*(..))"); // 메서드 이름 : 중간에 el이 들어감
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void nameMatchFalse() {
        pointcut.setExpression("execution(* hi(..))"); // 메서드 이름 : hi (X)
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    public void packageMatch1() {
        pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.hello(..))"); // 패키지 이름 : hello.aop.member.MemberServiceImpl
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void packageMatch2() {
        pointcut.setExpression("execution(* hello.aop.member.*.*(..))"); // 패키지의 클래스와 메서드 이름 *
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void packageMatchFalse() {
        pointcut.setExpression("execution(* hello.aop.*.*(..))"); // 패키지 hello.aop.* (X), hello.aop.member 까지 맞아야함
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    public void packageMatchSubPackage1() {
        pointcut.setExpression("execution(* hello.aop.member..hello(..))"); // hello.aop.member와 하위 모든 패키지의 hello
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void packageMatchSubPackage2() {
        pointcut.setExpression("execution(* hello.aop..*.*(..))"); // hello.aop와 하위 모든 패키지의 모든 메서드
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void typeExactMatch() {
        pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.*(..))"); // 타입이 정확히 MemberServiceImpl
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void typeMatchSuperType() {
        pointcut.setExpression("execution(* hello.aop.member.MemberService.*(..))"); // 부모 타입인 MemberService로 매칭 가능
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void typeMatchNoSuperTypeMethodFalse() throws NoSuchMethodException {
        pointcut.setExpression("execution(* hello.aop.member.MemberService.*(..))"); // 부모 타입 매칭시 자식 고유 메서드 매칭 불가
        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
        assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    public void typeMatchInternal() throws NoSuchMethodException {
        pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.*(..))"); // 자식 타입 매칭시 자식 고유 메서드 매칭 가능
        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
        assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void argsMatch() {
        pointcut.setExpression("execution(* *(String))"); //String 타입 파라미터 허용 (String)
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void argsMatchNoArgs() {
        pointcut.setExpression("execution(* *())"); //파라미터가 없는 메서드만 매칭 (X)
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    public void argsMatchStar() {
        pointcut.setExpression("execution(* *(*))"); //파라미터의 모든 타입이 가능하나 딱 하나만 가져야 매칭
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void argsMatchAll() {
        pointcut.setExpression("execution(* *(..))"); //파라미터의 타입, 개수에 무관하게 매칭
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void argsMatchStartWithString() {
        pointcut.setExpression("execution(* *(String, ..))"); //파라미터의 타입, 개수에 무관하나 String으로 시작해야 매칭
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void argsMatchStartWithStringAndTwo() {
        pointcut.setExpression("execution(* *(String, *))"); //String으로 시작하고 2개여야 매칭
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }
}