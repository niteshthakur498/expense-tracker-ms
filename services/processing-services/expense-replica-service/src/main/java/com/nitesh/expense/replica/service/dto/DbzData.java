package com.nitesh.expense.replica.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DbzData {
    @JsonProperty("expense_id")
    private Long expenseId;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("expense_title")
    private String expenseTitle;

    @JsonProperty("expense_description")
    private String expenseDescription;

    @JsonProperty("expense_amount")
    private BigDecimal expenseAmount;

    @JsonProperty("expense_date")
    private String expenseDate;

    @JsonProperty("expense_location")
    private String expenseLocation;

    @JsonProperty("tags")
    private String tags;

    @JsonProperty("category_id")
    private Long categoryId;

    @JsonProperty("payment_method")
    private String paymentMethod;

    @JsonProperty("notes")
    private String notes;

    @JsonProperty("attachment_id")
    private Long attachmentId;

    // Optional Field
    @JsonProperty("currency")
    private String currency;

    @JsonProperty("exchange_rate")
    private BigDecimal exchangeRate;

    // Audit Log
    @JsonProperty("expense_input_time")
    private Instant expenseInputTime;

    @JsonProperty("event_id")
    private Long eventId;
}
