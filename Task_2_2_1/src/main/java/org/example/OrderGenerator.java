package org.example;

/**
 * Ordergenerator class.
 */
public class OrderGenerator implements Runnable {
    private static final String[] pizzaNames = new String[]{"Margarita", "4 cheeses", "Hawaii", "Peperoni", "Meat", "Seafood"};
    private static int totalOrders = 0;
    private Thread thread;

    public OrderGenerator() {
        thread = new Thread(this, "orders");
    }

    public Thread getThread() {
        return this.thread;
    }

    public static Order generateNewOrder() {
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
            if (Bakery.isEndOfDay()) {
                return;
            } else {
                Order b = generateNewOrder();
                Bakery.addOrder(b);
                System.out.printf("[%d] [%s]\n", totalOrders, b.status);
                try {
                    Thread.sleep((int) (Math.random() * 500) + 1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
