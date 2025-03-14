package ru.nsu.fit.labusov.bakery;

public interface OrderGenerator extends Runnable {
    void setBakery(Bakery bakery);

    Thread getThread();

    Order generateNewOrder();
}
