package org.example;

/**
 * mark class.
 */
public enum Mark {
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2);

    public final Integer value;

    /**
     * mark function.
     */
    private Mark(int value) {
        this.value = value;
    }
}