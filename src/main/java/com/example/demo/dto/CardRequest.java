package com.example.demo.dto;

import com.example.demo.enums.AccountType;
import com.example.demo.enums.Currencies;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
public class CardRequest {

    @NotNull
    private Integer cartNumber;

    @NotNull
    private Integer secretNumber;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    private Currencies currencies;

    Integer balance;

//    public Integer getCartNumber() {
//        return cartNumber;
//    }
//
//    public void setCartNumber(Integer cartNumber) {
//        this.cartNumber = cartNumber;
//    }
//
//    public Integer getSecretNumber() {
//        return secretNumber;
//    }
//
//    public void setSecretNumber(Integer secretNumber) {
//        this.secretNumber = secretNumber;
//    }
//
//    public AccountType getAccountType() {
//        return accountType;
//    }
//
//    public void setAccountType(AccountType accountType) {
//        this.accountType = accountType;
//    }
//
//    public Currencies getCurrencies() {
//        return currencies;
//    }
//
//    public void setCurrencies(Currencies currencies) {
//        this.currencies = currencies;
//    }
//
//    public Integer getBalance() {
//        return balance;
//    }
//
//    public void setBalance(Integer balance) {
//        this.balance = balance;
//    }
}
