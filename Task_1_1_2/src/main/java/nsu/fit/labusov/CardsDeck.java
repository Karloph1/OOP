package nsu.fit.labusov;

import java.util.ArrayList;

/**
 *  Deck of cards class.
 */
public class CardsDeck {
    private final ArrayList<Card> cards;

    /**
     * Constructor deck of cards method.
     */
    public CardsDeck() {
        cards = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                if (j == 12) {
                    cards.add(new Card(i, j, 11));
                } else if (j > 8) {
                    cards.add(new Card(i, j, 10));
                } else {
                    cards.add(new Card(i, j, j + 2));
                }
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
