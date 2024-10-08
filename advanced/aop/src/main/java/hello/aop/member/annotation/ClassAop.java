package hello.aop.member.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) //Class에 붙이는 애노테이션
@Retention(RetentionPolicy.RUNTIME) //런타임에 애노테이션이 살아있음
public @interface ClassAop {
}
