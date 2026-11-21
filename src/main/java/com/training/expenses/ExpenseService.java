package com.training.expenses;
import org.springframework.stereotype.*;import org.springframework.transaction.annotation.*;import org.springframework.data.domain.*;import java.time.*;
@Service public class ExpenseService{
 private final ExpenseRepository r;public ExpenseService(ExpenseRepository r){this.r=r;}
 @Transactional public Expense create(ExpenseRequest x){return r.save(new Expense(x.ownerId(),x.category(),x.description(),x.amount(),x.spentOn()));}
 @Transactional(readOnly=true) public Expense get(long id,long owner){return r.findById(id).filter(e->e.ownerId.equals(owner)).orElseThrow(()->new IllegalArgumentException("Expense not found"));}
 @Transactional public Expense submit(long id,long owner){var e=get(id,owner);if(e.status!=Expense.Status.DRAFT)throw new IllegalStateException("Only draft expenses can be submitted");e.status=Expense.Status.SUBMITTED;return e;}
 @Transactional public Expense decide(long id,boolean approve){var e=r.findById(id).orElseThrow(()->new IllegalArgumentException("Expense not found"));if(e.status!=Expense.Status.SUBMITTED)throw new IllegalStateException("Expense must be submitted");e.status=approve?Expense.Status.APPROVED:Expense.Status.REJECTED;return e;}
 @Transactional(readOnly=true) public Page<Expense> search(long owner,LocalDate from,LocalDate to,String category,Pageable p){if(from.isAfter(to))throw new IllegalArgumentException("Invalid date range");return category==null?r.findByOwnerIdAndSpentOnBetween(owner,from,to,p):r.findByOwnerIdAndCategoryIgnoreCaseAndSpentOnBetween(owner,category,from,to,p);}
}
