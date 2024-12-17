package tech.reliab.course.chirkovsv.bank.controller;

import tech.reliab.course.chirkovsv.bank.model.dto.BankDto;

import tech.reliab.course.chirkovsv.bank.model.request.BankRequest;

import java.util.List;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

public interface BankController {
  ResponseEntity<BankDto> createBank(BankRequest request);

  ResponseEntity<List<BankDto>> getAllBanks();

  ResponseEntity<BankDto> getBankById(@PathVariable Integer id);

  ResponseEntity<BankDto> updateBank(
      @PathVariable Integer id,
      @RequestParam(name = "newBankName") String newBankName
  );

  ResponseEntity<Void> deleteBank(@PathVariable Integer id);
}
