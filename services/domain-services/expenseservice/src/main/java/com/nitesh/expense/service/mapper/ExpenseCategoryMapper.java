package com.nitesh.expense.service.mapper;


import com.nitesh.expense.service.dto.ExpenseCategoryDTO;
import com.nitesh.expense.service.entity.ExpenseCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseCategoryMapper {
    ExpenseCategoryDTO toResponseDTO(ExpenseCategory expenseCategory);
}
