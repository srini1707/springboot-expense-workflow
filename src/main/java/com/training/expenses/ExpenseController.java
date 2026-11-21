package com.training.expenses;
import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;import org.springframework.http.*;import org.springframework.data.domain.*;import java.time.*;
@RestController @RequestMapping("/api/v1/expenses") public class ExpenseController{
 private final ExpenseService s;public ExpenseController(ExpenseService s){this.s=s;}
 @PostMapping public ResponseEntity<Expense> create(@Valid @RequestBody ExpenseRequest r){return ResponseEntity.status(201).body(s.create(r));}
 @GetMapping("/{id}") public Expense get(@PathVariable long id,@RequestParam long ownerId){return s.get(id,ownerId);}
 @PostMapping("/{id}/submit") public Expense submit(@PathVariable long id,@RequestParam long ownerId){return s.submit(id,ownerId);}
 @PostMapping("/{id}/decision") public Expense decide(@PathVariable long id,@RequestParam boolean approve){return s.decide(id,approve);}
 @GetMapping public Page<Expense> search(@RequestParam long ownerId,@RequestParam LocalDate from,@RequestParam LocalDate to,@RequestParam(required=false) String category,@PageableDefault(size=20,sort="spentOn",direction=Sort.Direction.DESC) Pageable p){return s.search(ownerId,from,to,category,p);}
}
