package tech.reliab.course.chirkovsv.bank.model.dto;

import tech.reliab.course.chirkovsv.bank.entity.CreditAccount;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditAccountDto {
  private Integer id;
  private LocalDate startDate;
  private LocalDate endDate;
  private Integer nMonths;
  private Long moneyAmount;
  private Long monthlyPayment;
  private Long interestRate;

  private String bankName;
  private String userFullName;
  private String employeeProvidedCreditFullName;
  private Long paymentAccountMoneyAmount;

  public CreditAccountDto(CreditAccount creditAccount) {
    this.id = creditAccount.getId();
    this.startDate = creditAccount.getStartDate();
    this.endDate = creditAccount.getEndDate();
    this.nMonths = creditAccount.getNMonths();
    this.moneyAmount = creditAccount.getMoneyAmount();
    this.monthlyPayment = creditAccount.getMonthlyPayment();
    this.interestRate = creditAccount.getInterestRate();

    this.bankName = creditAccount.getBank().getName();
    this.userFullName = creditAccount.getUser().getFullName();
    this.employeeProvidedCreditFullName = creditAccount.getEmployeeProvidedCredit().getFullName();
    this.paymentAccountMoneyAmount = creditAccount.getPaymentAccount().getMoneyAmount();
  }
}
