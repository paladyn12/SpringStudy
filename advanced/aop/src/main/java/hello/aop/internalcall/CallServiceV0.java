package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV0 {

    public void external() {
        log.info("call external");
        internal(); //내부 메서드 호출(this.internal()), 메서드 호출의 대상을 지정하지 않으면 앞에 this. 이 생략된 것
    }

    public void internal() {
        log.info("call internal");
    }
}
