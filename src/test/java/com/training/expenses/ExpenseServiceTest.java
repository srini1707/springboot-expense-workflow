package com.training.expenses;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
class ExpenseServiceTest {
 @Test void enforcesApprovalWorkflow(){var s=new ExpenseService();var e=s.create(new ExpenseRequest("E1","TRAVEL","Taxi",new BigDecimal("450"),LocalDate.now()));
  assertEquals("SUBMITTED",s.submit(e.id()).status());assertEquals("APPROVED",s.approve(e.id(),"M1").status());}
}
