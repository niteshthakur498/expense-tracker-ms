package com.nitesh.expense.service.validator;


import com.nitesh.expense.service.dto.ErrorDetail;
import com.nitesh.expense.service.dto.ExpenseRequestDTO;
import com.nitesh.expense.service.entity.ExpenseCategory;
import com.nitesh.expense.service.entity.ExpenseEvent;
import com.nitesh.expense.service.exceptions.ValidationException;
import com.nitesh.expense.service.repository.ExpenseCategoryRepository;
import com.nitesh.expense.service.repository.ExpenseEventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class ExpenseValidator {

    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final ExpenseEventRepository expenseEventRepository;

    @Autowired
    public ExpenseValidator(ExpenseCategoryRepository expenseCategoryRepository,
                            ExpenseEventRepository expenseEventRepository) {
        this.expenseCategoryRepository = expenseCategoryRepository;
        this.expenseEventRepository = expenseEventRepository;
    }

    public void validateExpense(ExpenseRequestDTO expenseRequest) {
        log.debug("Starting Validating Expense Request..");
        List<ErrorDetail> errors = new ArrayList<>();
        log.debug("Event Id: {}", expenseRequest.getEventId());
        //Validating Amount
        validateAmount(expenseRequest.getExpenseAmount(), errors);

        //Validating Categories
        validateCategory(expenseRequest.getCategoryId(), errors);

        validateEvent(expenseRequest.getEventId(), errors);
        log.debug(errors.toString());
        if (!errors.isEmpty()) {
            throw new ValidationException("Validations Failed", errors);
        }
    }

    private void validateCategory(Long categoryId,
                                  List<ErrorDetail> errors) {
        log.debug("Starting Validating Expense Category..");
        if (categoryId == null || categoryId <= 0) {
            errors.add(new ErrorDetail("categoryId", "Category Id cannot be null"));
            return;
        }

        Optional<ExpenseCategory> expenseCategory = expenseCategoryRepository.findById(categoryId);

        if (expenseCategory.isEmpty()) {
            errors.add(new ErrorDetail("categoryId", "Invalid Category Id"));
        }
    }

    private void validateEvent(Long eventId,
                               List<ErrorDetail> errors) {
        log.debug("Starting Validating Expense Event..");
        if (eventId != null) {
            Optional<ExpenseEvent> expenseEvent = expenseEventRepository.findById(eventId);

            if (expenseEvent.isEmpty()) {
                errors.add(new ErrorDetail("eventId", "Invalid Event Id"));
            }
        }


    }

    private void validateAmount(BigDecimal amount,
                                List<ErrorDetail> errors) {
        log.debug("Starting Validating Expense Amount..");
        if (amount.compareTo(new BigDecimal(0)) <= 0) {
            errors.add(new ErrorDetail("expenseAmount", "Expense Amount cannot be less than or Equal to 0"));
        }
    }
}
