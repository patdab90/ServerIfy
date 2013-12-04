package pl.poznan.put.cs.ify.webify.data.annotation.user;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pl.poznan.put.cs.ify.webify.data.enums.user.UserRole;

/**
 * 
 */
@Target(value = { ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ViewAccessible {

	UserRole[] roles() default {};

	String[] users() default {};

}
