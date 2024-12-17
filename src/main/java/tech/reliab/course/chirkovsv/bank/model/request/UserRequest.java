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
public class UserRequest {
  private String fullName;
  private LocalDate birthDate;
  private String workplace;
  private Long monthlyIncome;
  private Long creditRating;
}
