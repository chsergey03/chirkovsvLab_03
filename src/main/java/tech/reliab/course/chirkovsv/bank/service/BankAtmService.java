package tech.reliab.course.chirkovsv.bank.service;

import tech.reliab.course.chirkovsv.bank.entity.BankAtm;

import tech.reliab.course.chirkovsv.bank.model.dto.BankAtmDto;

import tech.reliab.course.chirkovsv.bank.model.request.BankAtmRequest;

import java.util.List;

public interface BankAtmService {
  BankAtm createAtm(BankAtmRequest atmRequest);

  List<BankAtm> getAllAtms();

  BankAtm getAtmById(Integer id);

  void updateAtm(Integer id, final String newName);

  void deleteAtm(Integer id);
}