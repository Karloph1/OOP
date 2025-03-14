package ru.nsu.fit.labusov.bakery;

import java.util.Objects;

/**
 * Quick order generator class.
 */
public class QuickOrderGenerator implements Runnable, OrderGenerator {
    private static final String[] pizzaNames =
            new String[]{"Margarita", "4 cheeses", "Hawaii", "Peperoni", "Meat", "Seafood"};
    private static int totalOrders;
    private final Thread thread;
    private Bakery bakery;

    public QuickOrderGenerator() {
        totalOrders = 0;
        thread = new Thread(this, "orders");
    }

    @Override
    public void setBakery(Bakery bakery) {
        this.bakery = bakery;
    }

    @Override
    public Thread getThread() {
        return this.thread;
    }

    @Override
    public Order generateNewOrder() {
        String pizza = pizzaNames[(int) (Math.random() * pizzaNames.length)];
        totalOrders++;
        return new Order(pizza, totalOrders);
    }

    @Override
    public void run() {
        while (thread.isAlive()) {
            if (bakery.isEndOfDay()) {
                return;
            } else {
                Order b = generateNewOrder();
                //System.out.println("Generate new Order " + totalOrders);
                try {
                    bakery.addOrder(b);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    Thread.sleep((int) (Math.random() * 50) + 1);
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

        QuickOrderGenerator that = (QuickOrderGenerator) o;

        return Objects.equals(bakery, that.bakery);
    }

    @Override
    public int hashCode() {
        return bakery != null ? bakery.hashCode() : 0;
    }
}
