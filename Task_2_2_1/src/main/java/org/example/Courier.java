package org.example;

import java.util.ArrayList;

/**
 * Courier class.
 */
public class Courier implements Runnable {
    private int capacity; // вместимость сумки
    private String threadName;
    private Thread thread;
    private ArrayList<Order> takenOrders; // взятые заказы

    public Courier(int capacity, String threadName) {
        this.capacity = capacity;
        this.threadName = threadName;
        thread = new Thread(this, threadName);
        takenOrders = new ArrayList<>();
    }

    public Thread getThread() {
        return this.thread;
    }

    private void pizzaDelivery() throws InterruptedException {
        try {
            Storage.lock1.writeLock().lock();
            takenOrders = Storage.getOrders(capacity);
        } finally {
            Storage.lock1.writeLock().unlock();
        }

        if (takenOrders.size() > 0) {
            for (Order order : takenOrders) {
                order.sentOrder();
                System.out.printf("[%d] [%s]\n", order.orderNumber, order.status);
            }
            Thread.sleep(100 * takenOrders.size());
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
        while (true) { // пока поток жив
            if (Storage.getCurrentStorage() != 0) { // если есть готовые пиццы
                try {
                    pizzaDelivery();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                if (!Bakery.hasWorkedBakers() && Storage.getCurrentStorage() == 0) { // если готовых пицц больше нету
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