package com.hackathon.demoTransactionApp.service.exception;

import com.hackathon.demoTransactionApp.service.ErrorReporter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final ErrorReporter errorReporter;

    public GlobalExceptionHandler(ErrorReporter errorReporter) {
        this.errorReporter = errorReporter;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {

        String stackTrace = getStackTrace(ex);

        // Print timestamp, exception, message, and stack trace
        System.err.println("Timestamp: " + Instant.now());
        System.err.println("Exception: " + ex.getClass().getName());
        System.err.println("Message: " + ex.getMessage());
        System.out.println("stackTrace: ");
        System.out.println(stackTrace);
        System.out.println("=======================");
        errorReporter.report(ex,"Transaction App","Path");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("internal server error");


    }

    private String getStackTrace(Exception ex){
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        ex.printStackTrace(printWriter);
        return stringWriter.toString();
    }
}
