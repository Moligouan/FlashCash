package com.nnk.springboot.repositories;

import com.nnk.springboot.model.Link;
import com.nnk.springboot.model.User;
import com.nnk.springboot.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
    @Query("SELECT COUNT(l) > 0 FROM Link l WHERE l.user1.id = :user1Id AND l.user2.id = :user2Id")
    boolean existsByUsers(@Param("user1Id") Integer user1Id, @Param("user2Id") Integer user2Id);
}
