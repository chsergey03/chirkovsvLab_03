package tech.reliab.course.chirkovsv.bank.service.impl;

import tech.reliab.course.chirkovsv.bank.entity.Bank;
import tech.reliab.course.chirkovsv.bank.entity.BankAtm;
import tech.reliab.course.chirkovsv.bank.entity.BankOffice;

import tech.reliab.course.chirkovsv.bank.model.request.BankAtmRequest;

import tech.reliab.course.chirkovsv.bank.repository.BankAtmRepository;

import tech.reliab.course.chirkovsv.bank.service.BankAtmService;
import tech.reliab.course.chirkovsv.bank.service.BankOfficeService;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BankAtmServiceImpl implements BankAtmService {
  private final BankAtmRepository atmRepository;

  private final BankOfficeService officeService;

  /**
   * создаёт банкомат.
   *
   * @param request данные, необходимые для создания банкомата.
   * @return созданный банкомат.
   */
  @Override
  public BankAtm createAtm(BankAtmRequest request) {
    BankOffice office = officeService.getOfficeById(request.getOfficeId());

    Bank bank = office.getBank();

    BankAtm bankAtmToCreate = new BankAtm();

    bankAtmToCreate.setName(request.getName());
    bankAtmToCreate.setAddress(office.getAddress());
    bankAtmToCreate.setStatus(request.getStatus());

    bankAtmToCreate.setDepositAvailable(request.isDepositAvailable());
    bankAtmToCreate.setWithdrawAvailable(request.isWithdrawAvailable());

    bankAtmToCreate.setMoneyAmount(request.getMoneyAmount());
    bankAtmToCreate.setMaintenanceCost(request.getMaintenanceCost());

    bankAtmToCreate.setBank(bank);
    bankAtmToCreate.setOffice(office);

    return atmRepository.save(bankAtmToCreate);
  }

  /**
   * Возвращает список всех банкоматов.
   *
   * @return список всех банкоматов.
   */
  @Override
  public List<BankAtm> getAllAtms() {
    return atmRepository.findAll();
  }

  /**
   * Возвращает банкомат с указанным идентификатором,
   * если он существует.
   *
   * @param id идентификатор искомого банкомата.
   * @return банкомат с указанным идентификатором,
   * если он существует.
   */
  @Override
  public BankAtm getAtmById(Integer id) {
    return atmRepository.findAtmById(id)
        .orElseThrow(() -> new NoSuchElementException("atm was not found"));
  }

  /**
   * Изменяет банкомат с указанным идентификатором.
   *
   * @param id      идентификатор искомого банкомата.
   * @param newName новое название банкомата.
   */
  @Override
  public void updateAtm(Integer id, String newName) {
    BankAtm atmToUpdate = getAtmById(id);

    atmToUpdate.setName(newName);

    atmRepository.save(atmToUpdate);
  }

  /**
   * Меняет значение доступности вноса денег в
   * банкомат на противоположный.
   *
   * @param id идентификатор искомого банкомата.
   */
  private void switchIsDepositAvailable(Integer id) {
    BankAtm atmToEdit = getAtmById(id);

    atmToEdit.setDepositAvailable(!atmToEdit.isDepositAvailable());

    atmRepository.save(atmToEdit);
  }

  /**
   * Меняет значение доступности вывода денег из
   * банкомата на противоположный.
   *
   * @param id идентификатор искомого банкомата.
   */
  private void switchIsWithdrawAvailable(Integer id) {
    BankAtm atmToEdit = getAtmById(id);

    atmToEdit.setWithdrawAvailable(!atmToEdit.isWithdrawAvailable());

    atmRepository.save(atmToEdit);
  }

  /**
   * Удаляет банкомат с указанным идентификатором.
   *
   * @param id идентификатор искомого банкомата.
   */
  @Override
  public void deleteAtm(Integer id) {
    atmRepository.deleteAtmById(id);
  }
}
