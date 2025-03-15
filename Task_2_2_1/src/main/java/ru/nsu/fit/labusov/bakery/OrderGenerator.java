package ru.nsu.fit.labusov.bakery;

import java.util.Objects;

/**
 * Order generator class.
 */
public class OrderGenerator implements Runnable {
    private static final String[] pizzaNames =
            new String[]{"Margarita", "4 cheeses", "Hawaii", "Peperoni", "Meat", "Seafood"};
    private static int totalOrders;
    private final Thread thread;
    private Bakery bakery;
    private int orderSpeed;

    /**
     * order generator constructor class.
     */
    public OrderGenerator(int orderSpeed) {
        totalOrders = 0;
        thread = new Thread(this, "orders");
        this.orderSpeed = orderSpeed;
    }

    public void setOrderSpeed(int orderSpeed) {
        this.orderSpeed = orderSpeed;
    }

    public void setBakery(Bakery bakery) {
        this.bakery = bakery;
    }

    public Thread getThread() {
        return this.thread;
    }

    /**
     * generate function.
     */
    public Order generateNewOrder() {
        String pizza = pizzaNames[(int) (Math.random() * pizzaNames.length)];
        totalOrders++;
        return new Order(pizza, totalOrders);
    }

    /**
     * run function.
     */
    @Override
    public void run() {
        while (thread.isAlive()) {
            if (bakery.isEndOfDay()) {
                return;
            } else {
                Order b = generateNewOrder();
                try {
                    bakery.addOrder(b);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    Thread.sleep((int) (Math.random() * orderSpeed) + 1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderGenerator that = (OrderGenerator) o;

        return Objects.equals(bakery, that.bakery);
    }

    @Override
    public int hashCode() {
        int result = thread != null ? thread.hashCode() : 0;
        result = 31 * result + (bakery != null ? bakery.hashCode() : 0);
        return result;
    }
}