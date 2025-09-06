package com.nitesh.expense.service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "et_expense_event")
public class ExpenseEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_id_seq_generator")
    @SequenceGenerator(name = "event_id_seq_generator", sequenceName = "et_seq_event_id", allocationSize = 1)
    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "event_title")
    private String eventTitle;

    @Column(name = "event_description")
    private String eventDescription;

    @Column(name = "event_status", nullable = false)
    private String eventStatus;

    @Column(name = "event_input_time")
    private LocalDateTime eventInputTime;

    @Column(name = "budget")
    private BigDecimal budget;

    @PrePersist
    public void onCreate() {
        this.setEventInputTime(LocalDateTime.now());
        this.setEventStatus("O");
    }

}