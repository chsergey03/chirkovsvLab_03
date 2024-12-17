package tech.reliab.course.chirkovsv.bank.model.request;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {
  private String fullName;
  private LocalDate birthDate;
  private String post;
  private boolean isWorkInTheOffice;
  private boolean isAbilityToRegisterCredits;
  private Long salary;

  private Integer bankId;
  private Integer officeId;
}
