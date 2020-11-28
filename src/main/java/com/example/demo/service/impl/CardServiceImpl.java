package com.example.demo.service.impl;

import com.example.demo.entity.Card;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CardRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    Logger logger = LoggerFactory.getLogger(CardServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    CardRepository cardRepository;


    @Override
    public Optional<Card> findById(Integer id) {
        return cardRepository.findById(id);
    }

    @Override
    public Card save(Card card) {
        if (card.getCurrencies() == null || card.getUser() == null || card.getAccountType() == null) {
            logger.error("card can not be saved");
        }
        return cardRepository.save(card);
    }

    @Override
    public void delete(Integer cardId) {
        Optional<Card> card = cardRepository.findById(cardId);

        if (!card.isPresent()) {
            throw new ResourceNotFoundException("card not found");
        }

        cardRepository.delete(card.get());
    }

    @Override
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public Card findCardBySecretNumberAndCardNumber(Integer cartNumber, Integer secretNumber) {
        return cardRepository.
                findCardBySecretNumberAndCardNumber(cartNumber, secretNumber).
                orElseThrow(() -> new ResourceNotFoundException("cartNumber or secretNumber not found"));
    }


    @Override
    public List<Card> findCardById(Integer userId) {
        return cardRepository.findCardById(userId);
    }

    @Override
    public Card update(Card card) {
        return cardRepository.save(card);
    }


}
