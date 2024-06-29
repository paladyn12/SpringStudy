package hello.advanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate { //변하는 부분이 call(), 상속받은 클래스가 이를 구현

    public void excute() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        call();
        //비즈니스 로직 실행
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);

    }

    protected abstract void call();
}
