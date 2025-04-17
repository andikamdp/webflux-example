package id.co.practice.webflux.dto.request;

import id.co.practice.webflux.dto.response.ValidationErrorResponseDto;
import id.co.practice.webflux.util.DateTimeUtil;
import id.co.practice.webflux.util.MessageSourceUtil;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Getter
@Setter
public class EmployeeDto {
    private UUID id;
    private String identityId;
    private String name;
    private LocalDate birthDate;
    private LocalDate joinDate;
    private BigDecimal salary;

    private String maritalStatus;

    private int status;


    public List<ValidationErrorResponseDto> validate(boolean isUpdate, Locale locale) {
        List<ValidationErrorResponseDto> validationErrorResponseDtoList = new ArrayList<>();

        // Custom validation logic can be added here
        if (isUpdate && (this.getId() != null)) {
            validationErrorResponseDtoList.add(new ValidationErrorResponseDto("employee.id", MessageSourceUtil.getErrorMessage(locale, "employee.id")));
        }

        if (this.getIdentityId() != null && this.getIdentityId().matches("^[0-9]{1,20}$")) {
            validationErrorResponseDtoList.add(new ValidationErrorResponseDto("employee.identityId", MessageSourceUtil.getErrorMessage(locale, "employee.identityId")));
        }

        if (this.getName() == null || this.getName().matches("^[a-zA-Z ]{1,50}$")) {
            validationErrorResponseDtoList.add(new ValidationErrorResponseDto("employee.name", MessageSourceUtil.getErrorMessage(locale, "employee.name")));
        }

        if (this.getJoinDate() == null || (DateTimeUtil.isLocalDateToday(this.getJoinDate()) || DateTimeUtil.isLocalDateAfterNow(this.getJoinDate()))) {
            validationErrorResponseDtoList.add(new ValidationErrorResponseDto("employee.joinDate", MessageSourceUtil.getErrorMessage(locale, "employee.joinDate")));
        }

        if (this.getBirthDate() == null || (DateTimeUtil.isLocalDateBeforeNow(this.getBirthDate()))) {
            validationErrorResponseDtoList.add(new ValidationErrorResponseDto("employee.birthDate", MessageSourceUtil.getErrorMessage(locale, "employee.birthDate")));
        }

        if (this.getSalary() == null || this.getSalary().compareTo(BigDecimal.ONE) < 0 || this.getSalary().toPlainString().matches("^\\d{1,10}(\\.\\d{1,2})?$")) {
            validationErrorResponseDtoList.add(new ValidationErrorResponseDto("employee.salary", MessageSourceUtil.getErrorMessage(locale, "employee.salary")));
        }

        return validationErrorResponseDtoList;
    }
}
