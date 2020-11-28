package com.example.demo.service;

import com.example.demo.entity.Card;
import java.util.List;
import java.util.Optional;

public interface CardService {
    Optional<Card> findById(Integer id);

    Card save(Card card);

    void delete(Integer cardId);

    List<Card> findAll();

    Card findCardBySecretNumberAndCardNumber(Integer cartNumber, Integer secretNumber);

    List<Card> findCardById(Integer userId);

    Card update(Card card);
}
