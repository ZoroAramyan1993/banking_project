package com.example.demo.controller;


import com.example.demo.entity.Card;
import com.example.demo.entity.Transaction;
import com.example.demo.entity.User;
import com.example.demo.enums.AccountType;
import com.example.demo.enums.Status;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CardRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TransactionService;
import com.example.demo.service.UserService;
import com.example.demo.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/transaction")
public class TransactionController {


    @Autowired
    UserService userService;

    @Autowired
    CardService cardService;

    @Autowired
    TransactionService transactionService;


    Logger logger = LoggerFactory.getLogger(TransactionController.class);

    //@Secured("Admin")
    @Transactional
    @PostMapping("/save")
    Transaction createTransaction(@RequestParam("userId") Integer userId, @RequestParam("cardId") Integer cardId,
                                  @RequestBody Transaction transaction) {

        User user = userService.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("card not found"));
        Card card = cardService.findById(cardId).
                orElseThrow(() -> new ResourceNotFoundException("not found"));
        if (transaction.getStatus() == Status.APPENDED && transaction.getAccountType() == AccountType.DEPOSIT) {
            card.setBalance(card.getBalance() + transaction.getMoney());
            transaction.setCard(card);
        }

        if (transaction.getStatus() == Status.APPENDED && transaction.getAccountType() == AccountType.WITHDRAWAL) {
            if (card.getBalance() > transaction.getMoney()) {
                card.setBalance(card.getBalance() - transaction.getMoney());
                transaction.setCard(card);
            }
        }

        user.getTransactions().add(transaction);
        transaction.setUser(user);
        card.getList().add(transaction);
        transaction.setCard(card);
        userService.save(user);
        cardService.save(card);
        return transaction;
    }

    @PatchMapping("/{transactionId}")
    ResponseEntity<Transaction> updateTransaction(@Valid @RequestBody Transaction transaction,
                                                  @RequestParam("id") Integer id,
                                                  @RequestParam("transactionId") Integer transactionId) {
        transactionService.existByUserIdAndTransactionId(id, transactionId);
        if (transaction.getStatus() == Status.APPENDED || transaction.getStatus() == Status.CANCELED) {
            logger.warn("transaction status can not be changed");
        }

        Optional<Transaction> transaction1 = transactionService.findById(transactionId);

        if (transaction1.get().getStatus() == Status.APPENDED) {
            transaction1.get().setCard(transaction.getCard());
            transaction1.get().setUser(transaction.getUser());
            Card card = transaction1.get().getCard();
            if (card.getAccountType() == AccountType.DEPOSIT) {
                card.setBalance(card.getBalance() + transaction1.get().getMoney());
                transaction1.get().setCard(card);
            }

            if (transaction1.get().getAccountType() == AccountType.WITHDRAWAL) {
                if (card.getBalance() >= transaction1.get().getMoney()) {
                    card.setBalance(card.getBalance() - transaction1.get().getMoney());
                    transaction1.get().setCard(card);
                } else {
                    throw new ResourceNotFoundException("not found");
                }
            }

        }
        transactionService.updateTransaction(transaction1.get());
        return ResponseEntity.ok(transaction1.get());
    }

    @GetMapping("/time/get")
    ResponseEntity<List<Timestamp>> getTransactionTimeByUserId(Integer userId) {
        List<Timestamp> timeById = transactionService.getTimeById(userId);
        if (timeById.size() != 0) {
            return ResponseEntity.ok(timeById);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{userId}/getAll")
    ResponseEntity<List<Transaction>> getAllTransactions(@PathVariable(value = "userId") Integer userId) {
        List<Transaction> transactions = transactionService.getTransactionByUserId(userId);
        if (transactions.size() != 0) {
            return ResponseEntity.ok(transactions);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("{cardId}/transaction")
    ResponseEntity<List<Transaction>> getAllTransactionsByCardId(@PathVariable(value = "cardId") Integer cardId) {
        List<Transaction> transactions = transactionService.getTransactionsByCardId(cardId);
        if (transactions.size() != 0) {
            return ResponseEntity.ok(transactions);
        }
        return ResponseEntity.notFound().build();
    }


    @PatchMapping("{id}/cancel")
    public ResponseEntity cancelTransaction(@PathVariable("id") Integer id) {
        Transaction transaction = transactionService.
                findById(id).orElseThrow(() -> new ResourceNotFoundException("not found"));
        if (transaction.getStatus() == Status.CANCELED) {
            logger.error("transaction canceled", transaction);
        }

        if (transaction.getStatus() == Status.APPENDED) {
            transaction.setStatus(Status.CANCELED);
            transactionService.save(transaction);
        }
        return ResponseEntity.ok(transaction);
    }
}
