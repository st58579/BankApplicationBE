package mk.gridlib.anotations;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Translation {
    String label();
    String languageTag();
}
