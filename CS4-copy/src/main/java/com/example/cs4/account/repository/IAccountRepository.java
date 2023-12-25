package com.example.cs4.account.repository;

import com.example.cs4.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface IAccountRepository extends JpaRepository<Account, Integer>{
    Account findAccountByUserName(String username);
    @Transactional
    @Modifying
    @Query(value = "update app_accounts set is_deleted = 1 where account_id =:accountId",nativeQuery = true)
    void softDeleteAccount (@Param("accountId") Integer accountId);
    @Transactional
    @Modifying
    @Query(value = "update customers set is_deleted = 1 where account_id =:accountId",nativeQuery = true)
    void softDeleteCustomer (@Param("accountId") Integer accountId);

    @Transactional
    @Modifying
    @Query(value = "update app_accounts set is_deleted = 0 where account_id =:accountId",nativeQuery = true)
    void activeAccount (@Param("accountId") Integer accountId);

    @Transactional
    @Modifying
    @Query(value = "update customers set is_deleted = 0 where account_id =:accountId",nativeQuery = true)
    void activeCustomer (@Param("accountId") Integer accountId);

    @Transactional
    @Modifying
    @Query(value = "update app_accounts set password = :password  where account_id =:accountId",nativeQuery = true)
    void updatePassword (@Param("password") String password,@Param("accountId") Integer accountId);
}
