package com.example.demo.servicetest;
import com.example.demo.entity.Card;
import com.example.demo.entity.User;
import com.example.demo.repository.CardRepository;
import com.example.demo.service.impl.CardServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Assume;

import java.util.*;

import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CardServiceImplTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardServiceImpl cardServiceImpl;

    @Test
    public void get_by_id_test() {
        final Integer id = 1;
        Card card = new Card();
        card.setCartId(id);
        given(cardRepository.findById(id)).willReturn(Optional.of(card));
        final Optional<Card> excepted = cardServiceImpl.findById(id);
        Assume.assumeNotNull(excepted);
    }

    @Test
    public void get_all_test() {
        List<Card> list = new LinkedList<>();
        Card card = new Card();
        card.setCartId(1);
        card.setCartNumber(5555216);
        card.setSecretNumber(2564555);
        Card card1 = new Card();
        card1.setCartId(2);
        card1.setCartNumber(5555673);
        card1.setSecretNumber(25565555);
        list.add(card);
        list.add(card1);
        given(cardRepository.findAll()).willReturn(list);
        List<Card> excepted = cardServiceImpl.findAll();
        Assertions.assertEquals(excepted, list);
    }

    @Test
    public void get_by_cartNumber_and_secretNumber_test() {
        Card card = new Card();
        card.setSecretNumber(5445545);
        card.setCartNumber(3245345);
        card.setCartId(1);
        given(cardRepository.findCardBySecretNumberAndCardNumber(card.getCartNumber(), card.getSecretNumber())).
                willReturn(Optional.of(card));
        Card card1 = cardServiceImpl.findCardBySecretNumberAndCardNumber(card.getCartNumber(), card.getSecretNumber());
        Assertions.assertEquals(card1, card);
    }


    @Test
    public void update_account() {
        Card card = new Card();
        card.setCartNumber(225555);
        card.setSecretNumber(2225555);
        card.setCartId(1);
        given(cardRepository.save(card)).willReturn(card);
        Card card1 = cardServiceImpl.update(card);
        Assume.assumeNotNull(card1);
        verify(cardRepository).save(any(Card.class));
    }

    @Test
    public void get_cards_of_user_test() {
        User userEntity = new User();
        userEntity.setId(1);
        Card accountEntity = new Card();
        accountEntity.setCartId(1);
        accountEntity.setCartNumber(55555);
        accountEntity.setSecretNumber(5555);
        Card accountEntity1 = new Card();
        accountEntity1.setCartId(2);
        accountEntity1.setCartNumber(5555555);
        accountEntity1.setSecretNumber(55555555);
        List<Card> list = new LinkedList<>();
        list.add(accountEntity);
        list.add(accountEntity1);
        given(cardRepository.findCardById(userEntity.getId())).willReturn(list);
        List<Card> excepted = cardServiceImpl.findCardById(userEntity.getId());
        Assertions.assertEquals(excepted, list);
    }

    @Test
    public void get_card_by_accountId() {
        User user = new User();
        user.setId(1);
        Card card = new Card();
        card.setCartId(2);
        card.setUser(user);
        List<Card> card1 = new LinkedList<>();
        card1.add(card);
        given(cardRepository.findCardById(1))
                .willReturn(card1);
        List<Card> excepted = cardServiceImpl.findCardById(1);
        Assertions.assertEquals(excepted, card1);
    }

    @Test
    public void get_all_cards_test() {
        User user = new User();
        user.setId(1);
        User user2 = new User();
        user2.setId(2);
        Card card = new Card();
        card.setCartId(2);
        card.setCartNumber(5555);
        card.setSecretNumber(53533555);
        Card card1 = new Card();
        card1.setCartId(3);
        card1.setCartNumber(68256);
        card1.setSecretNumber(3462869);
        card.setUser(user);
        card1.setUser(user2);
        List<Card> cards = new LinkedList<>();
        cards.add(card);
        cards.add(card1);
        given(cardRepository.findAll()).willReturn(cards);
        List<Card> excepted = cardServiceImpl.findAll();
        Assertions.assertEquals(excepted, cards);
    }

    @Test
    public void delete_test() {
        final Integer id = 1;
        User user = new User();
        user.setId(id);
        Card card = new Card();
        card.setCartId(id);
        given(cardRepository.findById(id)).willReturn(Optional.of(card));
        cardServiceImpl.delete(id);
        cardServiceImpl.delete(id);
        verify(cardRepository, times(2)).delete(card);
    }

}
