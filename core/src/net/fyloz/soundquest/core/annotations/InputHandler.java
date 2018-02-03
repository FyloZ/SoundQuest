package net.fyloz.soundquest.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface InputHandler {
	public static enum Type {
		KEYS, TOUCH, ALL
	}

	Type type() default Type.ALL;
}
