package com.nnk.springboot.repositories;

import com.nnk.springboot.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserAccountRepository extends JpaRepository<Link, Integer> {

}
