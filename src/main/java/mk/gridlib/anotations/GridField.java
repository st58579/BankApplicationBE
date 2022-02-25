package mk.gridlib.anotations;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface GridField {
    String defaultLabel() default "";
    String tableColumn() default "";
    Translation[] translations() default {};

}
