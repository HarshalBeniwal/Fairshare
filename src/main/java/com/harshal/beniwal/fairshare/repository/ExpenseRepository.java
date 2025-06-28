package com.harshal.beniwal.fairshare.repository;

import com.harshal.beniwal.fairshare.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {

}
