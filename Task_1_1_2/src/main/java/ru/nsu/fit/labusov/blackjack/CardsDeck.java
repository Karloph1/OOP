package ru.nsu.fit.labusov.blackjack;

import java.util.ArrayList;

/**
 * Deck of cards class.
 */
public class CardsDeck {
    private final ArrayList<Card> cards;

    /**
     * Constructor deck of cards method.
     */
    public CardsDeck() {
        cards = new ArrayList<>();

        for (int i = 2; i < 15; i++) {
            if (i == 14) {
                cards.add(new Card(CardSuit.HEARTS, i, 11));
                cards.add(new Card(CardSuit.CROSSES, i, 11));
                cards.add(new Card(CardSuit.DIAMONDS, i, 11));
                cards.add(new Card(CardSuit.SPADES, i, 11));
            } else if (i > 10) {
                cards.add(new Card(CardSuit.HEARTS, i, 10));
                cards.add(new Card(CardSuit.CROSSES, i, 10));
                cards.add(new Card(CardSuit.DIAMONDS, i, 10));
                cards.add(new Card(CardSuit.SPADES, i, 10));
            } else {
                cards.add(new Card(CardSuit.HEARTS, i, i));
                cards.add(new Card(CardSuit.CROSSES, i, i));
                cards.add(new Card(CardSuit.DIAMONDS, i, i));
                cards.add(new Card(CardSuit.SPADES, i, i));
            }
        }
    }

    /**
     * Get object method.
     */
    public Card getCard(int index) {
        Card card = cards.get(index);
        cards.remove(index);

        return card;
    }

    public int getDeckLength() {
        return cards.size();
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("{");

        for (Card card : cards) {
            string.append(card).append("\n");
        }

        return string.delete(string.length() - 1, string.length()) + "}";
    }
}
