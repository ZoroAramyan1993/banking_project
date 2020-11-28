package com.example.demo.servicetest;
import com.example.demo.entity.Card;
import com.example.demo.entity.Transaction;
import com.example.demo.entity.User;
import com.example.demo.enums.AccountType;
import com.example.demo.enums.Status;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CardRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.TransactionServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Assume;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private UserRepository userRepository;

    @Mock
    private CardRepository cartRepository;


    @InjectMocks
    private TransactionServiceImpl transactionServiceImpl;

    @Test
    public void when_save_transaction_it_should_be_done_success() {
        Transaction transaction = new Transaction();
        transaction.setStatus(Status.PENDING);
        transaction.setAccountType(AccountType.DEPOSIT);
        User user = new User();
        user.setId(1);
        transaction.setUser(user);
        given(transactionRepository.save(transaction)).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        Transaction transaction1 = transactionServiceImpl.save(transaction);
        Assume.assumeNotNull(transaction1);
        verify(transactionRepository).save(any(Transaction.class));
    }

    @Test
    public void when_save_transaction_without_status_it_should_throw_error() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            Transaction transaction = new Transaction();
            User user = new User();
            user.setId(1);
            transaction.setUser(user);
            transaction.setAccountType(AccountType.DEPOSIT);
            transactionServiceImpl.save(transaction);
        });
    }


    @Test
    public void when_save_transaction_without_type_it_should_throw_error() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            Transaction transaction = new Transaction();
            User user = new User();
            user.setId(1);
            transaction.setUser(user);
            transaction.setStatus(Status.APPENDED);
            transactionServiceImpl.save(transaction);
        });
    }

    @Test
    public void when_save_transaction_without_user_it_should_throw_error() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            Transaction transaction = new Transaction();
            transaction.setStatus(Status.APPENDED);
            transaction.setAccountType(AccountType.DEPOSIT);
            transactionServiceImpl.save(transaction);
        });
    }

    @Test
    public void get_by_id_test() {
        final Integer id = 1;
        final Transaction transaction = new Transaction();
        transaction.setTransactionId(id);
        transaction.setAccountType(AccountType.DEPOSIT);
        transaction.setStatus(Status.PENDING);
        given(transactionServiceImpl.findById(id)).willReturn(Optional.of(transaction));
        final Optional<Transaction> expected = transactionServiceImpl.findById(id);
        Assume.assumeNotNull(expected);
    }

    @Test
    public void get_all_by_user_id_test() {
        List<Transaction> transactions = new LinkedList<>();
        Transaction transaction = new Transaction();
        User user = new User();
        user.setId(1);
        transaction.setTransactionId(1);
        transaction.setStatus(Status.PENDING);
        transaction.setAccountType(AccountType.DEPOSIT);
        transaction.setUser(user);
        Transaction transaction1 = new Transaction();
        transaction1.setTransactionId(2);
        transaction1.setStatus(Status.PENDING);
        transaction1.setAccountType(AccountType.DEPOSIT);
        transaction1.setUser(user);

        transactions.add(transaction);
        transactions.add(transaction1);
        given(userRepository.findById(1)).willReturn(Optional.of(user));
        given(transactionRepository.getTransactionByUserId(1)).willReturn(transactions);

        List<Transaction> excepted = transactionServiceImpl.getTransactionByUserId(user.getId());
        Assertions.assertEquals(excepted, transactions);

    }

    @Test
    public void get_all_by_card_id_test() {
        List<Transaction> transactions = new LinkedList<>();
        Transaction transaction = new Transaction();
        User user = new User();
        user.setId(1);
        Card card = new Card();
        card.setCartId(1);
        Set<Card> cards = new HashSet<>();
        cards.add(card);
        user.setCards(cards);
        transaction.setTransactionId(1);
        transaction.setStatus(Status.PENDING);
        transaction.setAccountType(AccountType.DEPOSIT);
        transaction.setUser(user);
        transaction.setCard(card);
        Transaction transaction1 = new Transaction();
        transaction1.setTransactionId(2);
        transaction1.setStatus(Status.PENDING);
        transaction1.setAccountType(AccountType.DEPOSIT);
        transaction1.setUser(user);
        transaction1.setCard(card);
        List<Transaction> transactions1 = new ArrayList<>();
        transactions1.add(transaction);
        transactions1.add(transaction1);
        card.setList(transactions1);


        transactions.add(transaction);
        transactions.add(transaction1);
        given(cartRepository.findById(1)).willReturn(Optional.of(card));
        given(transactionRepository.getTransactionsByCardId(1)).willReturn(transactions);


        List<Transaction> excepted = transactionServiceImpl.getTransactionsByCardId(card.getCartId());
        Assertions.assertEquals(excepted, transactions);

    }


    @Test
    public void get_transactions_time_by_user_id_test() {
        List<Transaction> transactions = new LinkedList<>();
        Transaction transaction = new Transaction();
        User user = new User();
        user.setId(1);
        transaction.setTransactionId(1);
        transaction.setStatus(Status.PENDING);
        transaction.setAccountType(AccountType.DEPOSIT);
        transaction.setUser(user);
        Timestamp instant = Timestamp.from(Instant.now());
        transaction.setCreated(instant.toInstant());
        Transaction transaction1 = new Transaction();
        transaction1.setTransactionId(2);
        transaction1.setStatus(Status.PENDING);
        transaction1.setAccountType(AccountType.DEPOSIT);
        transaction1.setUser(user);
        transaction1.setCreated(instant.toInstant());

        transactions.add(transaction);
        transactions.add(transaction1);
        List<Timestamp> timestamps = new ArrayList<>();
        timestamps.add(instant);
        timestamps.add(instant);

        given(userRepository.findById(1)).willReturn(Optional.of(user));
        given(transactionRepository.getTimeById(1)).willReturn(timestamps);

        List<Timestamp> excepted = transactionServiceImpl.getTimeById(user.getId());
        Assertions.assertEquals(excepted, timestamps);

    }


    @Test
    public void get_transaction_by_user_id_and_transaction_id() {
        List<Transaction> transactions = new LinkedList<>();
        Transaction transaction = new Transaction();
        User user = new User();
        user.setId(1);
        Card card = new Card();
        card.setCartId(1);
        Set<Card> cards = new HashSet<>();
        cards.add(card);
        user.setCards(cards);
        transaction.setTransactionId(1);
        transaction.setStatus(Status.PENDING);
        transaction.setAccountType(AccountType.DEPOSIT);
        transaction.setUser(user);
        transaction.setCard(card);
        Transaction transaction1 = new Transaction();
        transaction1.setTransactionId(2);
        transaction1.setStatus(Status.PENDING);
        transaction1.setAccountType(AccountType.DEPOSIT);
        transaction1.setUser(user);
        transaction1.setCard(card);
        List<Transaction> transactions1 = new ArrayList<>();
        transactions1.add(transaction);
        transactions1.add(transaction1);
        card.setList(transactions1);


        transactions.add(transaction);
        transactions.add(transaction1);
        given(userRepository.findById(1)).willReturn(Optional.of(user));
        given(transactionRepository.findById(1)).willReturn(Optional.of(transaction));
        given(transactionRepository.existByUserIdAndTransactionId(transaction.getTransactionId(),
                user.getId())).willReturn(transaction);


        Transaction excepted = transactionServiceImpl.existByUserIdAndTransactionId(1, 1);
        Assertions.assertEquals(excepted, transaction);

    }

    @Test
    public void update_test() {
        Transaction transaction = new Transaction();
        transaction.setStatus(Status.APPENDED);
        transaction.setAccountType(AccountType.DEPOSIT);
        transaction.setTransactionId(1);
        given(transactionRepository.save(transaction)).willReturn(transaction);
        Transaction transactionEntity1 = transactionServiceImpl.updateTransaction(transaction);
        Assume.assumeNotNull(transactionEntity1);
        verify(transactionRepository).save(any(Transaction.class));
    }

}
