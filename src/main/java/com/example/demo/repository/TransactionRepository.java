package com.example.demo.repository;

import com.example.demo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query(value = "SELECT * FROM transaction t where t.user_id=:userId", nativeQuery = true)
    List<Transaction> getTransactionByUserId(@PathParam("userId") Integer userId);

    @Query(value = "select t.created from transaction t where t.user_id = :userId", nativeQuery = true)
    List<Timestamp> getTimeById(@PathParam("userId") Integer userId);

    @Query(value = "select * from transaction t where t.card_id = :cardId", nativeQuery = true)
    List<Transaction> getTransactionsByCardId(@PathParam("cardId") Integer cardId);

    @Query(value = "select * from transaction t where t.user_id" +
            "=:userId and t.transaction_id=:transactionId",
            nativeQuery = true)
    Transaction existByUserIdAndTransactionId(@PathParam("userId") Integer userId,
                                              @PathParam("transactionId") Integer transactionId);
}


