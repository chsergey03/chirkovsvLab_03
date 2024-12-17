package tech.reliab.course.chirkovsv.bank.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import tech.reliab.course.chirkovsv.bank.model.dto.BankAtmDto;

import tech.reliab.course.chirkovsv.bank.model.request.BankAtmRequest;

import tech.reliab.course.chirkovsv.bank.controller.BankAtmController;

import tech.reliab.course.chirkovsv.bank.service.BankAtmService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/atms")
public class BankAtmControllerImpl implements BankAtmController {
  private final BankAtmService atmService;

  @PostMapping
  @Override
  public ResponseEntity<BankAtmDto> createAtm(BankAtmRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(
        new BankAtmDto(atmService.createAtm(request))
    );
  }

  @GetMapping
  @Override
  public ResponseEntity<List<BankAtmDto>> getAllAtms() {
    return ResponseEntity.ok(atmService.getAllAtms().stream().map(BankAtmDto::new).toList());
  }

  @GetMapping("/{id}")
  @Override
  public ResponseEntity<BankAtmDto> getAtmById(@PathVariable Integer id) {
    return ResponseEntity.ok(new BankAtmDto(atmService.getAtmById(id)));
  }

  @PatchMapping("/{id}")
  @Override
  public ResponseEntity<BankAtmDto> updateAtm(
      @PathVariable Integer id,
      @RequestParam(name = "newAtmName") String newAtmName
  ) {
    atmService.updateAtm(id, newAtmName);

    return ResponseEntity.ok(new BankAtmDto(atmService.getAtmById(id)));
  }

  @DeleteMapping("/{id}")
  @Override
  public ResponseEntity<Void> deleteAtm(@PathVariable Integer id) {
    atmService.deleteAtm(id);

    return ResponseEntity.noContent().build();
  }
}
