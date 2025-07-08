package com.harshal.beniwal.fairshare.model.expense;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ExpenseSummaryDTO {
    double net;
    double totalPaidExpense;
    double totalOwes;
    List<ExpenseDTO> expenseList;
    List<ExpenseSplitDTO> expenseSplits;
}
