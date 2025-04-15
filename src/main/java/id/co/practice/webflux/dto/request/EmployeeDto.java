package id.co.practice.webflux.dto.request;

import id.co.practice.webflux.anotation.ConditionalValidation;
import id.co.practice.webflux.anotation.ConditionalValidations;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ConditionalValidations({
        @ConditionalValidation(field = "identityId", dependentField = "name", message = "Name is required when Identity ID is provided"),
        @ConditionalValidation(field = "email", dependentField = "phoneNumber", message = "Phone number is required when email is provided")
})public class EmployeeDto {
    private UUID id;
    @NotEmpty
    @Size(min = 1, max = 20)
    @Pattern(regexp = "^[0-9]+$")
    private String identityId;
    @NotEmpty
    @Size(min = 1, max = 50)
    @Pattern(regexp = "^[a-zA-Z ]+$")
    private String name;
    @Past
    private LocalDate birthDate;
    @FutureOrPresent
    private LocalDate joinDate;
    @Min(value = 0, message = "{invalid.salary}")
    @Max(value = 1000000000)
    @Positive
    @Digits(integer = 10, fraction = 2)
    private BigDecimal salary;
    private int status;

}
