package tech.reliab.course.chirkovsv.bank.entity;

import java.util.List;
import java.time.LocalDate;

import jakarta.persistence.*;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "employees")
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer id;

  @Column(nullable = false)
  private String fullName;

  @Column(nullable = false)
  private LocalDate birthDate;

  @Column(nullable = false)
  private String post;

  @Column(nullable = false)
  private boolean isWorkInTheOffice;

  @Column(nullable = false)
  private boolean isAbilityToRegisterCredits;

  @Column(nullable = false)
  private Long salary;

  @ManyToOne
  private Bank bank;

  @ManyToOne
  private BankOffice office;

  @OneToMany(mappedBy = "servingEmployee")
  private List<BankAtm> atms;
}