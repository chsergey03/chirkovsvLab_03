package tech.reliab.course.chirkovsv.bank.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.reliab.course.chirkovsv.bank.controller.BankOfficeController;

import tech.reliab.course.chirkovsv.bank.model.dto.BankOfficeDto;

import tech.reliab.course.chirkovsv.bank.model.request.BankOfficeRequest;

import tech.reliab.course.chirkovsv.bank.service.BankOfficeService;

import java.util.List;

import org.springframework.http.ResponseEntity;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/offices")
public class BankOfficeControllerImpl implements BankOfficeController {
  private final BankOfficeService officeService;

  @PostMapping
  @Override
  public ResponseEntity<BankOfficeDto> createOffice(BankOfficeRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(
        new BankOfficeDto(officeService.createOffice(request))
    );
  }

  @GetMapping
  @Override
  public ResponseEntity<List<BankOfficeDto>> getAllOffices() {
    return ResponseEntity.ok(officeService.getAllOffices().stream().map(BankOfficeDto::new).toList());
  }

  @GetMapping("/{id}")
  @Override
  public ResponseEntity<BankOfficeDto> getOfficeById(@PathVariable Integer id) {
    return ResponseEntity.ok(new BankOfficeDto(officeService.getOfficeById(id)));
  }

  @PatchMapping("/{id}")
  @Override
  public ResponseEntity<BankOfficeDto> updateOffice(
      @PathVariable Integer id,
      @RequestParam(name = "newOfficeName") String newOfficeName
  ) {
    officeService.updateOffice(id, newOfficeName);

    return ResponseEntity.ok(new BankOfficeDto(officeService.getOfficeById(id)));
  }

  @DeleteMapping("/{id}")
  @Override
  public ResponseEntity<Void> deleteOffice(@PathVariable Integer id) {
    officeService.deleteOffice(id);

    return ResponseEntity.noContent().build();
  }
}
