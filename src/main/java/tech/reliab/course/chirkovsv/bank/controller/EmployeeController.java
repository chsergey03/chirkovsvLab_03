package tech.reliab.course.chirkovsv.bank.controller;

import tech.reliab.course.chirkovsv.bank.model.dto.EmployeeDto;

import tech.reliab.course.chirkovsv.bank.model.request.EmployeeRequest;

import java.util.List;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface EmployeeController {
  ResponseEntity<EmployeeDto> createEmployee(EmployeeRequest request);

  ResponseEntity<List<EmployeeDto>> getAllEmployees();

  ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Integer id);

  ResponseEntity<EmployeeDto> updateEmployee(
      @PathVariable Integer id,
      @RequestParam(name = "newEmployeeFullName") String newEmployeeFullName
  );

  ResponseEntity<Void> deleteEmployee(@PathVariable Integer id);
}
