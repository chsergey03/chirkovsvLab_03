package tech.reliab.course.chirkovsv.bank.service;

import tech.reliab.course.chirkovsv.bank.entity.Bank;

import tech.reliab.course.chirkovsv.bank.model.request.BankRequest;

import java.util.List;

public interface BankService {
  Bank createBank(BankRequest bankRequest);

  List<Bank> getAllBanks();

  Bank getBankById(Integer id);

  void updateBank(Integer id, String newName);

  void deleteBank(Integer id);
}