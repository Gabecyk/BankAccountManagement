package com.bankaccount.demo.domain.port.inbound;

import java.util.List;

import com.bankaccount.demo.domain.model.Transaction;

public interface GetTransactionHistoryUseCase {
    List<Transaction> getHistory(String cpf);
}
