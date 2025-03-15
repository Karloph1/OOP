package ru.nsu.fit.labusov.bakery;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Courier class.
 */
public class Courier implements Runnable {
    private final int capacity;
    private final Thread thread;
    private ArrayList<Order> takenOrders;
    private Storage storage;
    private Bakery bakery;

    /**
     * courier constructor.
     */
    public Courier(int capacity, String threadName) {
        this.capacity = capacity;
        thread = new Thread(this, threadName);
        takenOrders = new ArrayList<>();
    }

    public void setStorage(Bakery bakery) {
        this.bakery = bakery;
        this.storage = bakery.getStorage();
    }

    public int getCapacity() {
        return this.capacity;
    }

    protected Thread getThread() {
        return this.thread;
    }

    private void pizzaDelivery() throws InterruptedException {
        takenOrders = (ArrayList<Order>) storage.takePizzas(this);

        for (Order order : takenOrders) {
            order.sentOrder();
        }
        Thread.sleep(100L * takenOrders.size());
        takenOrders.clear();
    }

    /**
     * run function.
     */
    @Override
    public void run() {
        while (true) {
            if (!storage.isCompletedOrdersEmpty()) {
                try {
                    pizzaDelivery();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                if (bakery.hasNotWorkedBakers()
                        && storage.isCompletedOrdersEmpty()) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Courier courier = (Courier) o;

        if (capacity != courier.capacity) {
            return false;
        }

        return Objects.equals(takenOrders, courier.takenOrders);
    }

    @Override
    public int hashCode() {
        int result = capacity;
        result = 31 * result + (takenOrders != null ? takenOrders.hashCode() : 0);
        return result;
    }
}