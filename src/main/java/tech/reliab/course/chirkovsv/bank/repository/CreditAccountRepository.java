package tech.reliab.course.chirkovsv.bank.repository;

import tech.reliab.course.chirkovsv.bank.entity.CreditAccount;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreditAccountRepository extends JpaRepository<CreditAccount, Integer> {
  Optional<CreditAccount> findCreditAccountById(Integer id);

  void deleteCreditAccountById(Integer id);
}