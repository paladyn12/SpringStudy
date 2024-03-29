package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void basicScan(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        OrderService orderService = ac.getBean(OrderService.class);
        Assertions.assertThat(orderService).isInstanceOf(OrderService.class);

    }
}
