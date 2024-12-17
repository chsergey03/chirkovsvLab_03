package tech.reliab.course.chirkovsv.bank.entity;

import jakarta.persistence.*;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "payment_accounts")
public class PaymentAccount {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer id;

  @Column(nullable = false)
  private Long moneyAmount;

  @ManyToOne
  private Bank bank;

  @ManyToOne
  private User user;
}