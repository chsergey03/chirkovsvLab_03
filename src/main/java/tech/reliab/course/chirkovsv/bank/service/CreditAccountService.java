package tech.reliab.course.chirkovsv.bank.service;

import tech.reliab.course.chirkovsv.bank.entity.CreditAccount;

import tech.reliab.course.chirkovsv.bank.model.request.CreditAccountRequest;

import java.util.List;

public interface CreditAccountService {
  CreditAccount createCreditAccount(CreditAccountRequest request);

  List<CreditAccount> getAllCreditAccounts();

  CreditAccount getCreditAccountById(Integer id);

  void updateCreditAccount(Integer creditAccountId, Integer newUserId);

  void deleteCreditAccount(Integer id);
}