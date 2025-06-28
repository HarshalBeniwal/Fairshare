package com.harshal.beniwal.fairshare.controller;

import com.harshal.beniwal.fairshare.model.ApiResponse;
import com.harshal.beniwal.fairshare.model.expense.ExpenseRequestDTO;
import com.harshal.beniwal.fairshare.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ApiResponse<String>> addExpense(@RequestBody ExpenseRequestDTO expenseRequest){
        expenseService.addExpense(expenseRequest);
        return ResponseEntity.ok(ApiResponse.success(null ,"Expense added successfully"));
    }
}
