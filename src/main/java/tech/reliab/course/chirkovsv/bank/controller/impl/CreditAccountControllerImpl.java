package tech.reliab.course.chirkovsv.bank.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.reliab.course.chirkovsv.bank.controller.CreditAccountController;

import tech.reliab.course.chirkovsv.bank.model.dto.CreditAccountDto;

import tech.reliab.course.chirkovsv.bank.model.request.CreditAccountRequest;

import tech.reliab.course.chirkovsv.bank.service.CreditAccountService;

import java.util.List;

import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/credit_accounts")
public class CreditAccountControllerImpl implements CreditAccountController {
  private final CreditAccountService creditAccountService;

  @PostMapping
  @Override
  public ResponseEntity<CreditAccountDto> createCreditAccount(CreditAccountRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(
        new CreditAccountDto(creditAccountService.createCreditAccount(request))
    );
  }

  @GetMapping
  @Override
  public ResponseEntity<List<CreditAccountDto>> getAllCreditAccounts() {
    return ResponseEntity.ok(creditAccountService.getAllCreditAccounts().stream().map(CreditAccountDto::new).toList());
  }

  @GetMapping("/{id}")
  @Override
  public ResponseEntity<CreditAccountDto> getCreditAccountById(@PathVariable Integer id) {
    return ResponseEntity.ok(new CreditAccountDto(creditAccountService.getCreditAccountById(id)));
  }

  @PatchMapping("/{creditAccountId}/{newUserId}")
  @Override
  public ResponseEntity<CreditAccountDto> updateCreditAccount(
      @PathVariable Integer creditAccountId,
      @PathVariable Integer newUserId
  ) {
    creditAccountService.updateCreditAccount(creditAccountId, newUserId);

    return ResponseEntity.ok(new CreditAccountDto(creditAccountService.getCreditAccountById(creditAccountId)));
  }

  @DeleteMapping("/{id}")
  @Override
  public ResponseEntity<Void> deleteCreditAccount(@PathVariable Integer id) {
    creditAccountService.deleteCreditAccount(id);

    return ResponseEntity.noContent().build();
  }
}
