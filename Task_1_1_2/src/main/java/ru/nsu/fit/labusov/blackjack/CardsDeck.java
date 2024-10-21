package ru.nsu.fit.labusov.blackjack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

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
                cards.addAll(Arrays.stream(CardSuit.values()).map(suit -> new Card(suit, 14, 11)).collect(Collectors.toList()));
            } else if (i > 10) {
                final int j = i;
                cards.addAll(Arrays.stream(CardSuit.values()).map(suit -> new Card(suit, j, 10)).collect(Collectors.toList()));
            } else {
                final int k = i;
                cards.addAll(Arrays.stream(CardSuit.values()).map(suit -> new Card(suit, k, k)).collect(Collectors.toList()));
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
