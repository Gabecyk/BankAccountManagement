package com.bankaccount.demo.domain.port.inbound;

public interface TransactionUseCase {
    void transfer(String senderCpf, String receiverCpf, double amount, String transactionType);
}
