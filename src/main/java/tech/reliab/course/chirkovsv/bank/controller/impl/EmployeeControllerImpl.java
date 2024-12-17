package tech.reliab.course.chirkovsv.bank.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import tech.reliab.course.chirkovsv.bank.controller.EmployeeController;

import tech.reliab.course.chirkovsv.bank.model.dto.EmployeeDto;

import tech.reliab.course.chirkovsv.bank.model.request.EmployeeRequest;

import tech.reliab.course.chirkovsv.bank.service.EmployeeService;

import java.util.List;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeControllerImpl implements EmployeeController {
  private final EmployeeService employeeService;

  @PostMapping
  @Override
  public ResponseEntity<EmployeeDto> createEmployee(EmployeeRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(
        new EmployeeDto(employeeService.createEmployee(request))
    );
  }

  @GetMapping
  @Override
  public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
    return ResponseEntity.ok(employeeService.getAllEmployees().stream().map(EmployeeDto::new).toList());
  }

  @GetMapping("/{id}")
  @Override
  public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Integer id) {
    return ResponseEntity.ok(new EmployeeDto(employeeService.getEmployeeById(id)));
  }

  @PatchMapping("/{id}")
  @Override
  public ResponseEntity<EmployeeDto> updateEmployee(
      @PathVariable Integer id,
      @RequestParam(name = "newEmployeeFullName") String newEmployeeFullName
  ) {
    employeeService.updateEmployee(id, newEmployeeFullName);

    return ResponseEntity.ok(new EmployeeDto(employeeService.getEmployeeById(id)));
  }

  @DeleteMapping("/{id}")
  @Override
  public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
    employeeService.deleteEmployee(id);

    return ResponseEntity.noContent().build();
  }
}
