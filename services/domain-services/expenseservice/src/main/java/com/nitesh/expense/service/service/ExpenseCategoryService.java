package com.nitesh.expense.service.service;



import com.nitesh.expense.service.dto.ExpenseCategoryDTO;

import java.util.List;

public interface ExpenseCategoryService {
    List<ExpenseCategoryDTO> getAllCategories();

    ExpenseCategoryDTO getCategory(Long categoryId);
}
