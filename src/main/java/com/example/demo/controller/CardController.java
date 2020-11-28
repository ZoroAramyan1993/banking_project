package com.example.demo.controller;

import com.example.demo.dto.CardRequest;
import com.example.demo.entity.Card;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.UserService;
import com.example.demo.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("api/card")
public class CardController {

    @Autowired
    UserService userService;

    @Autowired
    CardService cardService;


    @GetMapping("/get")
    Card getCardNumberAndSecretNumber(@RequestParam(value = "cartNumber") Integer cartNumber,
                                      @RequestParam(value = "secretNumber") Integer secretNumber) {
        return cardService.findCardBySecretNumberAndCardNumber(cartNumber, secretNumber);
    }

    @GetMapping("/user/{userId}")
    List<Card> getCardByUserId(@PathVariable("userId") Integer userId) {
        userService.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("user not found"));
        List<Card> cards = cardService.findCardById(userId);
        return cards;
    }


    @GetMapping("/getAllCards")
    List<Card> getAllCards(@RequestParam("userId") Integer userId) {
        userService.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        return cardService.findAll();
    }


    //@Secured("ADMIN")
    @Transactional
    @PostMapping("/saveCard/")
    ResponseEntity<Card> saveCard(@Valid @RequestBody Card card, @RequestParam(name = "id") Integer id) {
        User user = userService.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("resource not found"));
        card.setUser(user);
        cardService.save(card);

        Set<Card> cardSet = new HashSet<>();
        cardSet.addAll(user.getCards());
        cardSet.add(card);
        user.getCards().clear();
        user.getCards().addAll(cardSet);
        return ResponseEntity.ok(card);
    }


    @PutMapping("/updateCard")
    ResponseEntity<?> updateCard(@Valid @RequestBody CardRequest cardRequest,
                                 @RequestParam(name = "cardId") Integer cardId) {
        cardService.findById(cardId).map(card -> {
            card.setSecretNumber(cardRequest.getSecretNumber());
            card.setCartNumber(cardRequest.getCartNumber());
            card.setBalance(cardRequest.getBalance());
            card.setAccountType(cardRequest.getAccountType());
            card.setCurrencies(cardRequest.getCurrencies());
            return cardService.update(card);
        }).orElseThrow(() -> new ResourceNotFoundException("resource not fund"));
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/deleteCard")
    void deleteCard(@RequestParam("userId") Integer userId, @RequestParam(value = "cardId") Integer cardId) {
        Optional<User> user = userService.findById(userId);
        cardService.delete(cardId);
        Set<Card> cardSet = new HashSet<>();
        cardSet.addAll(user.get().getCards());
        user.get().getCards().clear();
        user.get().setCards(cardSet);
        ResponseEntity.ok().build();
    }
}
