package com.hackathon.demoTransactionApp.dto;

public record ErrorRequest (
        String applicationName,
    String requestPath,
    String exceptionType,
    String message,
    String stackTrace
) {
}
