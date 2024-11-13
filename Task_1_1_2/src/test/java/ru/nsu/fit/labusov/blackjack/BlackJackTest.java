package ru.nsu.fit.labusov.blackjack;

import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * BlackJack test.
 */
public class BlackJackTest {

    /**
     * Card tests.
     */
    @Test
    void cardGetSuitTest() {
        Card card = new Card(CardSuit.DIAMONDS, 2);

        Assertions.assertEquals(CardSuit.DIAMONDS, card.getSuit());
    }

    @Test
    void cardGetValueTest() {
        Card card = new Card(CardSuit.DIAMONDS, 2);

        Assertions.assertEquals(2, card.getValue());
    }

    @Test
    void cardGetBlackJackValueTest() {
        Card card = new Card(CardSuit.DIAMONDS, 2);

        Assertions.assertEquals(2, card.getBlackJackValue());
    }

    @Test
    void cardSetBlackJackValueTest() {
        Card card = new Card(CardSuit.DIAMONDS, 14);
        Assertions.assertEquals(11, card.getBlackJackValue());

        card.setBlackJackValue(1);
        Assertions.assertEquals(1, card.getBlackJackValue());
    }

    @Test
    void cardToStringTest() {
        Card card = new Card(CardSuit.DIAMONDS, 2);

        Assertions.assertEquals("Двойка Буби (2)", card.toString());
    }

    @Test
    void cardEqualsTest() {
        Card card = new Card(CardSuit.DIAMONDS, 2);
        Card card1 = new Card(CardSuit.SPADES, 2);

        Assertions.assertNotEquals(card, card1);
    }

    /**
     * CardsDeck tests.
     */
    @Test
    void cardsDeckGetCardTest() {
        CardsDeck cardsDeck = new CardsDeck();

        Assertions.assertEquals(new Card(CardSuit.HEARTS, 7), cardsDeck.getCard(20));
    }

    @Test
    void cardsDeckGetDeckLengthTest() {
        CardsDeck cardsDeck = new CardsDeck();

        Assertions.assertEquals(52, cardsDeck.getDeckLength());
    }

    @Test
    void cardsDeckToStringTest() {
        CardsDeck cardsDeck = new CardsDeck();
        Assertions.assertEquals("""
                {Двойка Черви (2)
                Двойка Буби (2)
                Двойка Крести (2)
                Двойка Пики (2)
                Тройка Черви (3)
                Тройка Буби (3)
                Тройка Крести (3)
                Тройка Пики (3)
                Четверка Черви (4)
                Четверка Буби (4)
                Четверка Крести (4)
                Четверка Пики (4)
                Пятерка Черви (5)
                Пятерка Буби (5)
                Пятерка Крести (5)
                Пятерка Пики (5)
                Шестерка Черви (6)
                Шестерка Буби (6)
                Шестерка Крести (6)
                Шестерка Пики (6)
                Семерка Черви (7)
                Семерка Буби (7)
                Семерка Крести (7)
                Семерка Пики (7)
                Восьмерка Черви (8)
                Восьмерка Буби (8)
                Восьмерка Крести (8)
                Восьмерка Пики (8)
                Девятка Черви (9)
                Девятка Буби (9)
                Девятка Крести (9)
                Девятка Пики (9)
                Десятка Черви (10)
                Десятка Буби (10)
                Десятка Крести (10)
                Десятка Пики (10)
                Червовый Валет (10)
                Бубовый Валет (10)
                Крестовый Валет (10)
                Пиковый Валет (10)
                Червовая Дама (10)
                Бубовая Дама (10)
                Крестовая Дама (10)
                Пиковая Дама (10)
                Червовый Король (10)
                Бубовый Король (10)
                Крестовый Король (10)
                Пиковый Король (10)
                Туз Черви (11)
                Туз Буби (11)
                Туз Крести (11)
                Туз Пики (11)}""", cardsDeck.toString());
    }

    /**
     * Dealer test.
     */
    @Test
    void dealerTest() {
        Dealer dealer = new Dealer(3);

        dealer.drawCard(true);
        Assertions.assertNotEquals(new ArrayList<Card>(), dealer.getPlayerCards());
        Assertions.assertEquals(new ArrayList<Card>(), dealer.getDealerCards());
    }

    /**
     * BlackJack tests.
     */
    @Test
    void blackJackTestPlayerLose() {
        ArrayList<Card> b = new ArrayList<>();
        b.add(new Card(CardSuit.SPADES, 10));
        b.add(new Card(CardSuit.CROSSES, 10));
        b.add(new Card(CardSuit.HEARTS, 10));

        BlackJack blackJack = new BlackJack(1, 1, false);
        blackJack.changePlayerCards(b);

        Scanner scanner = new Scanner(System.in);

        boolean a = blackJack.startGame(scanner);

        Assertions.assertFalse(a);
    }

    @Test
    void blackJackTestDealerLose() {
        BlackJack blackJack = new BlackJack(1, 1, true);
        Scanner scanner2 = new Scanner("0");

        boolean a = blackJack.startGame(scanner2);

        Assertions.assertTrue(a);
    }
}
