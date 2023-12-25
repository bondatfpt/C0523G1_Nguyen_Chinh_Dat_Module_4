package com.example.cs4.customer.repository;

import com.example.cs4.customer.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ICustomerRepository extends JpaRepository<Customer,Integer> {
    @Query(value = "select * from customers  where name like :searchName and is_deleted = 0", nativeQuery = true)
    Page<Customer> findAllByFullNameContaining(@Param("searchName") String searchName, Pageable pageable);

    Customer findCustomerByPhoneNumber(String phoneNumber);
    Customer findCustomerByEmail (String email);
    @Query(value = "select customers.* from customers join app_accounts on app_accounts.account_id = customers.account_id" +
            " where customers.is_deleted = 0 and app_accounts.username = :username",nativeQuery = true)
    Customer findCustomerByUsername (@Param("username") String username);

    Customer findCustomerByAccount_AccountId(Integer accountId);

}
