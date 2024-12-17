package tech.reliab.course.chirkovsv.bank.controller;

import tech.reliab.course.chirkovsv.bank.model.dto.PaymentAccountDto;

import tech.reliab.course.chirkovsv.bank.model.request.PaymentAccountRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface PaymentAccountController {
  ResponseEntity<PaymentAccountDto> createPaymentAccount(PaymentAccountRequest request);

  ResponseEntity<List<PaymentAccountDto>> getAllPaymentAccounts();

  ResponseEntity<PaymentAccountDto> getPaymentAccountById(@PathVariable Integer id);

  ResponseEntity<PaymentAccountDto> updatePaymentAccount(
      @PathVariable Integer paymentAccountId,
      @PathVariable Integer newUserId
  );

  ResponseEntity<Void> deletePaymentAccount(@PathVariable Integer id);
}
