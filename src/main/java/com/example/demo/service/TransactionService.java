package com.example.demo.service;

import com.example.demo.entity.Transaction;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


public interface TransactionService {

    Optional<Transaction> findById(Integer id);

    Transaction save(Transaction transaction);

    Transaction updateTransaction(Transaction transaction);

    List<Timestamp> getTimeById(Integer userId);

    List<Transaction> getTransactionByUserId(Integer userId);

    List<Transaction> getTransactionsByCardId(Integer cardId);

    Transaction existByUserIdAndTransactionId(Integer userId, Integer transactionId);
}
