package id.co.practice.webflux.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Table(schema = "public", name = "tbl_holiday_tm")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HolidayEntity {
    @Id
    private UUID id;
    @Column("holiday_date")
    private LocalDate holidayDate;
    private String description;
    private int status;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
