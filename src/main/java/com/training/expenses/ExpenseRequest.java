package com.training.expenses;
import jakarta.validation.constraints.*;import java.math.*;import java.time.*;
public record ExpenseRequest(@NotNull @Positive Long ownerId,@NotBlank String category,@NotBlank String description,@NotNull @DecimalMin("0.01") BigDecimal amount,@NotNull LocalDate spentOn){}
