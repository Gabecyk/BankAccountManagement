package com.bankaccount.demo.infrastructure.adapter.inbound.web.dto;

public record TransactionRequest(
    String senderCpf, 
    String receiverCpf, 
    double amount, 
    String transactionType) {
}
