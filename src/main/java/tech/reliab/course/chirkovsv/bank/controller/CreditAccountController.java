package tech.reliab.course.chirkovsv.bank.controller;

import tech.reliab.course.chirkovsv.bank.model.dto.CreditAccountDto;

import tech.reliab.course.chirkovsv.bank.model.request.CreditAccountRequest;

import java.util.List;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;

public interface CreditAccountController {
  ResponseEntity<CreditAccountDto> createCreditAccount(CreditAccountRequest request);

  ResponseEntity<List<CreditAccountDto>> getAllCreditAccounts();

  ResponseEntity<CreditAccountDto> getCreditAccountById(@PathVariable Integer id);

  ResponseEntity<CreditAccountDto> updateCreditAccount(
      @PathVariable Integer creditAccountId,
      @PathVariable Integer newUserId
  );

  ResponseEntity<Void> deleteCreditAccount(@PathVariable Integer id);
}
