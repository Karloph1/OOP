package org.example;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Storage class.
 */
public class Storage {
    private static volatile Queue<Order> completedOrders = new ArrayDeque<>(); // готовые заказы на складе, синхронизованное
    private static volatile Queue<String> queue; // очередь на склад, синхронизованное
    private static int capacity;
    protected static final ReentrantReadWriteLock lock1 = new ReentrantReadWriteLock(true);

    public Storage(int capacity) {
        this.capacity = capacity;
        queue = new ArrayDeque<>();
    }

    public static int getCurrentStorage() {
        return completedOrders.size();
    }

    public static ArrayList<Order> getOrders(int maxCount) {
        ArrayList<Order> selectedOrders = new ArrayList<Order>();

        for (int i = 0; i < maxCount; i++) {
            if (!completedOrders.isEmpty()) {
                selectedOrders.add(completedOrders.poll());
            } else {
                break;
            }
        }

        return selectedOrders;
    }

    public static boolean isExistFreeSpace() {
        return (completedOrders.size() < capacity);
    }

    public static boolean cantTransferOrderToStorage() {
        return (!isExistFreeSpace() || !queue.isEmpty());
    }

    public static void putToStorage(Order order) {
        completedOrders.add(order);
    }

    public static void reservePlace(String orderNumber) {
        queue.add(orderNumber);
    }

    public static void unReservePlace() {
        String a = queue.remove();
    }

    public static boolean checkFirstReservedPlace(String orderNumber) {
        if (!queue.isEmpty()) {
            return (orderNumber.equals(queue.peek())); // нет элементов в очереди?
        } else {
            return true;
        }
    }

}