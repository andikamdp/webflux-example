package id.co.practice.webflux.anotation;

import id.co.practice.webflux.config.ConditionalValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ConditionalValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConditionalValidation {
    String message() default "Invalid conditional validation";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String field(); // The field to check existence
    String dependentField(); // The field to validate conditionally
}