package tech.reliab.course.chirkovsv.bank.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankRequest {
  private String name;
  private Long rating;
  private Long interestRate;
  private Long totalMoneyAmount;
}