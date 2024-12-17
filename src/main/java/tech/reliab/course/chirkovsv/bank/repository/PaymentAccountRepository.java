package tech.reliab.course.chirkovsv.bank.repository;

import tech.reliab.course.chirkovsv.bank.entity.PaymentAccount;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentAccountRepository extends JpaRepository<PaymentAccount, Integer> {
  Optional<PaymentAccount> findPaymentAccountById(Integer id);

  void deletePaymentAccountById(Integer id);
}