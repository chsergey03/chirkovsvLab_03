package tech.reliab.course.chirkovsv.bank.service.impl;

import tech.reliab.course.chirkovsv.bank.entity.*;

import tech.reliab.course.chirkovsv.bank.model.dto.BankOfficeDto;

import tech.reliab.course.chirkovsv.bank.model.request.BankOfficeRequest;

import tech.reliab.course.chirkovsv.bank.repository.BankOfficeRepository;

import tech.reliab.course.chirkovsv.bank.service.BankService;
import tech.reliab.course.chirkovsv.bank.service.BankOfficeService;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BankOfficeServiceImpl implements BankOfficeService {
  private final BankOfficeRepository officeRepository;

  private final BankService bankService;

  /**
   * Создаёт новый банковский офис.
   *
   * @param request данные для создания офиса.
   * @return созданный банковский офис.
   */
  @Override
  public BankOffice createOffice(BankOfficeRequest request) {
    Bank bank = bankService.getBankById(request.getBankId());

    BankOffice officeToCreate = new BankOffice();

    if (!officeToCreate.isAtmPlacingAvailable()) {
      throw new IllegalArgumentException("atm placing is not available in the office");
    }

    officeToCreate.setName(request.getName());
    officeToCreate.setAddress(request.getAddress());
    officeToCreate.setStatus(request.getStatus());

    officeToCreate.setAtmPlacingAvailable(request.isAtmPlacingAvailable());
    officeToCreate.setCreditRegistrationAvailable(request.isCreditRegistrationAvailable());
    officeToCreate.setDepositAvailable(request.isDepositAvailable());
    officeToCreate.setWithdrawAvailable(request.isWithdrawAvailable());

    officeToCreate.setTotalMoneyAmount(request.getTotalMoneyAmount());
    officeToCreate.setRentalCost(request.getRentalCost());

    officeToCreate.setBank(bank);

    return officeRepository.save(officeToCreate);
  }

  /**
   * Возвращает список всех банковских офисов.
   *
   * @return список всех банковских офисов.
   */
  @Override
  public List<BankOffice> getAllOffices() {
    return officeRepository.findAll();
  }

  /**
   * возвращает банковский офис с указанным идентификатором,
   * если он существует.
   *
   * @param id идентификатор искомого банковского офиса.
   * @return банковский офис с указанным идентификатором,
   * если он существует.
   */
  @Override
  public BankOffice getOfficeById(Integer id) {
    return officeRepository.findOfficeById(id)
        .orElseThrow(
            () -> new NoSuchElementException("office was not found")
        );
  }

  /**
   * Изменяет банковский офис с указанным идентификатором.
   *
   * @param id      идентификатор искомого банковского офиса.
   * @param newName новое название банковского офиса.
   */
  public void updateOffice(Integer id, String newName) {
    BankOffice officeToUpdate = getOfficeById(id);

    officeToUpdate.setName(newName);

    officeRepository.save(officeToUpdate);
  }

  /**
   * Меняет значение флага доступности размещения
   * банкоматов в офисе на противоположный.
   *
   * @param id идентификатор искомого офиса.
   */
  private void switchIsAtmPlacingAvailable(Integer id) {
    BankOffice officeToEdit = getOfficeById(id);

    officeToEdit.setAtmPlacingAvailable(!officeToEdit.isAtmPlacingAvailable());

    officeRepository.save(officeToEdit);
  }

  /**
   * Меняет значение флага доступности выдачи
   * кредитов в офисе на противоположный.
   *
   * @param id идентификатор искомого офиса.
   */
  private void switchIsCreditRegistrationAvailable(Integer id) {
    BankOffice officeToEdit = getOfficeById(id);

    officeToEdit.setCreditRegistrationAvailable(!officeToEdit.isCreditRegistrationAvailable());

    officeRepository.save(officeToEdit);
  }

  /**
   * Меняет значение флага доступности вноса
   * денег в офис на противоположный.
   *
   * @param id идентификатор искомого офиса.
   */
  private void switchIsDepositAvailable(Integer id) {
    BankOffice officeToEdit = getOfficeById(id);

    officeToEdit.setDepositAvailable(!officeToEdit.isDepositAvailable());

    officeRepository.save(officeToEdit);
  }

  /**
   * Меняет значение флага доступности вывода
   * денег из офиса на противоположный.
   *
   * @param id идентификатор искомого офиса.
   */
  private void switchIsWithdrawAvailable(Integer id) {
    BankOffice officeToEdit = getOfficeById(id);

    officeToEdit.setWithdrawAvailable(!officeToEdit.isWithdrawAvailable());

    officeRepository.save(officeToEdit);
  }

  /**
   * Удаляет банковский офис с указанным идентификатором.
   *
   * @param id идентификатор искомого банковского офиса.
   */
  @Override
  public void deleteOffice(Integer id) {
    officeRepository.deleteOfficeById(id);
  }
}
