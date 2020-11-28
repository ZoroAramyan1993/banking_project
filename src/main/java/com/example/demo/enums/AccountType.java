package com.example.demo.enums;

public enum AccountType {
    DEPOSIT("DEPOSIT"),
    WITHDRAWAL("WITHDRAWAL");

    AccountType(String accountType) {
        this.accountType = accountType;
    }
    String accountType;
}
