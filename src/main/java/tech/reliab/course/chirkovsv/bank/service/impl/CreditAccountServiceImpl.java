package tech.reliab.course.chirkovsv.bank.service.impl;

import tech.reliab.course.chirkovsv.bank.entity.*;

import tech.reliab.course.chirkovsv.bank.model.dto.CreditAccountDto;
import tech.reliab.course.chirkovsv.bank.model.request.CreditAccountRequest;

import tech.reliab.course.chirkovsv.bank.repository.CreditAccountRepository;

import tech.reliab.course.chirkovsv.bank.service.*;

import java.util.List;
import java.time.LocalDate;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditAccountServiceImpl implements CreditAccountService {
  private final CreditAccountRepository creditAccountRepository;

  private final UserService userService;
  private final EmployeeService employeeService;
  private final PaymentAccountService paymentAccountService;

  /**
   * Создаёт кредитный счёт.
   *
   * @param request данные, необходимые для создания кредитного счёта.
   * @return созданный кредитный счёт.
   */
  @Override
  public CreditAccount createCreditAccount(CreditAccountRequest request) {
    Employee employeeProvidedCredit = employeeService.getEmployeeById(request.getEmployeeProvidedCreditId());

    if (!employeeProvidedCredit.isAbilityToRegisterCredits()) {
      throw new IllegalArgumentException(
          "employee does not have ability to register credits"
      );
    }

    Bank bank = employeeProvidedCredit.getBank();

    User user = userService.getUserById(request.getUserId());

    PaymentAccount paymentAccount = paymentAccountService.getPaymentAccountById(request.getPaymentAccountId());

    CreditAccount creditAccountToCreate = new CreditAccount();

    creditAccountToCreate.setStartDate(request.getStartDate());
    creditAccountToCreate.setNMonths(request.getNMonths());
    creditAccountToCreate.setEndDate(getCalculatedEndDate(request.getStartDate(), request.getNMonths()));
    creditAccountToCreate.setMoneyAmount(getChosenMoneyAmount(request.getMoneyAmount(), bank));
    creditAccountToCreate.setInterestRate(getChosenInterestRate(request.getInterestRate(), bank));

    creditAccountToCreate.setMonthlyPayment(getCalculatedMonthlyPayment(
        creditAccountToCreate.getMoneyAmount(),
        creditAccountToCreate.getNMonths(),
        creditAccountToCreate.getInterestRate()
    ));

    creditAccountToCreate.setBank(bank);
    creditAccountToCreate.setUser(user);
    creditAccountToCreate.setEmployeeProvidedCredit(employeeProvidedCredit);
    creditAccountToCreate.setPaymentAccount(paymentAccount);

    return creditAccountRepository.save(creditAccountToCreate);
  }

  /**
   * Возвращает вычисленную дату окончания
   * кредита.
   *
   * @param startDate дата начала кредита;
   * @param nMonths   количество месяцев, на
   *                  которые рассчитан кредит.
   * @return вычисленная дата окончания кредита.
   */
  private LocalDate getCalculatedEndDate(
      LocalDate startDate,
      Integer nMonths
  ) {
    return startDate.plusMonths(nMonths);
  }

  /**
   * Возвращает выбранную сумму кредита.
   *
   * @param moneyAmount запрашиваемая сумма
   *                    кредита;
   * @param bank        банк, в котором взят
   *                    кредит.
   * @return выбранная сумма кредита.
   */
  private Long getChosenMoneyAmount(
      Long moneyAmount,
      Bank bank) {
    if (moneyAmount > bank.getTotalMoneyAmount()) {
      return bank.getTotalMoneyAmount();
    } else {
      return moneyAmount;
    }
  }

  /**
   * Возвращает выбранное значение процентной
   * ставки по кредиту.
   *
   * @param interestRate запрашиваемое значение
   *                     процентной ставки;
   * @param bank         банк, в котором взят
   *                     кредит.
   * @return выбранное значение процентной ставки
   * по кредиту.
   */
  private Long getChosenInterestRate(
      Long interestRate,
      Bank bank) {
    if (interestRate > bank.getInterestRate()) {
      return bank.getInterestRate();
    } else {
      return interestRate;
    }
  }

  /**
   * Возвращает вычисленное значение ежемесячного
   * платежа по кредиту.
   *
   * @param moneyAmount  сумма кредита;
   * @param nMonths      количество месяцев, на
   *                     которые рассчитан кредит.
   * @param interestRate значение процентной ставки
   *                     по кредиту.
   * @return вычисленное значение ежемесячного
   * платежа по кредиту.
   */
  private Long getCalculatedMonthlyPayment(
      final Long moneyAmount,
      final int nMonths,
      final Long interestRate
  ) {
    double monthlyRate = interestRate / 100.0 / 12.0;

    double annuityCoefficient =
        (monthlyRate * Math.pow(1 + monthlyRate, nMonths)) /
            (Math.pow(1 + monthlyRate, nMonths) - 1);

    return Math.round(moneyAmount * annuityCoefficient);
  }

  /**
   * Возвращает список всех кредитных счетов.
   *
   * @return список всех кредитных счетов.
   */
  @Override
  public List<CreditAccount> getAllCreditAccounts() {
    return creditAccountRepository.findAll();
  }

  /**
   * Возвращает кредитный счёт с указанным идентификатором,
   * если он существует.
   *
   * @param id идентификатор искомого кредитного счёта.
   * @return кредитный счёт с указанным идентификатором,
   * если он существует.
   */
  @Override
  public CreditAccount getCreditAccountById(Integer id) {
    return creditAccountRepository.findCreditAccountById(id)
        .orElseThrow(() -> new NoSuchElementException(
            "credit account was not found")
        );
  }

  /**
   * Изменяет кредитный счёт с указанным идентификатором.
   *
   * @param creditAccountId идентификатор искомого
   *                        кредитного счёта;
   * @param newUserId       идентификатор нового
   *                        пользователя онлайн-банка,
   *                        на которого оформлен
   *                        кредитный счёт.
   */
  @Override
  public void updateCreditAccount(
      Integer creditAccountId,
      Integer newUserId
  ) {
    CreditAccount creditAccountToUpdate = getCreditAccountById(creditAccountId);

    User newUser = userService.getUserById(newUserId);

    if (newUser != null) {
      if (newUser != creditAccountToUpdate.getUser()) {
        creditAccountToUpdate.getUser().getCreditAccounts().remove(creditAccountToUpdate);

        creditAccountToUpdate.setUser(newUser);

        newUser.getCreditAccounts().add(creditAccountToUpdate);
      } else {
        throw new IllegalArgumentException(
            "new user must not be the same"
        );
      }
    }

    creditAccountRepository.save(creditAccountToUpdate);
  }

  /**
   * Удаляет кредитный счёт с указанным идентификатором.
   *
   * @param id идентификатор искомого кредитного счёта.
   */
  @Override
  public void deleteCreditAccount(Integer id) {
    creditAccountRepository.deleteCreditAccountById(id);
  }
}