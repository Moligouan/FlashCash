package com.nnk.springboot.repositories;

import com.nnk.springboot.model.FlashCashAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlashCashAccountRepository extends JpaRepository<FlashCashAccount, Long> {

    @Query(value = "SELECT total_amount FROM flash_cash_account ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Double findLatestTotalAmount();

    List<FlashCashAccount> findAllByOrderByIdDesc();
}
