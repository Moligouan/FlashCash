package com.nnk.springboot.service;

import com.nnk.springboot.model.Transfer;
import com.nnk.springboot.model.User;
import com.nnk.springboot.model.UserAccount;
import com.nnk.springboot.repositories.TransferRepository;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.form.DepotForm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransferService {
    private final UserRepository userRepository;
    private final TransferRepository transferRepository;

    public TransferService(UserRepository userRepository, TransferRepository transferRepository) {
        this.userRepository = userRepository;
        this.transferRepository = transferRepository;
    }

    public void registerTransfer(DepotForm form, User user, Integer targetId) {
        User target = userRepository.findUserById(targetId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + targetId));
        Transfer transfer = new Transfer();
        transfer.setDate(LocalDateTime.now());
        transfer.setFrom(user);
        transfer.setTo(target);
        transfer.setAmountBeforeFee(form.getAmount());
        transfer.setAmountAfterFee(form.getAmount() * 995/1000);

        transferRepository.save(transfer);
    }
}
