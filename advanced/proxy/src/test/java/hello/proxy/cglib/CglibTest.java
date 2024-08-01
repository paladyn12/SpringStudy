package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    @Test
    public void cglib() {
        ConcreteService target = new ConcreteService();

        Enhancer enhancer = new Enhancer(); //CGLIB 만드는 역할
        enhancer.setSuperclass(ConcreteService.class); //CGLIB의 상위 클래스를 ConcreteService로 지정
        enhancer.setCallback(new TimeMethodInterceptor(target)); //CGLIB의 Callback함수를 TimeMethodInterceptor()로 지정하여 target을 넣어줌
        ConcreteService proxy = (ConcreteService) enhancer.create();

        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        proxy.call();

    }

}
