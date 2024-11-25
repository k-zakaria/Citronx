package com.capps.citronix.service.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FarmSearchCriteria {
    private String name;
    private String location;
    private Float minArea;
    private Float maxArea;
    private LocalDate createdAfter;
    private LocalDate createdBefore;
}
