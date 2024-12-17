package tech.reliab.course.chirkovsv.bank.model.dto;

import tech.reliab.course.chirkovsv.bank.entity.BankAtm;
import tech.reliab.course.chirkovsv.bank.enums.impl.BankAtmStatusEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAtmDto {
  private Integer id;
  private String name;
  private String address;
  private BankAtmStatusEnum status;
  private String locationInTheOffice;
  private boolean isDepositAvailable;
  private boolean isWithdrawAvailable;
  private Long moneyAmount;
  private Long maintenanceCost;

  private String bankName;
  private String officeName;
  private String servingEmployeeFullName;

  public BankAtmDto(BankAtm atm) {
    this.id = atm.getId();
    this.name = atm.getName();
    this.address = atm.getAddress();
    this.status = atm.getStatus();
    this.locationInTheOffice = atm.getLocationInTheOffice();
    this.isDepositAvailable = atm.isDepositAvailable();
    this.isWithdrawAvailable = atm.isWithdrawAvailable();
    this.moneyAmount = atm.getMoneyAmount();
    this.maintenanceCost = atm.getMaintenanceCost();

    this.bankName = atm.getBank().getName();
    this.officeName = atm.getOffice().getName();
    this.servingEmployeeFullName = atm.getServingEmployee().getFullName();
  }
}
