package com.example.cs4.account.service;

import com.example.cs4.account.model.Account;
import com.example.cs4.customer.model.Customer;

public interface IAccountService {
    boolean save (Account account);
    Account findAccountByUserName(String username);
    Account findAccountById(Integer id);

    void softDeleteAccount (Integer accountId);

    void activeAccount (Integer accountId);
    void sendEmail (String email, String randomNumber);
    void sendEmailGetAccount (String email, String randomNumber);
    void updatePassword (String password,Integer accountId);

}
