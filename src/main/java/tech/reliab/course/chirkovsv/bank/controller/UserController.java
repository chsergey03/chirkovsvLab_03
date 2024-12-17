package tech.reliab.course.chirkovsv.bank.controller;

import tech.reliab.course.chirkovsv.bank.model.dto.UserDto;

import tech.reliab.course.chirkovsv.bank.model.request.UserRequest;

import java.util.List;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserController {
  ResponseEntity<UserDto> createUser(UserRequest request);

  ResponseEntity<List<UserDto>> getAllUsers();

  ResponseEntity<UserDto> getUserById(@PathVariable Integer id);

  ResponseEntity<UserDto> updateUser(
      @PathVariable Integer id,
      @RequestParam(name = "newUserFullName") String newUserFullName
  );

  ResponseEntity<Void> deleteUser(@PathVariable Integer id);
}
