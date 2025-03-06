package ru.nsu.fit.labusov.bakery;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    protected Thread getThread() {
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
                //System.out.printf("[%d] [%s]\n", order.getOrderNumber(), order.getStatus());
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
            if (!bakery.getStorage().getCompletedOrders().isEmpty()) { // если есть готовые пиццы
                try {
                    pizzaDelivery();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                if (bakery.hasNotWorkedBakers()
                        && bakery.getStorage().getCompletedOrders().isEmpty()) {
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