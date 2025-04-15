package id.co.practice.webflux.config;

import id.co.practice.webflux.anotation.ConditionalValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ConditionalValidator implements ConstraintValidator<ConditionalValidation, Object> {

    private String field;
    private String dependentField;

    @Override
    public void initialize(ConditionalValidation constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.dependentField = constraintAnnotation.dependentField();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            Object fieldValue = value.getClass().getDeclaredField(field).get(value);
            Object dependentFieldValue = value.getClass().getDeclaredField(dependentField).get(value);

            if (fieldValue != null && !fieldValue.toString().isEmpty()) {
                return dependentFieldValue != null && !dependentFieldValue.toString().isEmpty();
            }
            return true; // Skip validation if the field is not present
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return false; // Invalid if reflection fails
        }
    }
}
