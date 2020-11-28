package com.example.demo.enums;

public enum Status {
     PENDING("PENDING"),
    CANCELED("CANCELED"),
    APPENDED("APPENDED");

    Status(String status) {
        this.status = status;
    }
    String status;
}
