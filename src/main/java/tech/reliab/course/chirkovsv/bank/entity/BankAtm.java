package tech.reliab.course.chirkovsv.bank.entity;

import tech.reliab.course.chirkovsv.bank.enums.impl.BankAtmStatusEnum;

import jakarta.persistence.*;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "atms")
public class BankAtm {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String address;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private BankAtmStatusEnum status;

  @Column(nullable = false)
  private String locationInTheOffice;

  @Column(nullable = false)
  private boolean isDepositAvailable;

  @Column(nullable = false)
  private boolean isWithdrawAvailable;

  @Column(nullable = false)
  private Long moneyAmount;

  @Column(nullable = false)
  private Long maintenanceCost;

  @ManyToOne
  private Bank bank;

  @ManyToOne
  private BankOffice office;

  @ManyToOne
  private Employee servingEmployee;
}