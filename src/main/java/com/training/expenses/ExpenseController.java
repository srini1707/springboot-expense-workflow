package com.training.expenses;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/v1/expenses")
public class ExpenseController {
 private final ExpenseService s;public ExpenseController(ExpenseService s){this.s=s;}
 @PostMapping @ResponseStatus(org.springframework.http.HttpStatus.CREATED) public Expense create(@Valid @RequestBody ExpenseRequest r){return s.create(r);}
 @GetMapping("/{id}") public Expense get(@PathVariable long id){return s.get(id);}
 @GetMapping public List<Expense> list(@RequestParam String employeeId){return s.byEmployee(employeeId);}
 @PatchMapping("/{id}/submit") public Expense submit(@PathVariable long id){return s.submit(id);}
 @PatchMapping("/{id}/approve") public Expense approve(@PathVariable long id,@RequestParam String approverId){return s.approve(id,approverId);}
 @PatchMapping("/{id}/reject") public Expense reject(@PathVariable long id,@RequestParam String approverId,@RequestParam String reason){return s.reject(id,approverId,reason);}
}
