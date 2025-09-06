package com.nitesh.expense.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseEventResponseDTO {

    private Long eventId;

    private Long userId;

    private String eventTitle;

    private String eventDescription;

    private BigDecimal budget;

    private String eventStatus;

    private LocalDateTime eventInputTime;

}
