package ru.nsu.fit.labusov.blackjack;

/**
 * Card suit class.
 */
public enum CardSuit {
    HEARTS, DIAMONDS, SPADES, CROSSES;
    private String stringRepresentation;

    public void setStringRepresentation(String string) {
        this.stringRepresentation = string;
    }

    public String getStringRepresentation() {
        return stringRepresentation;
    }
}
