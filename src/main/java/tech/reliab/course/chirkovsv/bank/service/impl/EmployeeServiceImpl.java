package tech.reliab.course.chirkovsv.bank.service.impl;

import tech.reliab.course.chirkovsv.bank.entity.*;

import tech.reliab.course.chirkovsv.bank.model.request.EmployeeRequest;

import tech.reliab.course.chirkovsv.bank.repository.EmployeeRepository;

import tech.reliab.course.chirkovsv.bank.service.*;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
  private final EmployeeRepository employeeRepository;

  private final BankService bankService;
  private final BankOfficeService officeService;

  /**
   * создаёт работник банка.
   *
   * @param request данные, необходимые для создания работника.
   * @return создаваемый работник банка.
   */
  @Override
  public Employee createEmployee(EmployeeRequest request) {
    Bank bank = bankService.getBankById(request.getBankId());

    BankOffice office = null;

    if (request.getOfficeId() != null) {
      office = officeService.getOfficeById(request.getOfficeId());

      if (!office.getBank().equals(bank)) {
        throw new IllegalArgumentException(
            "bank of office and bank of employee are not the same"
        );
      }
    }

    Employee employeeToCreate = getEmployeeToCreate(request, office, bank);

    return employeeRepository.save(employeeToCreate);
  }

  /**
   * возвращает объект работника для его создания.
   *
   * @param request данные, необходимые для создания работника;
   * @param office  офис, в котором работает сотрудник;
   * @param bank    банк работника.
   * @return объект работника для его создания.
   */
  private Employee getEmployeeToCreate(
      EmployeeRequest request,
      BankOffice office,
      Bank bank) {
    Employee employeeToCreate = new Employee();

    employeeToCreate.setFullName(request.getFullName());
    employeeToCreate.setBirthDate(request.getBirthDate());
    employeeToCreate.setPost(request.getPost());
    employeeToCreate.setWorkInTheOffice(office != null);

    if (office != null) {
      employeeToCreate.setAbilityToRegisterCredits(office.isCreditRegistrationAvailable());
    } else {
      employeeToCreate.setAbilityToRegisterCredits(false);
    }

    employeeToCreate.setSalary(request.getSalary());

    employeeToCreate.setBank(bank);
    employeeToCreate.setOffice(office);

    return employeeToCreate;
  }

  /**
   * Возвращает список со всеми работниками банков.
   *
   * @return список со всеми работниками банков.
   */
  @Override
  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }

  /**
   * Возвращает работника банка с указанным идентификатором,
   * если он существует.
   *
   * @param id идентификатор искомого работника банка.
   * @return работник банка с указанным идентификатором, если
   * он существует.
   */
  @Override
  public Employee getEmployeeById(Integer id) {
    return employeeRepository.findEmployeeById(id)
        .orElseThrow(
            () -> new NoSuchElementException("employee was not found")
        );
  }

  /**
   * Изменяет работника банка с указанным идентификатором.
   *
   * @param id          идентификатор искомого работника;
   * @param newFullName новое Ф.И.О. работника.
   */
  @Override
  public void updateEmployee(Integer id, String newFullName) {
    Employee employeeToUpdate = getEmployeeById(id);

    employeeToUpdate.setFullName(newFullName);

    employeeRepository.save(employeeToUpdate);
  }

  /**
   * Добавляет работнику банка офис, в котором он
   * работает.
   *
   * @param employeeId идентификатор работника;
   * @param officeId   идентификатор офиса.
   */
  @Override
  public void addOffice(
      Integer employeeId,
      Integer officeId) {
    Employee employeeToEdit = getEmployeeById(employeeId);

    if (employeeToEdit.getOffice() != null) {
      throw new IllegalStateException(
          "employee already has an office"
      );
    }

    BankOffice officeToAdd = officeService.getOfficeById(officeId);

    employeeToEdit.setOffice(officeToAdd);
    employeeToEdit.setWorkInTheOffice(true);

    employeeRepository.save(employeeToEdit);
  }


  @Override
  public void removeOffice(Integer id) {
    Employee employeeToEdit = getEmployeeById(id);

    if (employeeToEdit.getOffice() == null) {
      throw new IllegalStateException(
          "employee already hasn't an office"
      );
    }

    employeeToEdit.setOffice(null);
    employeeToEdit.setWorkInTheOffice(false);

    employeeRepository.save(employeeToEdit);
  }

  /**
   * Меняет значение флага, определяющего, работает ли работник
   * в офисе или нет, на противоположный.
   *
   * @param id идентификатор искомого работника.
   */
  private void switchDoesWorkInTheOffice(Integer id) {
    Employee employeeToEdit = getEmployeeById(id);

    employeeToEdit.setWorkInTheOffice(!employeeToEdit.isWorkInTheOffice());

    employeeRepository.save(employeeToEdit);
  }

  /**
   * Меняет значение флага, определяющего, имеет ли работник право
   * выдавать кредиты или нет, на противоположный.
   *
   * @param id идентификатор искомого работника.
   */
  private void switchHasAbilityToRegisterCredits(Integer id) {
    Employee employeeToEdit = getEmployeeById(id);

    employeeToEdit.setAbilityToRegisterCredits(!employeeToEdit.isAbilityToRegisterCredits());

    employeeRepository.save(employeeToEdit);
  }

  /**
   * Удаляет работника банка с указанным идентификатором.
   *
   * @param id идентификатор искомого работника.
   */
  @Override
  public void deleteEmployee(Integer id) {
    employeeRepository.deleteEmployeeById(id);
  }
}