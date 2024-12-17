package tech.reliab.course.chirkovsv.bank.entity;

import tech.reliab.course.chirkovsv.bank.enums.impl.BankOfficeStatusEnum;

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
@Table(name = "offices")
public class BankOffice {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String address;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private BankOfficeStatusEnum status;

  @Column(nullable = false)
  private boolean isAtmPlacingAvailable;

  @Column(nullable = false)
  private boolean isCreditRegistrationAvailable;

  @Column(nullable = false)
  private boolean isDepositAvailable;

  @Column(nullable = false)
  private boolean isWithdrawAvailable;

  @Column(nullable = false)
  private Long totalMoneyAmount;

  @Column(nullable = false)
  private Long rentalCost;

  @ManyToOne
  private Bank bank;

  @OneToMany(mappedBy = "office")
  private List<BankAtm> atms;

  @OneToMany(mappedBy = "office")
  private List<Employee> employees;
}