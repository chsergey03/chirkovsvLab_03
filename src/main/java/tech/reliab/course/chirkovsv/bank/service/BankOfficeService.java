package tech.reliab.course.chirkovsv.bank.service;

import tech.reliab.course.chirkovsv.bank.entity.BankOffice;

import tech.reliab.course.chirkovsv.bank.model.request.BankOfficeRequest;

import java.util.List;

public interface BankOfficeService {
  BankOffice createOffice(BankOfficeRequest officeRequest);

  List<BankOffice> getAllOffices();

  BankOffice getOfficeById(Integer id);

  void updateOffice(Integer id, String newName);

  void deleteOffice(Integer id);
}