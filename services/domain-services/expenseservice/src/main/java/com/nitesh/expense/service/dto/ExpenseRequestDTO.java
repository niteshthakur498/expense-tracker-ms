package com.nitesh.expense.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseRequestDTO {

    //@NotBlank(message = "")
    @JsonProperty("userId")                // Maps this field to "userId" in the JSON
    private Long userId;

   // @NotBlank(message = "")
    @JsonProperty("expenseTitle")           // Maps this field to "expenseTitle" in the JSON
    private String expenseTitle;

    @JsonProperty("expenseDescription")      // Maps this field to "expenseDescription" in the JSON
    private String expenseDescription;

   // @NotBlank(message = "")
    @JsonProperty("expenseAmount")           // Maps this field to "expenseAmount" in the JSON
    private BigDecimal expenseAmount;

    //@NotBlank(message = "")
    @JsonProperty("expenseDate")             // Maps this field to "expenseDate" in the JSON
    private LocalDate expenseDate;

    @JsonProperty("expenseLocation")          // Maps this field to "expenseLocation" in the JSON
    private String expenseLocation;

    @JsonProperty("tags")                     // Maps this field to "tags" in the JSON
    private String tags;

    @JsonProperty("categoryId")                 // Maps this field to "category" in the JSON
    private Long categoryId;

    @JsonProperty("paymentMethod")            // Maps this field to "paymentMethod" in the JSON
    private String paymentMethod;

    @JsonProperty("notes")                    // Maps this field to "notes" in the JSON
    private String notes;

    @JsonProperty("attachmentId")             // Maps this field to "attachmentId" in the JSON
    private Long attachmentId;

    //@NotBlank(message = "")
    @JsonProperty("currency")                 // Maps this field to "currency" in the JSON
    private String currency;

    @JsonProperty("eventId")                 // Maps this field to "currency" in the JSON
    private Long eventId;
}
