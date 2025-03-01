package ru.nsu.fit.labusov.bakery;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Bakery class.
 */
public class Bakery {
    private static ArrayList<Baker> bakers = new ArrayList<>();
    private static ArrayList<Courier> couriers = new ArrayList<>();
    private static final LinkedList<Order> freeOrders = new LinkedList<>();
    private static Storage storage;
    private static boolean isEndOfDay; // конец дня
    private static AtomicInteger workingBakerCounter;
    protected static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    /**
     * bakery constructor.
     */
    public Bakery(ArrayList<Baker> bakers, ArrayList<Courier> couriers, Storage storage) {
        Bakery.bakers = bakers;
        Bakery.couriers = couriers;
        isEndOfDay = false;
        Bakery.storage = storage;
        workingBakerCounter = new AtomicInteger(0);
    }

    public static void unregisterBaker() {
        workingBakerCounter.decrementAndGet();
    }

    public static void registerBaker() {
        workingBakerCounter.incrementAndGet();
    }

    public static int getWorkingBakers() {
        return workingBakerCounter.get();
    }

    public static boolean hasWorkedBakers() {
        return workingBakerCounter.get() <= 0;
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

    /**
     * start function.
     */
    public void initialisingProcess() { // инициализация пиццерии
        for (Baker thr : bakers) {
            thr.getThread().start();
        }

        while (!bakers.isEmpty() && hasWorkedBakers()) {
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