package ru.nsu.fit.labusov.bakery;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Storage class.
 */
public class Storage {
    private final Queue<Order> completedOrders = new ArrayDeque<>();
    private final Queue<String> queue;
    private final int capacity;
    protected final ReentrantReadWriteLock lock1 = new ReentrantReadWriteLock(true);

    public Storage(int capacity) {
        this.capacity = capacity;
        queue = new ArrayDeque<>();
    }

    public Queue<Order> getCompletedOrders() {
        return completedOrders;
    }

    public Queue<String> getQueue() {
        return queue;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCompletedOrdersNumber() {
        return completedOrders.size();
    }

    public boolean isExistFreeSpace() {
        return (completedOrders.size() < capacity);
    }


    /**
     * check function.
     */
    public boolean checkFirstReservedPlace(String orderNumber) {
        if (!queue.isEmpty()) {
            return (orderNumber.equals(queue.peek()));
        } else {
            return true;
        }
    }

    /**
     * get function.
     */
    private List<Order> getOrders(int maxCount) {
        ArrayList<Order> selectedOrders = new ArrayList<>();

        for (int i = 0; i < maxCount; i++) {
            if (!completedOrders.isEmpty()) {
                selectedOrders.add(completedOrders.poll());
            } else {
                break;
            }
        }

        return selectedOrders;
    }

    protected void getPizza(Baker baker, Order order) {
        boolean isReserved = false;

        try {
            lock1.writeLock().lock();
            if (!isExistFreeSpace() || !queue.isEmpty()) {
                queue.add(baker.getThreadName());
                isReserved = true;
            } else {
                completedOrders.add(order);
            }
        } finally {
            lock1.writeLock().unlock();
        }

        if (isReserved) {
            while (true) {
                try {
                    lock1.writeLock().lock();
                    if (isExistFreeSpace()
                            && checkFirstReservedPlace(baker.getThreadName())) {
                        queue.remove();
                        completedOrders.add(order);
                        break;
                    }
                } finally {
                    lock1.writeLock().unlock();
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    protected List<Order> takePizzas(Courier courier) {
        List<Order> takenOrders;

        try {
            lock1.writeLock().lock();
            takenOrders = getOrders(courier.getCapacity());
        } finally {
            lock1.writeLock().unlock();
        }

        return takenOrders;
    }
}