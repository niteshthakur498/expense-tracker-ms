package com.nitesh.expense.service.controller;

import com.nitesh.expense.service.constants.ResponseMessage;
import com.nitesh.expense.service.dto.ExpenseRequestDTO;
import com.nitesh.expense.service.dto.ExpenseResponseDTO;
import com.nitesh.expense.service.dto.ResponseWrapper;
import com.nitesh.expense.service.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class ExpenseController {


    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/{userId}/expenses")
    public ResponseEntity<ResponseWrapper<List<ExpenseResponseDTO>>> getExpenseByUser(@PathVariable Long userId) {
        List<ExpenseResponseDTO> userExpenses = expenseService.getAllUserExpenses(userId);
        ResponseWrapper<List<ExpenseResponseDTO>> response = new ResponseWrapper<>(HttpStatus.OK.value(),
                ResponseMessage.RETRIEVED,
                userExpenses,
                null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}/expense-event/{eventId}/expenses")
    public ResponseEntity<ResponseWrapper<List<ExpenseResponseDTO>>> getExpenseByUserAndEvent(@PathVariable Long userId,
                                                                                              @PathVariable Long eventId) {
        List<ExpenseResponseDTO> userExpenses = expenseService.getExpenseByUserAndEvent(userId, eventId);
        ResponseWrapper<List<ExpenseResponseDTO>> response = new ResponseWrapper<>(HttpStatus.OK.value(),
                ResponseMessage.RETRIEVED,
                userExpenses,
                null);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{userId}/expenses")
    public ResponseEntity<ResponseWrapper<ExpenseResponseDTO>> createExpense(@PathVariable Long userId,
                                                                             @RequestBody ExpenseRequestDTO expenseRequest) {
        ExpenseResponseDTO expenseResponseDTO = expenseService.addExpense(expenseRequest);
        ResponseWrapper<ExpenseResponseDTO> response = new ResponseWrapper<>(HttpStatus.OK.value(),
                ResponseMessage.CREATED,
                expenseResponseDTO,
                null);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);

    }

    @PutMapping("/{userId}/expenses/{expenseId}")
    public ResponseEntity<ResponseWrapper<ExpenseResponseDTO>> updateExpense(@PathVariable Long userId,
                                                                             @PathVariable Long expenseId,
                                                                             @RequestBody ExpenseRequestDTO expenseRequest) {
        ExpenseResponseDTO expenseResponseDTO = expenseService.updateExpense(userId, expenseId, expenseRequest);
        ResponseWrapper<ExpenseResponseDTO> response = new ResponseWrapper<>(HttpStatus.OK.value(),
                ResponseMessage.UPDATED,
                expenseResponseDTO,
                null);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);

    }

    @GetMapping("/{userId}/expenses/{expenseId}")
    public ResponseEntity<ResponseWrapper<ExpenseResponseDTO>> getExpenseById(@PathVariable Long userId,
                                                                              @PathVariable Long expenseId) {
        ExpenseResponseDTO expenseResponseDTO = expenseService.getExpenseById(userId, expenseId);
        ResponseWrapper<ExpenseResponseDTO> response = new ResponseWrapper<>(HttpStatus.OK.value(),
                ResponseMessage.RETRIEVED,
                expenseResponseDTO,
                null);
        return ResponseEntity.status(200)
                .body(response);
    }

    @DeleteMapping("/{userId}/expenses/{expenseId}")
    public ResponseEntity<ResponseWrapper<String>> deleteExpense(@PathVariable Long userId,
                                                                 @PathVariable Long expenseId) {

        expenseService.deleteExpense(userId, expenseId);
        ResponseWrapper<String> response = new ResponseWrapper<>(HttpStatus.OK.value(),
                ResponseMessage.DELETED,
                null,
                null);
        return ResponseEntity.ok(response);
    }
}
