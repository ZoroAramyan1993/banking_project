package com.example.demo.entity;


import com.example.demo.chronology.CreatedChronology;
import com.example.demo.enums.AccountType;
import com.example.demo.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;


import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Data

@Entity
@Table(name = "transaction")
public class Transaction extends CreatedChronology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactionId", unique = true, nullable = false)
    private Integer transactionId;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Integer money;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "card_id")
    private Card card;


    public Transaction() {
    }

    public Transaction(Integer transactionId, Integer money, Card card) {
        this.transactionId = transactionId;
        this.money = money;
        this.card = card;
    }

}
