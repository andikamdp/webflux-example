package id.co.practice.webflux.anotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
public @interface ConditionalValidations {
    ConditionalValidation[] value();
    String message() default "Invalid conditional validations";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}