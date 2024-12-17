package tech.reliab.course.chirkovsv.bank.repository;

import tech.reliab.course.chirkovsv.bank.entity.BankOffice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankOfficeRepository extends JpaRepository<BankOffice, Integer> {
  Optional<BankOffice> findOfficeById(Integer id);

  void deleteOfficeById(Integer id);
}