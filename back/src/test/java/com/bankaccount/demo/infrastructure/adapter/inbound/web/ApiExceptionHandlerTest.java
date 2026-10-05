package com.bankaccount.demo.infrastructure.adapter.inbound.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;

class ApiExceptionHandlerTest {

    private final ApiExceptionHandler exceptionHandler = new ApiExceptionHandler();

    @Test
    void notFoundResponseIncludesExceptionMessageAndRequestPath() {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/api/accounts/cpf/123");

        var response = exceptionHandler.handleNotFound(
                new NoSuchElementException("Account not found"),
                request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Account not found", response.getBody().message());
        assertEquals("/api/accounts/cpf/123", response.getBody().path());
    }

    @Test
    void badRequestResponseIncludesExceptionMessage() {
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/api/accounts/deposit");

        var response = exceptionHandler.handleBadRequest(
                new IllegalArgumentException("Deposit amount must be greater than zero"),
                request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Deposit amount must be greater than zero", response.getBody().message());
    }
}