package com.training.expenses;
import org.springframework.stereotype.Service;
import java.time.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class ExpenseService {
 private final Map<Long,Expense> data=new ConcurrentHashMap<>();private final AtomicLong ids=new AtomicLong();
 public Expense create(ExpenseRequest r){if(r.expenseDate().isAfter(LocalDate.now()))throw new IllegalArgumentException("Expense date cannot be in future");
  var now=Instant.now();var e=new Expense(ids.incrementAndGet(),r.employeeId(),r.category(),r.description(),r.amount(),r.expenseDate(),"DRAFT",null,null,now,now);data.put(e.id(),e);return e;}
 public Expense get(long id){var e=data.get(id);if(e==null)throw new NoSuchElementException("Expense not found");return e;}
 public List<Expense> byEmployee(String employee){return data.values().stream().filter(e->e.employeeId().equals(employee)).sorted(Comparator.comparing(Expense::createdAt).reversed()).toList();}
 public Expense submit(long id){var e=get(id);if(!e.status().equals("DRAFT")&&!e.status().equals("REJECTED"))throw new IllegalStateException("Only draft or rejected expenses can be submitted");
  return save(e,"SUBMITTED",null,null);}
 public Expense approve(long id,String approver){var e=get(id);if(!e.status().equals("SUBMITTED"))throw new IllegalStateException("Only submitted expenses can be approved");return save(e,"APPROVED",approver,null);}
 public Expense reject(long id,String approver,String reason){var e=get(id);if(!e.status().equals("SUBMITTED"))throw new IllegalStateException("Only submitted expenses can be rejected");if(reason==null||reason.isBlank())throw new IllegalArgumentException("Rejection reason is required");return save(e,"REJECTED",approver,reason);}
 private Expense save(Expense e,String status,String approver,String reason){var n=new Expense(e.id(),e.employeeId(),e.category(),e.description(),e.amount(),e.expenseDate(),status,approver,reason,e.createdAt(),Instant.now());data.put(e.id(),n);return n;}
}
