package com.nnk.springboot.repositories;

import com.nnk.springboot.model.Link;
import com.nnk.springboot.model.User;
import com.nnk.springboot.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<Link, Integer> {
    @Query(value="SELECT u FROM UserAccount u WHERE u.accountId=:accountId")
    public Optional<UserAccount> findUserById(Integer accountId);
}
