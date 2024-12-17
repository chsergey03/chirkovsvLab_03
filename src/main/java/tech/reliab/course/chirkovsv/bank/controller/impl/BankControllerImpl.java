package tech.reliab.course.chirkovsv.bank.controller.impl;

import lombok.RequiredArgsConstructor;
import tech.reliab.course.chirkovsv.bank.controller.BankController;

import tech.reliab.course.chirkovsv.bank.model.dto.BankDto;

import tech.reliab.course.chirkovsv.bank.model.request.BankRequest;

import tech.reliab.course.chirkovsv.bank.service.BankService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/banks")
public class BankControllerImpl implements BankController {
  private final BankService bankService;

  @PostMapping
  @Override
  public ResponseEntity<BankDto> createBank(BankRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(
        new BankDto(bankService.createBank(request))
    );
  }

  @GetMapping
  @Override
  public ResponseEntity<List<BankDto>> getAllBanks() {
    return ResponseEntity.ok(bankService.getAllBanks().stream().map(BankDto::new).toList());
  }

  @GetMapping("/{id}")
  @Override
  public ResponseEntity<BankDto> getBankById(@PathVariable Integer id) {
    return ResponseEntity.ok(new BankDto(bankService.getBankById(id)));
  }

  @PatchMapping("/{id}")
  @Override
  public ResponseEntity<BankDto> updateBank(
      @PathVariable Integer id,
      @RequestParam(name = "newBankName") String newBankName
  ) {
    bankService.updateBank(id, newBankName);

    return ResponseEntity.ok(new BankDto(bankService.getBankById(id)));
  }

  @DeleteMapping("/{id}")
  @Override
  public ResponseEntity<Void> deleteBank(@PathVariable Integer id) {
    bankService.deleteBank(id);

    return ResponseEntity.noContent().build();
  }
}