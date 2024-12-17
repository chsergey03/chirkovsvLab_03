package tech.reliab.course.chirkovsv.bank.entity;

import java.util.List;

import jakarta.persistence.*;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "banks")
public class Bank {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Long rating;

  @Column(nullable = false)
  private Long totalMoneyAmount;

  @Column(nullable = false)
  private Long interestRate;

  @OneToMany(mappedBy = "bank")
  private List<BankOffice> offices;

  @OneToMany(mappedBy = "bank")
  private List<BankAtm> atms;

  @OneToMany(mappedBy = "bank")
  private List<Employee> employees;

  @OneToMany(mappedBy = "bank")
  private List<CreditAccount> creditAccounts;

  @OneToMany(mappedBy = "bank")
  private List<PaymentAccount> paymentAccounts;
}