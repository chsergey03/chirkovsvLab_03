package tech.reliab.course.chirkovsv.bank.service.impl;

import tech.reliab.course.chirkovsv.bank.entity.Bank;
import tech.reliab.course.chirkovsv.bank.entity.PaymentAccount;
import tech.reliab.course.chirkovsv.bank.entity.User;

import tech.reliab.course.chirkovsv.bank.model.dto.PaymentAccountDto;

import tech.reliab.course.chirkovsv.bank.model.request.PaymentAccountRequest;

import tech.reliab.course.chirkovsv.bank.repository.PaymentAccountRepository;

import tech.reliab.course.chirkovsv.bank.service.PaymentAccountService;
import tech.reliab.course.chirkovsv.bank.service.BankService;
import tech.reliab.course.chirkovsv.bank.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentAccountServiceImpl implements PaymentAccountService {
  private final PaymentAccountRepository paymentAccountRepository;

  private final BankService bankService;
  private final UserService userService;

  /**
   * Создаёт платёжный счёт.
   *
   * @param request данные, необходимые для создания платёжного счёта.
   * @return созданный платёжный счёт.
   */
  @Override
  public PaymentAccount createPaymentAccount(PaymentAccountRequest request) {
    Bank bank = bankService.getBankById(request.getBankId());

    User user = userService.getUserById(request.getUserId());

    PaymentAccount paymentAccountToCreate = new PaymentAccount();

    paymentAccountToCreate.setBank(bank);
    paymentAccountToCreate.setUser(user);
    paymentAccountToCreate.setMoneyAmount(request.getMoneyAmount());

    return paymentAccountRepository.save(paymentAccountToCreate);
  }

  /**
   * Возвращает список с DTO всех платёжных счетов.
   *
   * @return список с DTO всех платёжных счетов.
   */
  @Override
  public List<PaymentAccount> getAllPaymentAccounts() {
    return paymentAccountRepository.findAll();
  }

  /**
   * Возвращает платёжный счёт с указанным идентификатором,
   * если он существует.
   *
   * @param id идентификатор искомого платёжного счёта.
   * @return платёжный счёт с указанным идентификатором, если
   * он существует.
   */
  @Override
  public PaymentAccount getPaymentAccountById(Integer id) {
    return paymentAccountRepository.findPaymentAccountById(id)
        .orElseThrow(() -> new NoSuchElementException(
                "payment account was not found"
            )
        );
  }

  /**
   * Изменяет платёжный аккаунт с указанным идентификатором
   *
   * @param paymentAccountId идентификатор искомого платёжного аккаунта.
   * @param newUserId        идентификатор нового пользователя онлайн-банка,
   *                         на которого оформлен платёжный счёт.
   */
  @Override
  public void updatePaymentAccount(Integer paymentAccountId, Integer newUserId) {
    PaymentAccount paymentAccountToUpdate = getPaymentAccountById(paymentAccountId);

    User newUser = userService.getUserById(newUserId);

    if (newUser != null) {
      if (newUser != paymentAccountToUpdate.getUser()) {
        paymentAccountToUpdate.getUser().getPaymentAccounts().remove(paymentAccountToUpdate);

        paymentAccountToUpdate.setUser(newUser);

        newUser.getPaymentAccounts().add(paymentAccountToUpdate);
      } else {
        throw new IllegalArgumentException(
            "new user must not be the same"
        );
      }
    }

    paymentAccountRepository.save(paymentAccountToUpdate);
  }

  /**
   * Удаляет платёжный аккаунт с указанным идентификатором.
   *
   * @param id идентификатор искомого платёжного аккаунта.
   */
  @Override
  public void deletePaymentAccount(Integer id) {
    paymentAccountRepository.deletePaymentAccountById(id);
  }
}