package com.nitesh.expense.replica.service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "expenses_raw")
@CompoundIndex(name = "user_category_date_idx", def = "{'userId': 1, 'categoryId': 1, 'expenseDate': -1}")
public class ExpenseRaw {

    @Id
    private String expenseId; // String to match CDC id (can also be Long)

    private Long userId;

    private String expenseTitle;

    private String expenseDescription;

    private BigDecimal expenseAmount;

    private LocalDate expenseDate;

    private String expenseLocation;

    private List<String> tags; // stored as array in Mongo

    private Long categoryId;

    private String paymentMethod;

    private String notes;

    private Long attachmentId;

    private String currency;

    private BigDecimal exchangeRate;

    // Audit log
    private LocalDateTime expenseInputTime;

    private Long eventId;

    private Boolean deleted; // soft delete flag for CDC "d" operation
}
