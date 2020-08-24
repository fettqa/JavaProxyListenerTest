package methodInfo_test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) //on class level
public @interface TestMethodInfo {

    //Приоритет теста
    Priority priority() default Priority.Major;

    //Автор теста
    String author() default "Bill Gates";

    //Дата последних изменений в тесте
    String lastModified() default "01.01.2019";
}