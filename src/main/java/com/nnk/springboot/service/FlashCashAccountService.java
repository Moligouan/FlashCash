package com.nnk.springboot.service;

import com.nnk.springboot.model.FlashCashAccount;
import com.nnk.springboot.repositories.FlashCashAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Service
public class FlashCashAccountService {
    private final FlashCashAccountRepository flashCashAccountRepository;

    public FlashCashAccountService(FlashCashAccountRepository flashCashAccountRepository) {
        this.flashCashAccountRepository = flashCashAccountRepository;
    }

    public List<FlashCashAccount> FlashCashInfo() {
        return flashCashAccountRepository.findAllByOrderByIdDesc();
    }
}
