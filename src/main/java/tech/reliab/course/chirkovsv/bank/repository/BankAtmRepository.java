package tech.reliab.course.chirkovsv.bank.repository;

import tech.reliab.course.chirkovsv.bank.entity.BankAtm;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankAtmRepository extends JpaRepository<BankAtm, Integer> {
  Optional<BankAtm> findAtmById(Integer id);

  void deleteAtmById(Integer id);
}