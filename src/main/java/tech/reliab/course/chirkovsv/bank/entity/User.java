package tech.reliab.course.chirkovsv.bank.entity;

import java.time.LocalDate;
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
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer id;

  @Column(nullable = false)
  private String fullName;

  @Column(nullable = false)
  private LocalDate birthDate;

  @Column
  private String workplace;

  @Column(nullable = false)
  private Long monthlyIncome;

  @Column(nullable = false)
  private Long creditRating;

  @ManyToMany
  @JoinTable(
      name = "user_banks",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "bank_id")
  )
  private List<Bank> banks;

  @OneToMany(mappedBy = "user")
  private List<CreditAccount> creditAccounts;

  @OneToMany(mappedBy = "user")
  private List<PaymentAccount> paymentAccounts;
}