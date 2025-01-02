package com.nnk.springboot.repositories;

import com.nnk.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, CrudRepository<User, Long> {
    @Query(value="SELECT u FROM User u LEFT JOIN FETCH u.links WHERE u.email=:email")
    public Optional<User> findUserByMail(String email);

    @Query(value="SELECT u FROM User u LEFT JOIN FETCH u.links WHERE u.id=:id")
    public Optional<User> findUserById(Integer id);
}
