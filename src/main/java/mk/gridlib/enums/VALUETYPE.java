package mk.gridlib.enums;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public enum VALUETYPE {

    NUMBER(Double.class, Float.class, Integer.class, Byte.class, BigDecimal.class),
    STRING(Character.class, String.class),
    DATE(Date.class, LocalDateTime.class, LocalDate.class, LocalTime.class),
    COLLECTION(Collection.class),
    ENUMERATION(Enum.class),
    BOOLEAN(Boolean.class, boolean.class),
    OBJECT();

    private final List<Class<?>> classes;

    VALUETYPE(Class<?>... classes) {
        this.classes = Arrays.asList(classes);
    }

    public List<Class<?>> getClasses() {
        return classes;
    }

    public static VALUETYPE getType(Class<?> clazz) {
        for (VALUETYPE fieldvaluetype : VALUETYPE.values()){
            for (Class<?> c : fieldvaluetype.classes){
                if (c.isAssignableFrom(clazz)){
                    return fieldvaluetype;
                }
            }
        }
        return OBJECT;
    }
}
