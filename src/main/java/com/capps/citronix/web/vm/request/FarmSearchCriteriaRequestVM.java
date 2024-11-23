package com.capps.citronix.web.vm.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FarmSearchCriteriaRequestVM {
    private String name;
    private String location;
    private Float minArea;
    private Float maxArea;
    private LocalDate createdAfter;
    private LocalDate createdBefore;
}
