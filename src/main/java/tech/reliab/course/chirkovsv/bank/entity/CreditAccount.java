package tech.reliab.course.chirkovsv.bank.entity;

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
@Table(name = "credit_accounts")
public class CreditAccount {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer id;

  @Column(nullable = false)
  private LocalDate startDate;

  @Column(nullable = false)
  private LocalDate endDate;

  @Column(nullable = false)
  private Integer nMonths;

  @Column(nullable = false)
  private Long moneyAmount;

  @Column(nullable = false)
  private Long monthlyPayment;

  @Column(nullable = false)
  private Long interestRate;

  @ManyToOne
  private Bank bank;

  @ManyToOne
  private User user;

  @ManyToOne
  private Employee employeeProvidedCredit;

  @ManyToOne
  private PaymentAccount paymentAccount;
}