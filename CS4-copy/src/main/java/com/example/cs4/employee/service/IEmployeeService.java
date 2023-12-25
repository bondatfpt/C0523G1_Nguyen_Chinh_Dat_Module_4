package com.example.cs4.employee.service;

import com.example.cs4.employee.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEmployeeService {
    Page<Employee> showList(Pageable pageable, String searchName);

    void save(Employee employee);

    void delete(int id);
    Employee findById(int id);
    Employee findByUserName(String userName);
}
