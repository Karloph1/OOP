package ru.nsu.fit.labusov.bakery;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Storage class.
 */
public class Storage {
    private final Queue<Order> completedOrders;
    private final Queue<String> queue;
    private final int capacity;
    protected final ReentrantReadWriteLock lock1 = new ReentrantReadWriteLock(true);

    /**
     * storage constructor method.
     */
    public Storage(int capacity) {
        this.capacity = capacity;
        completedOrders = new ArrayDeque<>();
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

    /**
     * get pizza to storage.
     */
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

    /**
     * take pizzas from storage.
     */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Storage storage = (Storage) o;

        if (capacity != storage.capacity) {
            return false;
        }

        if (this.getCompletedOrders().size() != storage.getCompletedOrders().size()
                || this.getQueue().size() != storage.getQueue().size()) {
            return false;
        }

        Queue<Order> tmp = this.completedOrders;
        for (int i = 0; i < tmp.size(); i++) {
            if (tmp.remove() != storage.completedOrders.remove()) {
                return false;
            }
        }

        Queue<String> tmp2 = this.queue;
        for (int i = 0; i < tmp2.size(); i++) {
            if (!Objects.equals(tmp2.remove(), storage.queue.remove())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = completedOrders.hashCode();
        result = 31 * result + queue.hashCode();
        result = 31 * result + capacity;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Storage: capacity - ");
        stringBuilder.append(capacity).append(", Completed orders - [");

        for (int i = 0; i < completedOrders.size(); i++) {
            stringBuilder.append(completedOrders.peek()).append(", ");
        }

        stringBuilder.append("], queue - [");

        for (int i = 0; i < queue.size(); i++) {
            stringBuilder.append(queue.peek()).append(", ");
        }

        return stringBuilder.append("]").toString();
    }
}