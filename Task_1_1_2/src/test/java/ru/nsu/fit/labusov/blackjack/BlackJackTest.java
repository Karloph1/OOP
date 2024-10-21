package ru.nsu.fit.labusov.blackjack;

import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * BlackJack test.
 */
public class BlackJackTest {
    @Test
    void cardTest() {
        Card card = new Card(CardSuit.DIAMONDS, 2, 10);

        Assertions.assertEquals(CardSuit.DIAMONDS, card.getSuit());
        Assertions.assertEquals(2, card.getValue());
        Assertions.assertEquals(2, card.getBlackJackValue());
        Assertions.assertEquals("Двойка Буби (2)", card.toString());
    }

    @Test
    void cardsDeckTest() {
        CardsDeck cardsDeck = new CardsDeck();
        Assertions.assertEquals(52, cardsDeck.getDeckLength());

        Assertions.assertEquals(new Card(CardSuit.HEARTS, 7, 7), cardsDeck.getCard(20));
        Assertions.assertEquals(4, cardsDeck.getCard(10).getBlackJackValue());
        Assertions.assertEquals(CardSuit.DIAMONDS, cardsDeck.getCard(1).getSuit());
        Assertions.assertEquals(cardsDeck.getCard(0), new Card(CardSuit.HEARTS, 2, 2));

        Assertions.assertEquals(48, cardsDeck.getDeckLength());
    }

    @Test
    void dealerTest() {
        Dealer dealer = new Dealer(3);

        dealer.drawCard(true);
        Assertions.assertNotEquals(new ArrayList<Card>(), dealer.getPlayerCards());
        Assertions.assertEquals(new ArrayList<Card>(), dealer.getDealerCards());
    }

    @Test
    void blackJackTestPlayerLose() {
        BlackJack blackJack = new BlackJack(1, 1, false);

        ArrayList<Card> b = new ArrayList<>();
        b.add(new Card(CardSuit.SPADES, 10, 10));
        b.add(new Card(CardSuit.CROSSES, 10, 10));
        b.add(new Card(CardSuit.HEARTS, 10, 10));

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
