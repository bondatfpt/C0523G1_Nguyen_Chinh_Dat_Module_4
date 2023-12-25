package com.example.cs4.customer.service;

import com.example.cs4.customer.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface ICustomerService {


    void delete(int id);
    Customer findById(int id);

    void save(Customer customer);

    Page<Customer> showList(Pageable pageable, String searchName);

    Customer findCustomerByPhoneNumber(String phoneNumber);
    Customer findCustomerByEmail (String email);

    Customer findCustomerByUsername (String username);
    Customer findCustomerByAccount_AccountId(Integer accountId);

}
