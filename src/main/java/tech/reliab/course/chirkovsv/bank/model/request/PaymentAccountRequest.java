package tech.reliab.course.chirkovsv.bank.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentAccountRequest {
  private Long moneyAmount;

  private Integer bankId;
  private Integer userId;
}
