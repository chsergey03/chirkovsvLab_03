package tech.reliab.course.chirkovsv.bank.service.impl;

import tech.reliab.course.chirkovsv.bank.entity.User;

import tech.reliab.course.chirkovsv.bank.model.dto.UserDto;

import tech.reliab.course.chirkovsv.bank.model.request.UserRequest;

import tech.reliab.course.chirkovsv.bank.repository.UserRepository;

import tech.reliab.course.chirkovsv.bank.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  /**
   * Создаёт пользователя онлайн-банка.
   *
   * @param request данные, необходимые для создания пользователя.
   * @return созданный пользователь онлайн-банка.
   */
  @Override
  public User createUser(UserRequest request) {
    User userToCreate = new User();

    userToCreate.setFullName(request.getFullName());
    userToCreate.setBirthDate(request.getBirthDate());
    userToCreate.setWorkplace(request.getWorkplace());
    userToCreate.setMonthlyIncome(request.getMonthlyIncome());
    userToCreate.setCreditRating(getCalculatedCreditRating(request.getMonthlyIncome()));

    return userRepository.save(userToCreate);
  }

  /**
   * Возвращает вычисленное значение кредитного рейтинга
   * пользователя.
   *
   * @param monthlyIncome размер месячного дохода пользователя.
   * @return вычисленное значение кредитного рейтинга
   * пользователя.
   */
  private Long getCalculatedCreditRating(
      Long monthlyIncome
  ) {
    long creditRating = 0L;
    long creditRatingValue = 100L;

    for (long currentBound = 0L; currentBound < monthlyIncome; currentBound += 100000L) {
      creditRating += creditRatingValue;
    }

    return creditRating;
  }

  /**
   * Возвращает список всех пользователей онлайн-банка.
   *
   * @return список всех пользователей онлайн-банка.
   */
  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  /**
   * Возвращает пользователя онлайн-банка с указанным
   * идентификатором, если он существует.
   *
   * @param id идентификатор искомого пользователя.
   * @return пользователь онлайн-банка с указанным
   * идентификатором, если он существует
   */
  @Override
  public User getUserById(Integer id) {
    return userRepository.findById(id)
        .orElseThrow(
            () -> new NoSuchElementException("user was not found")
        );
  }

  /**
   * Изменяет пользователя онлайн-банка с указанным идентификатором.
   *
   * @param id          идентификатор искомого пользователя.
   * @param newFullName новое Ф.И.О. пользователя.
   */
  @Override
  public void updateUser(Integer id, String newFullName) {
    User userToUpdate = getUserById(id);

    userToUpdate.setFullName(newFullName);

    userRepository.save(userToUpdate);
  }

  /**
   * Удаляет пользователя онлайн-банка с
   * указанным идентификатором.
   *
   * @param id идентификатор искомого
   *           пользователя.
   */
  @Override
  public void deleteUser(Integer id) {
    userRepository.deleteById(id);
  }
}
