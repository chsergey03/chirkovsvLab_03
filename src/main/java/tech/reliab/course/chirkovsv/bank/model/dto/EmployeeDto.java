package tech.reliab.course.chirkovsv.bank.model.dto;

import java.util.List;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import tech.reliab.course.chirkovsv.bank.entity.BankAtm;
import tech.reliab.course.chirkovsv.bank.entity.Employee;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
  private Integer id;
  private String fullName;
  private LocalDate birthDate;
  private String post;
  private boolean isWorkInTheOffice;
  private boolean isAbilityToRegisterCredits;
  private Long salary;

  private String bankName;
  private String officeName;
  private List<String> atmsNames;

  public EmployeeDto(Employee employee) {
    this.id = employee.getId();
    this.fullName = employee.getFullName();
    this.birthDate = employee.getBirthDate();
    this.post = employee.getPost();
    this.isWorkInTheOffice = employee.isWorkInTheOffice();
    this.isAbilityToRegisterCredits = employee.isAbilityToRegisterCredits();
    this.salary = employee.getSalary();

    this.bankName = employee.getBank().getName();
    this.officeName = employee.getOffice().getName();
    this.atmsNames = employee.getAtms().stream().map(BankAtm::getName).toList();
  }
}
