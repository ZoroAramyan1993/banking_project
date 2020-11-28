package com.example.demo.enums;

public enum Currencies {
    US("US"),
    AMD("AMD"),
    EUR("EUR");


    Currencies(String currency){
        this.currency = currency;
    }
    String currency;
}
