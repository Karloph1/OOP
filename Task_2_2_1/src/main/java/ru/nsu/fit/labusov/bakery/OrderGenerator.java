package ru.nsu.fit.labusov.bakery;

/**
 * Order generator interface.
 */
public interface OrderGenerator extends Runnable {
    void setBakery(Bakery bakery);

    Thread getThread();

    Order generateNewOrder();
}
