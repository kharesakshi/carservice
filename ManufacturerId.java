package com.carservice.carExample.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({FIELD,METHOD,PARAMETER})
@Documented
@Retention(RUNTIME)
@Constraint(validatedBy = { ManufacturerIdValidator.class })
public @interface ManufacturerId {

	String message() default "the manufacturer Id does not exists";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
