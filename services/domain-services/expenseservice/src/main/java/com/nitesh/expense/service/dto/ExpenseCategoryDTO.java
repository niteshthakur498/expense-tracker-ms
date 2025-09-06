package com.nitesh.expense.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseCategoryDTO {
    private Long categoryId;

    private String categoryTitle;

    private String categoryDescription;
}
