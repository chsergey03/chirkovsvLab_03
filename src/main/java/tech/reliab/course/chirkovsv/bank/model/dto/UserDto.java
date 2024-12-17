package tech.reliab.course.chirkovsv.bank.model.dto;

import java.time.LocalDate;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import tech.reliab.course.chirkovsv.bank.entity.Bank;
import tech.reliab.course.chirkovsv.bank.entity.CreditAccount;
import tech.reliab.course.chirkovsv.bank.entity.PaymentAccount;
import tech.reliab.course.chirkovsv.bank.entity.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  private Integer id;
  private String fullName;
  private LocalDate birthDate;
  private String workplace;
  private Long monthlyIncome;
  private Long creditRating;

  private List<String> banksNames;
  private List<Long> creditAccountsMoneyAmounts;
  private List<Long> paymentAccountsMoneyAmounts;

  public UserDto(User user) {
    this.id = user.getId();
    this.fullName = user.getFullName();
    this.birthDate = user.getBirthDate();
    this.workplace = user.getWorkplace();
    this.monthlyIncome = user.getMonthlyIncome();
    this.creditRating = user.getCreditRating();

    this.banksNames = user.getBanks().stream().map(Bank::getName).toList();
    this.creditAccountsMoneyAmounts = user.getCreditAccounts().stream().map(CreditAccount::getMoneyAmount).toList();
    this.paymentAccountsMoneyAmounts = user.getPaymentAccounts().stream().map(PaymentAccount::getMoneyAmount).toList();
  }
}
