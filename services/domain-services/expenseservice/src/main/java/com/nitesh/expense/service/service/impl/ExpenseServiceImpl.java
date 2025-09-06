package com.nitesh.expense.service.service.impl;


import com.nitesh.expense.service.dto.ExpenseRequestDTO;
import com.nitesh.expense.service.dto.ExpenseResponseDTO;
import com.nitesh.expense.service.entity.Expense;
import com.nitesh.expense.service.exceptions.ResourceNotFoundException;
import com.nitesh.expense.service.mapper.ExpenseMapper;
import com.nitesh.expense.service.repository.ExpenseRepository;
import com.nitesh.expense.service.service.ExpenseService;
import com.nitesh.expense.service.validator.ExpenseValidator;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseValidator expenseValidator;
    private final ExpenseMapper expenseMapper;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository,
                              ExpenseMapper expenseMapper,
                              ExpenseValidator expenseValidator
    ) {
        this.expenseRepository = expenseRepository;
        this.expenseMapper = expenseMapper;
        this.expenseValidator = expenseValidator;
    }

    @Override
    @Transactional
    public ExpenseResponseDTO addExpense(ExpenseRequestDTO expenseRequest) {
        expenseValidator.validateExpense(expenseRequest);
        Expense expense = expenseMapper.toEntity(expenseRequest);
        Expense createdExpense = expenseRepository.save(expense);

        return expenseMapper.toResponseDTO(createdExpense);
    }

    @Override
    @Transactional
    public ExpenseResponseDTO updateExpense(Long userId,
                                            Long expenseId,
                                            ExpenseRequestDTO expenseRequest) {
        Expense existingExpense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense Record not found for Expense: " + expenseId));
        log.debug("Expense Record Found for Modification...");
        expenseMapper.map(expenseRequest, existingExpense);
        Expense updatedExpense = expenseRepository.save(existingExpense);
        return expenseMapper.toResponseDTO(updatedExpense);
    }

    @Override
    @Transactional
    public void deleteExpense(Long userId,
                              Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense Record not found for Expense: " + expenseId));
        log.debug("Expense Record Found for Deletion..");
        expenseRepository.deleteById(expenseId);
    }

    @Override
    @Transactional
    public ExpenseResponseDTO getExpenseById(Long userId,
                                             Long expenseId) {
        log.debug("Entered getExpenseById...");
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense Record not found for Expense: " + expenseId));
        log.debug("Expense Record Found...");
        return expenseMapper.toResponseDTO(expense);
    }

    @Override
    @Transactional
    public List<ExpenseResponseDTO> getAllUserExpenses(Long userId) {
        return expenseRepository.findByUserId(userId)
                .stream()
                .map(expenseMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ExpenseResponseDTO> getExpenseByUserAndEvent(Long userId,
                                                             Long eventId) {
        return expenseRepository.findByUserIdAndEventId(userId, eventId)
                .stream()
                .map(expenseMapper::toResponseDTO)
                .collect(Collectors.toList());

    }
}
