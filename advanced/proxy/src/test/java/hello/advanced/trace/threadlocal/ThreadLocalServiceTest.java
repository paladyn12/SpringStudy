package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import hello.advanced.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {

    private ThreadLocalService threadLocalService = new ThreadLocalService();

    @Test
    public void field() {
        log.info("main start");
        Runnable userA = () -> {
            threadLocalService.logic("userA");
        }; //Runnable userA = new Runnable() { @Override public void run() { ~~~ } } 를 줄인 표현
        Runnable userB = () -> {
            threadLocalService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        sleep(500); //동시성 문제 방지를 위한 시간 간격
        threadB.start();

        sleep(2000); //메인 쓰레드 종료 대기, 이 라인이 없으면 threadB가 돌던 중 메인 쓰레드가 종료됨
    }

    public void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
