package com.bankaccount.demo.infrastructure.adapter.inbound.web.dto;

import java.math.BigDecimal;
public record DepositRequest(
    BigDecimal amount
) {
}
