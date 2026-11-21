package com.training.expenses;
import org.springframework.data.jpa.repository.*;import org.springframework.data.domain.*;import java.time.*;
public interface ExpenseRepository extends JpaRepository<Expense,Long>{
 Page<Expense> findByOwnerIdAndSpentOnBetween(Long owner,LocalDate from,LocalDate to,Pageable p);
 Page<Expense> findByOwnerIdAndCategoryIgnoreCaseAndSpentOnBetween(Long owner,String category,LocalDate from,LocalDate to,Pageable p);
}
