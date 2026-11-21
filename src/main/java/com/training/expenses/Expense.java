package com.training.expenses;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
public record Expense(Long id,String employeeId,String category,String description,BigDecimal amount,LocalDate expenseDate,
 String status,String approverId,String rejectionReason,Instant createdAt,Instant updatedAt) {}
