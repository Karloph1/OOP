package ru.nsu.fit.labusov.bakery;

import java.util.ArrayList;
import java.util.List;

/**
 * Courier class.
 */
public class Courier implements Runnable {
    private final int capacity; // вместимость сумки
    private final Thread thread;
    private ArrayList<Order> takenOrders; // взятые заказы
    private Bakery bakery;

    /**
     * courier constructor.
     */
    public Courier(int capacity, String threadName) {
        this.capacity = capacity;
        thread = new Thread(this, threadName);
        takenOrders = new ArrayList<>();
    }

    public void setBakery(Bakery bakery) {
        this.bakery = bakery;
    }

    public int getCapacity() {
        return this.capacity;
    }
    public Thread getThread() {
        return this.thread;
    }
    public List<Order> getTakenOrders() {
        return takenOrders;
    }

    private void pizzaDelivery() throws InterruptedException {
        takenOrders = (ArrayList<Order>) bakery.getStorage().takePizzas(this);

        if (!takenOrders.isEmpty()) {
            for (Order order : takenOrders) {
                order.sentOrder();
                System.out.printf("[%d] [%s]\n", order.getOrderNumber(), order.getStatus());
            }
            Thread.sleep(100L * takenOrders.size());
            takenOrders.clear();
        } else {
            Thread.sleep(100);
        }
    }

    /**
     * run function.
     */
    @Override
    public void run() {
        while (true) {
            if (bakery.getStorage().getCompletedOrdersNumber() != 0) { // если есть готовые пиццы
                try {
                    pizzaDelivery();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                if (bakery.hasWorkedBakers() && bakery.getStorage().getCompletedOrdersNumber() == 0) {
                    return;
                } else {
                    try {
                        pizzaDelivery();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}