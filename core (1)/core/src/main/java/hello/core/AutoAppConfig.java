package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(//컴파운드 스캔시 이 키워드가 붙은 클래스를 자동으로 등록
        //Component 애노테이션이 붙은 클래스를 스캔하여 스프링 빈으로 등록, @Component 붙이기
        //@Configuration이 붙은 모두(이전의 AppConfig)가 자동 스캔의 대상, 이를 제외
        //@Configuration도 올라가보면 @Component가 붙어있음
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class),

        basePackages = "hello.core.member"//해당 패키지부터 하위 패키지만 찾아감
        //지정하지 않으면 @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작 위치
        //권장 방법 : 따로 지정하지 않고 설정 정보 클래스의 위치를 프로젝트 최상단에 두기
        )
public class AutoAppConfig {
    //@Bean으로 등록한 클래스가 없음
}
