package tech.reliab.course.chirkovsv.bank.service;

import tech.reliab.course.chirkovsv.bank.entity.Employee;

import tech.reliab.course.chirkovsv.bank.model.request.EmployeeRequest;

import java.util.List;

public interface EmployeeService {
  Employee createEmployee(EmployeeRequest request);

  List<Employee> getAllEmployees();

  Employee getEmployeeById(Integer id);

  void updateEmployee(Integer id, String newFullName);

  void addOffice(Integer employeeId, Integer officeId);

  void removeOffice(Integer id);

  void deleteEmployee(Integer id);
}