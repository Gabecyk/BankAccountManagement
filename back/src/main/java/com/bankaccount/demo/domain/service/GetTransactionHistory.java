package com.bankaccount.demo.domain.service;

import java.util.List;

import com.bankaccount.demo.domain.model.Account;
import com.bankaccount.demo.domain.model.Transaction;
import com.bankaccount.demo.domain.port.inbound.GetAccountByCpfUseCase;
import com.bankaccount.demo.domain.port.inbound.GetTransactionHistoryUseCase;
import com.bankaccount.demo.domain.port.outbound.TransactionRepositoryPort;

public class GetTransactionHistory implements GetTransactionHistoryUseCase {
    private final TransactionRepositoryPort transactionRepository;
    private final GetAccountByCpfUseCase getAccountByCpfUseCase;

    public GetTransactionHistory(
            TransactionRepositoryPort transactionRepository,
            GetAccountByCpfUseCase getAccountByCpfUseCase) {
        this.transactionRepository = transactionRepository;
        this.getAccountByCpfUseCase = getAccountByCpfUseCase;
    }

    @Override
    public List<Transaction> getHistory(String cpf) {
        Account account = getAccountByCpfUseCase.findByCpf(cpf);
        return transactionRepository.getTransactionsHistory(account.getId());
    }
}
