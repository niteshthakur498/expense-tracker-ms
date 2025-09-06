package com.nitesh.expense.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseResponseDTO {
    private Long userId;               // User who created the expense

    private Long expenseId;             // Unique identifier of the expense

    private String expenseTitle;         // Title of the expense

    private String expenseDescription;     // Description of the expense

    private BigDecimal expenseAmount;      // Amount spent

    private LocalDate expenseDate;         // Date of the expense

    private String expenseLocation;         // Location of the expense

    private String tags;                      // Tags associated with the expense

    private Long categoryId;              // Category of the expense

    private String paymentMethod;           // Payment method used

    private String notes;                   // Additional notes

    private Long attachmentId;            // Attachment associated with the expense

    private String currency;                // Currency of the expense

    private LocalDateTime expenseInputTime;        // Date when the expense was created

    private Long eventId;

}
