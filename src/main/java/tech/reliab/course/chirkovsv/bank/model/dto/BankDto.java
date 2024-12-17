package tech.reliab.course.chirkovsv.bank.model.dto;

import tech.reliab.course.chirkovsv.bank.entity.*;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankDto {
  private Integer id;
  private String name;
  private Long rating;
  private Long totalMoneyAmount;
  private Long interestRate;

  private List<String> officesNames;
  private List<String> atmsNames;
  private List<String> employeesFullNames;
  private List<Long> creditAccountsMoneyAmounts;
  private List<Long> paymentAccountsMoneyAmounts;

  public BankDto(Bank bank) {
    this.id = bank.getId();
    this.name = bank.getName();
    this.rating = bank.getRating();
    this.totalMoneyAmount = bank.getTotalMoneyAmount();
    this.interestRate = bank.getInterestRate();

    this.officesNames = bank.getOffices().stream().map(BankOffice::getName).toList();
    this.atmsNames = bank.getAtms().stream().map(BankAtm::getName).toList();
    this.employeesFullNames = bank.getEmployees().stream().map(Employee::getFullName).toList();
    this.creditAccountsMoneyAmounts = bank.getCreditAccounts().stream().map(CreditAccount::getMoneyAmount).toList();
    this.paymentAccountsMoneyAmounts = bank.getPaymentAccounts().stream().map(PaymentAccount::getMoneyAmount).toList();
  }
}