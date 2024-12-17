package tech.reliab.course.chirkovsv.bank.controller;

import tech.reliab.course.chirkovsv.bank.model.dto.BankOfficeDto;

import tech.reliab.course.chirkovsv.bank.model.request.BankOfficeRequest;

import java.util.List;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface BankOfficeController {
  ResponseEntity<BankOfficeDto> createOffice(BankOfficeRequest request);

  ResponseEntity<List<BankOfficeDto>> getAllOffices();

  ResponseEntity<BankOfficeDto> getOfficeById(@PathVariable Integer id);

  ResponseEntity<BankOfficeDto> updateOffice(
      @PathVariable Integer id,
      @RequestParam(name = "newOfficeName") String newOfficeName
  );

  ResponseEntity<Void> deleteOffice(@PathVariable Integer id);
}
