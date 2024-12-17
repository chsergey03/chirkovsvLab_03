package tech.reliab.course.chirkovsv.bank.controller;

import tech.reliab.course.chirkovsv.bank.model.dto.BankAtmDto;

import tech.reliab.course.chirkovsv.bank.model.request.BankAtmRequest;

import java.util.List;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface BankAtmController {
  ResponseEntity<BankAtmDto> createAtm(BankAtmRequest request);

  ResponseEntity<List<BankAtmDto>> getAllAtms();

  ResponseEntity<BankAtmDto> getAtmById(@PathVariable Integer id);

  ResponseEntity<BankAtmDto> updateAtm(
      @PathVariable Integer id,
      @RequestParam(name = "newAtmName") String newAtmName
  );

  ResponseEntity<Void> deleteAtm(@PathVariable Integer id);
}
