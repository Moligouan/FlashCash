package com.nnk.springboot.repositories;

import com.nnk.springboot.model.Link;
import com.nnk.springboot.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long>, CrudRepository<Transfer, Long> {
}
