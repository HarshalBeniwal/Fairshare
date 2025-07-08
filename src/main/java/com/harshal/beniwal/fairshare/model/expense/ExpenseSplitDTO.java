package com.harshal.beniwal.fairshare.model.expense;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExpenseSplitDTO {
    private Long id;
    private Long expenseId;
    private Long ownedByUserId;
    private String ownedByUserName;
    private double amount;
}