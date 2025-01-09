package com.nnk.springboot.repositories;

import com.nnk.springboot.model.Link;
import com.nnk.springboot.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer>, CrudRepository<Transfer, Integer> {

    @Query("SELECT t FROM Transfer t WHERE t.from.id = :userId OR t.to.id = :userId ORDER BY t.date DESC")
    List<Transfer> findTransferByUserId(@Param("userId") Integer userId);
}
