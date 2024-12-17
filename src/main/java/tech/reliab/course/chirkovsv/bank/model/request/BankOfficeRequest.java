package tech.reliab.course.chirkovsv.bank.model.request;

import tech.reliab.course.chirkovsv.bank.enums.impl.BankOfficeStatusEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankOfficeRequest {
  private Integer id;
  private String name;
  private String address;
  private BankOfficeStatusEnum status;
  private boolean isAtmPlacingAvailable;
  private boolean isCreditRegistrationAvailable;
  private boolean isDepositAvailable;
  private boolean isWithdrawAvailable;
  private Long totalMoneyAmount;
  private Long rentalCost;

  private Integer bankId;
}
