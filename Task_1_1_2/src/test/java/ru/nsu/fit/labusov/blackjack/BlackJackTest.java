package ru.nsu.fit.labusov.blackjack;

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

        Assertions.assertEquals(4, cardsDeck.getCard(10).getBlackJackValue());
        Assertions.assertEquals(CardSuit.CROSSES, cardsDeck.getCard(1).getSuit());
        Assertions.assertEquals(cardsDeck.getCard(0), new Card(CardSuit.HEARTS, 2, 2));

        Assertions.assertEquals(49, cardsDeck.getDeckLength());
    }

    @Test
    void dealerTest() {
        Dealer dealer = new Dealer(3);

        dealer.drawCard(true);
        Assertions.assertFalse(dealer.getPlayerCards().isEmpty());
        Assertions.assertTrue(dealer.getDealerCards().isEmpty());

        dealer.makeNewRound();
        Assertions.assertTrue(dealer.getPlayerCards().isEmpty());
    }

    @Test
    void blackJackTestPlayerLose() {
        BlackJack blackJack = new BlackJack(1, 1, false);
        Scanner scanner1 = new Scanner("1 1 1 1 1 1 1 1 1");

        boolean a = blackJack.startGame(scanner1);

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
