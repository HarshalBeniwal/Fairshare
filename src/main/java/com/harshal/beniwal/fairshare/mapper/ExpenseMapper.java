package com.harshal.beniwal.fairshare.mapper;

import com.harshal.beniwal.fairshare.entity.Expense;
import com.harshal.beniwal.fairshare.entity.ExpenseSplit;
import com.harshal.beniwal.fairshare.model.expense.ExpenseDTO;
import com.harshal.beniwal.fairshare.model.expense.ExpenseSplitDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {
    @Mapping(source = "expense.id", target = "expenseId")
    @Mapping(source = "ownedBy.id", target = "ownedByUserId")
    @Mapping(source = "ownedBy.displayName", target = "ownedByUserName")
    ExpenseSplitDTO toExpenseSplitDTO(ExpenseSplit expenseSplit);

    @Mapping(source = "payer", target = "group")
    @Mapping(source = "split_mode", target = "splitMode")
    ExpenseDTO toExpenseDTO(Expense expense);
    List<ExpenseDTO> toExpenseDTOList(List<Expense> expenseList);
    List<ExpenseSplitDTO>  toExpenseSplitDTOList(List<ExpenseSplit> expenseSplitList);

}
