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
    private final LockQueue<Order> completedOrders;
    private final Queue<String> incomingOrders;
    private final int capacity;
    protected final ReentrantReadWriteLock lock1 = new ReentrantReadWriteLock(true);

    /**
     * storage constructor method.
     */
    public Storage(int capacity) {
        this.capacity = capacity;
        completedOrders = new LockQueue<>(10);
        incomingOrders = new ArrayDeque<>();
    }

    public boolean isCompletedOrdersEmpty() {
        return completedOrders.isEmpty();
    }

    public boolean isExistFreeSpace() {
        return (completedOrders.size() < capacity);
    }

    /**
     * check function.
     */
    public boolean checkFirstReservedPlace(String orderNumber) {
        if (!incomingOrders.isEmpty()) {
            return (orderNumber.equals(incomingOrders.peek()));
        } else {
            return true;
        }
    }

    /**
     * get function.
     */
    private List<Order> getOrders(int maxCount) throws InterruptedException {
        ArrayList<Order> selectedOrders = new ArrayList<>();

        for (int i = 0; i < maxCount; i++) {
            if (!completedOrders.isEmpty()) {
                selectedOrders.add(completedOrders.take());
            } else {
                break;
            }
        }

        return selectedOrders;
    }

    /**
     * get pizza to storage.
     */
    protected void putPizza(Baker baker, Order order) throws InterruptedException {
        boolean isReserved = false;

        try {
            lock1.writeLock().lock();
            if (!isExistFreeSpace() || !incomingOrders.isEmpty()) {
                incomingOrders.add(baker.getThreadName());
                isReserved = true;
            } else {
                completedOrders.put(order);
            }
        } finally {
            lock1.writeLock().unlock();
        }

        if (isReserved) {
            while (true) {
                if (isExistFreeSpace()
                        && checkFirstReservedPlace(baker.getThreadName())) {
                    incomingOrders.remove();
                    completedOrders.put(order);
                    break;
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
    protected List<Order> takePizzas(Courier courier) throws InterruptedException {
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

        if (this.completedOrders.size() != storage.completedOrders.size()
                || this.incomingOrders.size() != storage.incomingOrders.size()) {
            return false;
        }

        LockQueue<Order> tmp = this.completedOrders;
        for (int i = 0; i < tmp.size(); i++) {
            try {
                if (tmp.take() != storage.completedOrders.take()) {
                    return false;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        Queue<String> tmp2 = this.incomingOrders;
        for (int i = 0; i < tmp2.size(); i++) {
            if (!Objects.equals(tmp2.remove(), storage.incomingOrders.remove())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = completedOrders.hashCode();
        result = 31 * result + incomingOrders.hashCode();
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

        for (int i = 0; i < incomingOrders.size(); i++) {
            stringBuilder.append(incomingOrders.peek()).append(", ");
        }

        return stringBuilder.append("]").toString();
    }
}