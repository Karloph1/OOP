package ru.nsu.fit.labusov.bakery;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Courier class.
 */
public class Courier implements Runnable {
    private final int capacity; // вместимость сумки
    private final Thread thread;
    private ArrayList<Order> takenOrders; // взятые заказы
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

        if (!takenOrders.isEmpty()) {
            for (Order order : takenOrders) {
                order.sentOrder();
                //System.out.printf("[%d] [%s] by Courier [%s]\n", order.getOrderNumber(), order.getStatus(), this.thread);
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
            if (!storage.isCompletedOrdersEmpty()) { // если есть готовые пиццы
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