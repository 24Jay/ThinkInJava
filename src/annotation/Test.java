package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Java目前内置了三种标准注解: @Override, @Deprecated, @SuppressWarnings
 * 四种元注解: @Target, @Retention, @Documented, @Inherited
 * 
 * @Target:表示注解可以用在什么地方，CONTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE,
 *                                  PARAMETER,TYPE
 * @Retention:表示需要在什么级别保存注解信息, SOURCE(将被编译器丢弃),CLASS(class文件中可用，但是会被JVM丢弃),RUNTIME(JVM运行期也会保留，
 *                             因此可以通过反射机制来读取注解信息)
 * @author jay
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test
{
}
