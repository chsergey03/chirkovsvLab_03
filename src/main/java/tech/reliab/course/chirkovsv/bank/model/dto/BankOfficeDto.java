package tech.reliab.course.chirkovsv.bank.model.dto;

import tech.reliab.course.chirkovsv.bank.entity.BankAtm;
import tech.reliab.course.chirkovsv.bank.entity.BankOffice;
import tech.reliab.course.chirkovsv.bank.entity.Employee;
import tech.reliab.course.chirkovsv.bank.enums.impl.BankOfficeStatusEnum;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankOfficeDto {
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

  private String bankName;
  private List<String> atmsNames;
  private List<String> employeesFullNames;

  public BankOfficeDto(BankOffice office) {
    this.id = office.getId();
    this.name = office.getName();
    this.address = office.getAddress();
    this.status = office.getStatus();
    this.isAtmPlacingAvailable = office.isAtmPlacingAvailable();
    this.isCreditRegistrationAvailable = office.isCreditRegistrationAvailable();
    this.isDepositAvailable = office.isDepositAvailable();
    this.isWithdrawAvailable = office.isWithdrawAvailable();
    this.totalMoneyAmount = office.getTotalMoneyAmount();
    this.rentalCost = office.getRentalCost();

    this.bankName = office.getBank().getName();
    this.atmsNames = office.getAtms().stream().map(BankAtm::getName).toList();
    this.employeesFullNames = office.getEmployees().stream().map(Employee::getFullName).toList();
  }
}
