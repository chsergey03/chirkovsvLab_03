package tech.reliab.course.chirkovsv.bank.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import tech.reliab.course.chirkovsv.bank.controller.PaymentAccountController;

import tech.reliab.course.chirkovsv.bank.model.dto.PaymentAccountDto;

import tech.reliab.course.chirkovsv.bank.model.request.PaymentAccountRequest;

import java.util.List;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import tech.reliab.course.chirkovsv.bank.service.PaymentAccountService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment_accounts")
public class PaymentAccountControllerImpl implements PaymentAccountController {
  private final PaymentAccountService paymentAccountService;

  @PostMapping
  @Override
  public ResponseEntity<PaymentAccountDto> createPaymentAccount(PaymentAccountRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(
        new PaymentAccountDto(paymentAccountService.createPaymentAccount(request))
    );
  }

  @GetMapping
  @Override
  public ResponseEntity<List<PaymentAccountDto>> getAllPaymentAccounts() {
    return ResponseEntity.ok(paymentAccountService.getAllPaymentAccounts().stream().map(PaymentAccountDto::new).toList());
  }

  @GetMapping("/{id}")
  @Override
  public ResponseEntity<PaymentAccountDto> getPaymentAccountById(@PathVariable Integer id) {
    return ResponseEntity.ok(new PaymentAccountDto(paymentAccountService.getPaymentAccountById(id)));
  }

  @PatchMapping("/{paymentAccountId}/{newUserId}")
  @Override
  public ResponseEntity<PaymentAccountDto> updatePaymentAccount(
      @PathVariable Integer paymentAccountId,
      @PathVariable Integer newUserId
  ) {
    paymentAccountService.updatePaymentAccount(paymentAccountId, newUserId);

    return ResponseEntity.ok(new PaymentAccountDto(paymentAccountService.getPaymentAccountById(paymentAccountId)));
  }

  @DeleteMapping("/{id}")
  @Override
  public ResponseEntity<Void> deletePaymentAccount(@PathVariable Integer id) {
    paymentAccountService.deletePaymentAccount(id);

    return ResponseEntity.noContent().build();
  }
}
