package tech.reliab.course.chirkovsv.bank.service;

import tech.reliab.course.chirkovsv.bank.entity.User;

import tech.reliab.course.chirkovsv.bank.model.request.UserRequest;

import java.util.List;

public interface UserService {
  User createUser(UserRequest request);

  List<User> getAllUsers();

  User getUserById(Integer id);

  void updateUser(Integer id, final String newFullName);

  void deleteUser(Integer id);
}