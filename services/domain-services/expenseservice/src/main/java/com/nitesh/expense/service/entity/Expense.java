package com.nitesh.expense.service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "et_expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expense_id_seq_generator")
    @SequenceGenerator(name = "expense_id_seq_generator", sequenceName = "et_seq_expense_id", allocationSize = 1)
    @Column(name = "expense_Id")
    private Long expenseId;

    @Column(name = "user_Id")
    private Long userId;

    @Column(name = "expense_title")
    private String expenseTitle;

    @Column(name = "expense_description")
    private String expenseDescription;

    @Column(name = "expense_amount")
    private BigDecimal expenseAmount;

    @Column(name = "expense_date")
    private LocalDate expenseDate;

    @Column(name = "expense_location")
    private String expenseLocation;

    @Column(name = "tags")
    private String tags;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "notes")
    private String notes;

    @Column(name = "attachment_id")
    private Long attachmentId;

    //optional Field
    @Column(name = "currency")
    private String currency;

    @Column(name = "exchange_rate")
    private BigDecimal exchangeRate;

    //Audit Log
    @Column(name = "expense_input_time")
    private LocalDateTime expenseInputTime;

    @Column(name = "event_id")
    private Long eventId;

    @PrePersist
    public void onCreate() {
        this.expenseInputTime = LocalDateTime.now();
    }
}
