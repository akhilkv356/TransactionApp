package com.hackathon.demoTransactionApp.service;

import com.hackathon.demoTransactionApp.dto.ErrorRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ErrorReporter {
    private final RestClient restClient;

    public ErrorReporter() {
        this.restClient = RestClient.builder().baseUrl("http://localhost:8081").build();
    }

    public void report(Exception ex,String appName,String path){
        ErrorRequest request = new ErrorRequest("Transaction App","/Transactions",ex.getClass().getName(), ex.getMessage(), getStackTrace(ex));

        try {
            restClient.post().uri("/api/incidents").body(request).retrieve().toBodilessEntity();
            System.out.println("Error reported successfully");
        } catch (Exception e) {
            System.err.println("Failed to report error: " + e.getMessage());
        }
    }

    public String getStackTrace(Exception ex){
        StringBuilder stackTrace = new StringBuilder();
        stackTrace.append(ex.toString()).append("\n");
        for (StackTraceElement element : ex.getStackTrace()) {
            stackTrace.append("\tat ").append(element.toString()).append("\n");
        }
        return stackTrace.toString();
    }
}
