package tech.reliab.course.chirkovsv.bank.service;

import tech.reliab.course.chirkovsv.bank.entity.PaymentAccount;

import tech.reliab.course.chirkovsv.bank.model.request.PaymentAccountRequest;

import java.util.List;

public interface PaymentAccountService {
  PaymentAccount createPaymentAccount(PaymentAccountRequest request);

  List<PaymentAccount> getAllPaymentAccounts();

  PaymentAccount getPaymentAccountById(Integer id);

  void updatePaymentAccount(Integer paymentAccountId, Integer newUserId);

  void deletePaymentAccount(Integer id);
}