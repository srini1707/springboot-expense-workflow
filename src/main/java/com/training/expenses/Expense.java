package com.training.expenses;
import jakarta.persistence.*;import java.math.*;import java.time.*;
@Entity @Table(indexes=@Index(columnList="ownerId,spentOn"))
public class Expense{
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 public Long ownerId;public String category,description;public BigDecimal amount;public LocalDate spentOn;
 @Enumerated(EnumType.STRING) public Status status=Status.DRAFT;@Version public Long version;
 protected Expense(){} public Expense(Long o,String c,String d,BigDecimal a,LocalDate date){ownerId=o;category=c;description=d;amount=a;spentOn=date;}
 public enum Status{DRAFT,SUBMITTED,APPROVED,REJECTED}
}
