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

    public OrderGenerator() {
        totalOrders = 0;
        thread = new Thread(this, "orders");
    }

    public void setBakery(Bakery bakery) {
        this.bakery = bakery;
    }

    protected Thread getThread() {
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
                System.out.printf("[%d] [%s]\n", b.getOrderNumber(), b.getStatus());

                bakery.addOrder(b);
                try {
                    Thread.sleep((int) (Math.random() * 500) + 1);
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

        if (!Objects.equals(thread, that.thread)) {
            return false;
        }

        return Objects.equals(bakery, that.bakery);
    }

    @Override
    public int hashCode() {
        int result = thread != null ? thread.hashCode() : 0;
        result = 31 * result + (bakery != null ? bakery.hashCode() : 0);
        return result;
    }
}