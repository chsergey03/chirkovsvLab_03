package tech.reliab.course.chirkovsv.bank.model.request;

import tech.reliab.course.chirkovsv.bank.enums.impl.BankAtmStatusEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAtmRequest {
  private String name;
  private BankAtmStatusEnum status;
  private String locationInTheOffice;
  private boolean isDepositAvailable;
  private boolean isWithdrawAvailable;
  private Long moneyAmount;
  private Long maintenanceCost;

  private Integer officeId;
}
