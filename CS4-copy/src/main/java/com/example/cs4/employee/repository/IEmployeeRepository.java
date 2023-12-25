package com.example.cs4.employee.repository;


import com.example.cs4.employee.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = "select * from employees where name like :searchName and is_deleted = 0",nativeQuery = true)
    Page<Employee> findAllByFullNameContaining(@Param("searchName") String searchName, Pageable pageable);
    @Query(value = "select employees.* from employees \n" +
            "join app_accounts on employees.account_id = app_accounts.account_id\n" +
            "where app_accounts.username =:userName",
            nativeQuery = true)
    Employee findByUserName(@Param("userName") String userName);
}
