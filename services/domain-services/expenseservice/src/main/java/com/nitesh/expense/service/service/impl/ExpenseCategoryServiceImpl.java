package com.nitesh.expense.service.service.impl;

import com.nitesh.expense.service.dto.ExpenseCategoryDTO;
import com.nitesh.expense.service.entity.ExpenseCategory;
import com.nitesh.expense.service.exceptions.ResourceNotFoundException;
import com.nitesh.expense.service.mapper.ExpenseCategoryMapper;
import com.nitesh.expense.service.repository.ExpenseCategoryRepository;
import com.nitesh.expense.service.service.ExpenseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ExpenseCategoryServiceImpl implements ExpenseCategoryService {
    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final ExpenseCategoryMapper expenseCategoryMapper;

    @Autowired
    public ExpenseCategoryServiceImpl(ExpenseCategoryRepository expenseCategoryRepository,
                                      ExpenseCategoryMapper expenseCategoryMapper) {
        this.expenseCategoryRepository = expenseCategoryRepository;
        this.expenseCategoryMapper = expenseCategoryMapper;
    }

    @Override
    public List<ExpenseCategoryDTO> getAllCategories() {
        List<ExpenseCategory> expenseCategory = expenseCategoryRepository.findAll();
        return expenseCategory.stream()
                .map(expenseCategoryMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExpenseCategoryDTO getCategory(Long categoryId) {
        ExpenseCategory expenseCategory = expenseCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category Record not found for: " + categoryId));
        return expenseCategoryMapper.toResponseDTO(expenseCategory);
    }
}
