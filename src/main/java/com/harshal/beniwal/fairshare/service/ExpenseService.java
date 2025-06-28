package com.harshal.beniwal.fairshare.service;

import com.harshal.beniwal.fairshare.entity.*;
import com.harshal.beniwal.fairshare.exception.DomainException;
import com.harshal.beniwal.fairshare.model.expense.ExpenseRequestDTO;
import com.harshal.beniwal.fairshare.repository.ExpenseRepository;
import com.harshal.beniwal.fairshare.repository.ExpenseSplitRepository;
import com.harshal.beniwal.fairshare.repository.GroupRepository;
import com.harshal.beniwal.fairshare.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;
    private final ExpenseSplitRepository expenseSplitRepository;
    @Transactional
    public void addExpense(ExpenseRequestDTO expenseRequest) {

        UserGroup userGroup = groupRepository.findById(expenseRequest.getGroupId())
                .orElseThrow(() ->
                        new DomainException.ResourceNotFoundException("No group found"));
        User payer = userRepository.findById(expenseRequest.getPayerId()).orElseThrow(
                ()-> new DomainException.UserException("User not found" , HttpStatus.NOT_FOUND)
        );

        List<User> userList = userRepository.findAllById(expenseRequest.getParticipants());
        if (userList.isEmpty()) {
            throw new DomainException.GroupException("No valid users found for the provided IDs",
                    HttpStatus.BAD_REQUEST);
        }

        Expense expense = Expense.builder()
                .description(expenseRequest.getDescription())
                .amount(expenseRequest.getAmount())
                .split_mode(expenseRequest.getSplitmode())
                .group(userGroup)
                .payer(payer)
                .participants(userList)
                .build();

        expenseRepository.save(expense);


        double share;
        if (expenseRequest.getSplitmode() == SPLITMODE.EQUAL) {
            share = expenseRequest.getAmount() / userList.size();
        } else {
            throw new UnsupportedOperationException("Only EQUAL split supported now");
        }

        List<ExpenseSplit> expenseSplits = userList.stream()
                .map(user -> ExpenseSplit.builder()
                        .expense(expense)
                        .amount(share)
                        .ownedBy(user).build())
                .toList();
        expenseSplitRepository.saveAll(expenseSplits);
    }
}
