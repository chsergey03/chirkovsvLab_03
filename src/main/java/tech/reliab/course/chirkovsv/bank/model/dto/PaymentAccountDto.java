package tech.reliab.course.chirkovsv.bank.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import tech.reliab.course.chirkovsv.bank.entity.PaymentAccount;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentAccountDto {
  private Integer id;
  private Long moneyAmount;

  private String bankName;
  private String userFullName;

  public PaymentAccountDto(PaymentAccount paymentAccount) {
    this.id = paymentAccount.getId();
    this.moneyAmount = paymentAccount.getMoneyAmount();

    this.bankName = paymentAccount.getBank().getName();
    this.userFullName = paymentAccount.getUser().getFullName();
  }
}
