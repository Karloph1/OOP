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
