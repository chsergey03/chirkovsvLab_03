package tech.reliab.course.chirkovsv.bank.repository;

import tech.reliab.course.chirkovsv.bank.entity.Bank;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, Integer> {
  Optional<Bank> findBankById(Integer id);

  void deleteBankById(Integer id);
}