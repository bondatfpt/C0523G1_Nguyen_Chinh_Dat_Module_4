package com.codegym.repository;

import com.codegym.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository implements ICustomerRepository{
    private static List<Customer> customerList = new ArrayList<>();
    static {
        customerList.add(new Customer(1,"dat1","dat1@gmail.com","QT"));
        customerList.add(new Customer(2,"dat2","dat2@gmail.com","QT"));
        customerList.add(new Customer(3,"dat3","dat3@gmail.com","QT"));
        customerList.add(new Customer(4,"dat4","dat4@gmail.com","QT"));
        customerList.add(new Customer(5,"dat5","dat5@gmail.com","QT"));
    }
    @Override
    public List<Customer> getAll() {
        return customerList;
    }
}
