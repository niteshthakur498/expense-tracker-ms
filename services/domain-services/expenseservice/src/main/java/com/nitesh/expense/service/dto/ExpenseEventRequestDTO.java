package com.nitesh.expense.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseEventRequestDTO {

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("eventTitle")
    private String eventTitle;

    @JsonProperty("eventDescription")
    private String eventDescription;

    @JsonProperty("budget")
    private BigDecimal budget;

}
