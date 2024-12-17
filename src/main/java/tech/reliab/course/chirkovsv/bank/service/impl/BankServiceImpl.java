package tech.reliab.course.chirkovsv.bank.service.impl;

import tech.reliab.course.chirkovsv.bank.entity.*;

import tech.reliab.course.chirkovsv.bank.model.dto.BankDto;
import tech.reliab.course.chirkovsv.bank.repository.BankRepository;

import tech.reliab.course.chirkovsv.bank.model.request.BankRequest;

import tech.reliab.course.chirkovsv.bank.service.BankService;

import java.util.List;
import java.util.NoSuchElementException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {
  private final BankRepository bankRepository;
  /**
   * Создаёт новый банк.
   *
   * @param request данные для создания банка.
   * @return созданный банк.
   */
  @Override
  public Bank createBank(BankRequest request) {
    Bank bankToCreate = new Bank();

    bankToCreate.setName(request.getName());

    bankToCreate.setRating(request.getRating());
    bankToCreate.setInterestRate(request.getInterestRate());
    bankToCreate.setTotalMoneyAmount(request.getTotalMoneyAmount());

    return bankRepository.save(bankToCreate);
  }

  /**
   * Возвращает список всех банков.
   *
   * @return список всех банков.
   */
  @Override
  public List<Bank> getAllBanks() {
    return bankRepository.findAll();
  }

  /**
   * возвращает банк с указанным идентификатором,
   * если он существует.
   *
   * @param id идентификатор искомого банка.
   * @return банк с указанным идентификатором,
   * если он существует.
   */
  @Override
  public Bank getBankById(Integer id) {
    return bankRepository.findBankById(id)
        .orElseThrow(
            () -> new NoSuchElementException("bank was not found")
        );
  }

  /**
   * Изменяет банк с указанным идентификатором.
   *
   * @param id      идентификатор искомого банка.
   * @param newName новое название банка.
   */
  @Override
  public void updateBank(Integer id, String newName) {
    Bank bankToUpdate = getBankById(id);

    bankToUpdate.setName(newName);

    bankRepository.save(bankToUpdate);
  }

  /**
   * Удаляет банк с указанным идентификатором.
   *
   * @param id идентификатор искомого банка.
   */
  @Override
  public void deleteBank(Integer id) {
    bankRepository.deleteBankById(id);
  }
}