package ru.nsu.fit.labusov.blackjack;

/**
 * Single card class.
 */
public class Card {
    private final CardSuit suit;
    private final int value;
    private int blackJackValue;

    /**
     * Constructor single card method.
     */
    public Card(int numberSuit, int value, int blackJackValue) {
        switch (numberSuit) {
            case 0:
                suit = CardSuit.HEARTS;
                suit.setStringRepresentation("Червы");

                break;
            case 1:
                suit = CardSuit.DIAMONDS;
                suit.setStringRepresentation("Буби");

                break;
            case 2:
                suit = CardSuit.SPADES;
                suit.setStringRepresentation("Пики");

                break;
            default:
                suit = CardSuit.CROSSES;
                suit.setStringRepresentation("Крести");

                break;
        }

        this.value = value;
        this.blackJackValue = blackJackValue;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public int getBlackJackValue() {
        return blackJackValue;
    }

    public void setBlackJackValue(int value) {
        blackJackValue = value;
    }

    @Override
    public String toString() {
        return suit + " " + value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Card card = (Card) obj;

        return this.suit == card.suit && this.value == card.value
                && this.blackJackValue == card.blackJackValue;
    }
}
