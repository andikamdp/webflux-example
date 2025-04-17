package id.co.practice.webflux.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

@Table(schema = "public", name = "tbl_employee_tm")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {
    @Id
    private UUID id;
    private String identityId;
    private String name;
    private LocalDate birthDate;
    private LocalDate joinDate;
    private BigDecimal salary;

    private int status;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

}
