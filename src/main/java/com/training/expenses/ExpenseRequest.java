package com.training.expenses;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
public record ExpenseRequest(@NotBlank String employeeId,@NotBlank String category,@NotBlank String description,
 @NotNull @DecimalMin("0.01") BigDecimal amount,@NotNull LocalDate expenseDate) {}
