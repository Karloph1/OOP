package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Bakery class.
 */
public class Bakery {
    private static ArrayList<Baker> bakers = new ArrayList<>();
    private static ArrayList<Courier> couriers = new ArrayList<>();
    private static volatile LinkedList<Order> freeOrders = new LinkedList<>();
    private static Storage storage;
    private static boolean isEndOfDay; // конец дня
    private static volatile int workingBakerCounter = 0; // конец дня
    protected static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    public Bakery(ArrayList<Baker> bakers, ArrayList<Courier> couriers, Storage storage) {
        this.bakers = bakers;
        this.couriers = couriers;
        isEndOfDay = false;
        this.storage = storage;
    }

    public static void unregisterBaker() {
        workingBakerCounter--;
    }

    public static void registerBaker() {
        workingBakerCounter++;
    }

    public static int getWorkingBakers() {
        return workingBakerCounter;
    }

    public static boolean hasWorkedBakers() {
        return workingBakerCounter > 0;
    }

    public static boolean hasFreeOrders() {
        return !freeOrders.isEmpty();
    }

    public static void addOrder(Order order) {
        freeOrders.add(order);
    }

    public static Order takeOrder() {
        return freeOrders.removeFirst();
    }

    public static boolean isEndOfDay() {
        return isEndOfDay;
    }

    public void initialisingProcess() { // инициализация пиццерии
        for (Baker thr : bakers) {
            thr.getThread().start();
        }

        while (bakers.size() > 0 && !hasWorkedBakers()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for (Courier thr : couriers) {
            thr.getThread().start();
        }
        OrderGenerator thrr = new OrderGenerator();
        thrr.getThread().start();
        long dayStart = System.currentTimeMillis();
        while (true) {
            if (System.currentTimeMillis() - dayStart >= 10000) {
                isEndOfDay = true;
                break;
            }
        }
    }
}