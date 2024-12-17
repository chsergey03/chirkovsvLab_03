package tech.reliab.course.chirkovsv.bank.model.request;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditAccountRequest {
  private LocalDate startDate;
  private Integer nMonths;
  private Long moneyAmount;
  private Long monthlyPayment;
  private Long interestRate;

  private Integer bankId;
  private Integer userId;
  private Integer employeeProvidedCreditId;
  private Integer paymentAccountId;
}
