package ru.nsu.fit.labusov.bakery;

/**
 *Order generator class.
 */
public class OrderGenerator implements Runnable {
    private static final String[] pizzaNames =
            new String[]{"Margarita", "4 cheeses", "Hawaii", "Peperoni", "Meat", "Seafood"};
    private static int totalOrders = 0;
    private final Thread thread;
    private final Bakery bakery;

    public OrderGenerator(Bakery bakery) {
        this.bakery = bakery;
        thread = new Thread(this, "orders");
    }

    public Thread getThread() {
        return this.thread;
    }

    /**
     * generate function.
     */
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
            if (bakery.isEndOfDay()) {
                return;
            } else {
                Order b = generateNewOrder();
                bakery.addOrder(b);
                System.out.printf("[%d] [%s]\n", totalOrders, b.getStatus());
                try {
                    Thread.sleep((int) (Math.random() * 500) + 1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}