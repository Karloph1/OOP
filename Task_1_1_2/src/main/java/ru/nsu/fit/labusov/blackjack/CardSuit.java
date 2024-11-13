package ru.nsu.fit.labusov.blackjack;

import java.util.Objects;

/**
 * Card suit class.
 */
public enum CardSuit {
    HEARTS, DIAMONDS, SPADES, CROSSES;

    @Override
    public String toString() {
        return switch (this) {
            case HEARTS -> ("Черви");
            case DIAMONDS -> ("Буби");
            case CROSSES -> ("Пики");
            case SPADES -> ("Крести");
        };
    }

    /**
     * Get card formatted into string.
     */
    public String toString(int cardValue) {
        if (cardValue <= 10 || cardValue == 14) {
            return toString();
        } else {
            StringBuilder suitName = new StringBuilder(Objects.requireNonNull(toString()));
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
