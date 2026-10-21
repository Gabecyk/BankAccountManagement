package com.bankaccount.demo.domain.service;

import java.math.BigDecimal;

import com.bankaccount.demo.domain.model.Account;
import com.bankaccount.demo.domain.model.Transaction;
import com.bankaccount.demo.domain.port.inbound.GetAccountByCpfUseCase;
import com.bankaccount.demo.domain.port.inbound.SaveAccountUseCase;
import com.bankaccount.demo.domain.port.inbound.TransactionUseCase;
import com.bankaccount.demo.domain.port.outbound.TransactionRepositoryPort;

public class TransactionService implements TransactionUseCase {

    private final TransactionRepositoryPort transactionRepositoryPort;
    private final GetAccountByCpfUseCase getAccountByCpfUseCase;
    private final SaveAccountUseCase saveAccountUseCase;

    public TransactionService(TransactionRepositoryPort transactionRepositoryPort, GetAccountByCpfUseCase getAccountByCpfUseCase, SaveAccountUseCase saveAccountUseCase) {
        this.transactionRepositoryPort = transactionRepositoryPort;
        this.getAccountByCpfUseCase = getAccountByCpfUseCase;
        this.saveAccountUseCase = saveAccountUseCase;
    }

    @Override
    public void transfer(String senderCpf, String receiverCpf, double amount, String transactionType) {
        Account senderAccount = getAccountByCpfUseCase.findByCpf(senderCpf);
        Account receiverAccount = getAccountByCpfUseCase.findByCpf(receiverCpf);
        if (senderAccount == null) {
            throw new IllegalArgumentException("Sender account not found");
        }
        if (receiverAccount == null) {
            throw new IllegalArgumentException("Receiver account not found");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be greater than zero");
        }


        if (senderAccount.getBalance().doubleValue() < amount) {
            throw new IllegalArgumentException("Insufficient balance in sender account");
        }

        senderAccount.transfer(BigDecimal.valueOf(amount));
        receiverAccount.deposit(BigDecimal.valueOf(amount));

        saveAccountUseCase.saveAccount(senderAccount);
        saveAccountUseCase.saveAccount(receiverAccount);

        Transaction transaction = new Transaction(
            senderAccount,
            receiverAccount,
            BigDecimal.valueOf(amount),
            transactionType
        );
        transactionRepositoryPort.save(transaction);
    }
    
}
