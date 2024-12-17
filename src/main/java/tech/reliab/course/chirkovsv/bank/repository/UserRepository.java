package tech.reliab.course.chirkovsv.bank.repository;

import tech.reliab.course.chirkovsv.bank.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findUserById(Integer id);

  void deleteUserById(Integer id);
}