package com.example.demo.repository;

import com.example.demo.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    @Query(value = "SELECT * FROM card u WHERE u.cart_number" +
            "  = :cartNumber and u.secret_number = :secretNumber",
            nativeQuery = true)
    public Optional<Card> findCardBySecretNumberAndCardNumber(@Param("cartNumber") Integer cartNumber,
                                                              @Param("secretNumber") Integer secretNumber);

    @Query(value = "select * from card c where c.user_id=:userId", nativeQuery = true)
    public List<Card> findCardById(@Param("userId") Integer userId);
}
