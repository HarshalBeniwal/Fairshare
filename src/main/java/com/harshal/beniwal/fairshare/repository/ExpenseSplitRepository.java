package com.harshal.beniwal.fairshare.repository;

import com.harshal.beniwal.fairshare.entity.Expense;
import com.harshal.beniwal.fairshare.entity.ExpenseSplit;
import com.harshal.beniwal.fairshare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseSplitRepository extends JpaRepository<ExpenseSplit, Long> {
    List<ExpenseSplit> findByOwnedBy(User user);

    List<ExpenseSplit> findByExpense(Expense expense);
}
