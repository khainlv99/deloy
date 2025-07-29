package com.example.dncinema.repository;

import com.example.dncinema.model.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface IAccountUserRepository extends JpaRepository<AccountUser, Integer> {
    @Query(value = "select * from account_user where name_account like :name", nativeQuery = true)
    AccountUser findAccountUserByNameAccount(@Param("name") String nameAccount);
    @Query(value = "select * from account_user where name_account like :email", nativeQuery = true)
    AccountUser findAccountUserByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "insert into account_user(name_account , password_account) values (:name_account , :password_account)", nativeQuery = true)
    void createAccountUser(@Param("name_account") String nameAccount
            , @Param("password_account") String password_account);

    @Modifying
    @Transactional
    @Query(value = "update account_user set name_account = :name_account , password_account = :password_account WHERE id = :id", nativeQuery = true)
    void updateAccount(
            @Param("name_account") String nameAccount
            , @Param("password_account") String passwordAccount
            , @Param("id") Integer id);
  
    boolean existsByNameAccount(String username);
  
    @Query(value = "update account_user set password_account = :password_account WHERE id = :id", nativeQuery = true)
    void savePassword(
            @Param("password_account") String passwordAccount,
            @Param("id") Integer id);
  
    @Transactional
    @Query(value = "select * from account_user where id = :id", nativeQuery = true)
    AccountUser findAccountUserById(@Param("id") Integer id);
}
