package com.example.cs4.employee.service;


import com.example.cs4.employee.model.Employee;
import com.example.cs4.employee.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private IEmployeeRepository iEmployeeRepository;

    @Override
    public Page<Employee> showList(Pageable pageable, String searchName) {
        Page<Employee> employees = iEmployeeRepository.findAllByFullNameContaining("%" + searchName + "%", pageable);
        return employees;
    }

    @Override
    public void save(Employee employee) {
        iEmployeeRepository.save(employee);
    }

    @Override
    public void delete(int id) {
        Employee employee = findById(id);
        employee.setDeleted(true);
        iEmployeeRepository.save(employee);
    }

    @Override
    public Employee findById(int id) {
        return iEmployeeRepository.findById(id).get();
    }

    @Override
    public Employee findByUserName(String userName) {
        return iEmployeeRepository.findByUserName(userName);
    }
}
