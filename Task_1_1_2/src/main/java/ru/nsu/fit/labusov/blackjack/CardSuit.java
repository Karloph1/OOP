package ru.nsu.fit.labusov.blackjack;

/**
 * Card suit class.
 */
public enum CardSuit {
    HEARTS, DIAMONDS, SPADES, CROSSES;

    @Override
    public String toString() {
        switch (this) {
            case HEARTS:
                return ("Червы");
            case DIAMONDS:
                return ("Буби");
            case CROSSES:
                return ("Крести");
            default:
                return ("Пики");
        }
    }

    /**
     * Get card formatted into string.
     */
    public String toString(int cardValue) {
        if (cardValue <= 10 || cardValue == 14) {
            return toString();
        } else {
            StringBuilder suitName = new StringBuilder(toString());
            suitName.delete(suitName.length() - 2, suitName.length());
            suitName.append("ов");

            if (cardValue == 12) {
                suitName.append("ая");
            } else {
                suitName.append("ый");
            }

            return suitName.toString();
        }
    }
}
