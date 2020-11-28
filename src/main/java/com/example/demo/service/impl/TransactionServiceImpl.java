package com.example.demo.service.impl;

import com.example.demo.entity.Card;
import com.example.demo.entity.Transaction;
import com.example.demo.entity.User;
import com.example.demo.exception.InputException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CardRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CardRepository cardRepository;


    Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);


    @Override
    public Optional<Transaction> findById(Integer id) {
        return transactionRepository.findById(id);
    }

    @Override
    public Transaction save(Transaction transaction) {
        if (transaction.getStatus() == null || transaction.getUser() == null ||
                transaction.getAccountType() == null) {
            logger.error("transaction can not be saved");
            throw new ResourceNotFoundException("not found");
        }
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Timestamp> getTimeById(Integer userId) {
        userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("user not found"));
        return transactionRepository.getTimeById(userId);
    }

    @Override
    public List<Transaction> getTransactionByUserId(Integer userId) {
        userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("user not found"));
        return transactionRepository.getTransactionByUserId(userId);
    }

    @Override
    public List<Transaction> getTransactionsByCardId(Integer cardId) {
        cardRepository.findById(cardId).orElseThrow(() ->
                new ResourceNotFoundException("card not found"));
        return transactionRepository.getTransactionsByCardId(cardId);
    }

    @Override
    public Transaction existByUserIdAndTransactionId(Integer userId, Integer transactionId) {
        userRepository.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("not found"));
        findById(transactionId).
                orElseThrow(() -> new ResourceNotFoundException("not found"));
        return transactionRepository.existByUserIdAndTransactionId(userId, transactionId);

    }
}
