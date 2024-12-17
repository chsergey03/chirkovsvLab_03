package tech.reliab.course.chirkovsv.bank.controller.impl;

import lombok.RequiredArgsConstructor;
import tech.reliab.course.chirkovsv.bank.controller.UserController;

import tech.reliab.course.chirkovsv.bank.model.dto.UserDto;

import tech.reliab.course.chirkovsv.bank.model.request.UserRequest;

import tech.reliab.course.chirkovsv.bank.service.UserService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserControllerImpl implements UserController {
  private final UserService userService;

  @PostMapping
  @Override
  public ResponseEntity<UserDto> createUser(UserRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(
        new UserDto(userService.createUser(request))
    );
  }

  @GetMapping
  @Override
  public ResponseEntity<List<UserDto>> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers().stream().map(UserDto::new).toList());
  }

  @GetMapping("/{id}")
  @Override
  public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
    return ResponseEntity.ok(new UserDto(userService.getUserById(id)));
  }

  @PatchMapping("/{id}")
  @Override
  public ResponseEntity<UserDto> updateUser(
      @PathVariable Integer id,
      @RequestParam(name = "newUserFullName") String newUserFullName
  ) {
    userService.updateUser(id, newUserFullName);

    return ResponseEntity.ok(new UserDto(userService.getUserById(id)));
  }

  @DeleteMapping("/{id}")
  @Override
  public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
    userService.deleteUser(id);

    return ResponseEntity.noContent().build();
  }
}
