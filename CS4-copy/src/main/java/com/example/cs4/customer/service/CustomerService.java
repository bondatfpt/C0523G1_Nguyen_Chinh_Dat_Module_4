package com.example.cs4.customer.service;

import com.example.cs4.customer.model.Customer;
import com.example.cs4.customer.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository iCustomerRepository;


    @Override
    public void delete(int id) {
        Customer customer = findById(id);
        customer.setDeleted(true);
        iCustomerRepository.save(customer);
    }

    @Override
    public Customer findById(int id) {
        Customer customer = iCustomerRepository.findById(id).get();
        return customer;
    }

    @Override
    public void save(Customer customer) {
        iCustomerRepository.save(customer);
    }

    @Override
    public Page<Customer> showList(Pageable pageable, String searchName) {
        Page<Customer> page = iCustomerRepository.findAllByFullNameContaining("%" + searchName + "%", pageable);
        return page;
    }

    @Override
    public Customer findCustomerByPhoneNumber(String phoneNumber) {
        Customer customer = new Customer();
        try {
            customer =  iCustomerRepository.findCustomerByPhoneNumber(phoneNumber);
        }catch (Exception e){
            return null;
        }
        return customer ;
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        return iCustomerRepository.findCustomerByEmail(email);
    }

    @Override
    public Customer findCustomerByUsername(String username) {
        return iCustomerRepository.findCustomerByUsername(username);
    }

    @Override
    public Customer findCustomerByAccount_AccountId(Integer accountId) {
        return iCustomerRepository.findCustomerByAccount_AccountId(accountId);
    }
}
