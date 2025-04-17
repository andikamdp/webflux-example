package id.co.practice.webflux.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class HolidayDto {
    private UUID id;
    private LocalDate holidayDate;
    private String description;
    private int status;
}
