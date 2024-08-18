package hello.aop.internalcall;

import hello.aop.internalcall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest //test 시 스프링 컨테이너를 써야함
class CallServiceV0Test {

    @Autowired
    CallServiceV0 callServiceV0;

    @Test
    void external() {
        callServiceV0.external();
        //external()을 실행하며 callServiceV0 내부의 internal()이 실행될 때 aop=... 로그가 찍히지 않음(Advice가 적용되지 않음)
        //JAVA는 앞에 참조가 없으면 this.를 가리키게 되어 실제 객체의 internal()이 호출된 것
        //실제 객체는 AOP 적용 대상이 아니므로 로그가 찍히지 않음
    }

    @Test
    void internal() {
        callServiceV0.internal();
    }
}