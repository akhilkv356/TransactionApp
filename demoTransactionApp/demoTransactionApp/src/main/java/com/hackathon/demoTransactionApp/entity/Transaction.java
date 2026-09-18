package com.hackathon.demoTransactionApp.entity;




public class Transaction {
    private Long id;
    private String details;

    public Transaction(Long id, String details){
        this.id = id;
        this.details = details;
    }
    public Long getId(){
        return id;
    }
    public String getDetails(){
        return details;
    }

}
