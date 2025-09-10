package com.nitesh.expense.replica.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DbzPayload {
    private String op;
    private DbzData before;
    private DbzData after;
}
