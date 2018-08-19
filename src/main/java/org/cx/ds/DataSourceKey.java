package org.cx.ds;

import java.lang.annotation.*;

/**
 * @author grass
 * @date 2018/8/11
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSourceKey {
    String value() default "";
}
