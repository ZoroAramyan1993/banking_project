package com.example.demo.entity;


import com.example.demo.enums.AccountType;
import com.example.demo.enums.Currencies;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.*;


import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Data

@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartId", nullable = false)
    Integer cartId;

    @NotNull
    @Column(unique = true)
    private Integer cartNumber;

    @NotNull
    @Column(unique = true)
    private Integer secretNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Currencies currencies;


    Integer balance;


    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    User user;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "card")
    List<Transaction> list;


    public Card() {
    }

    public Card(Integer cartId, Integer cartNumber, Integer secretNumber, Integer balance) {
        this.cartId = cartId;
        this.cartNumber = cartNumber;
        this.secretNumber = secretNumber;
        this.balance = balance;
    }

    public Card(Integer cartNumber, Integer secretNumber,
                Integer balance, AccountType accountType, Currencies currencies) {
        this.cartNumber = cartNumber;
        this.secretNumber = secretNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.currencies = currencies;
    }

}
