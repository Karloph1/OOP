package ru.nsu.fit.labusov.bakery;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Bakery class.
 */
public class Bakery {
    private final ArrayList<Baker> bakers;
    private final ArrayList<Courier> couriers;
    private final Storage storage;
    private final LinkedList<Order> freeOrders = new LinkedList<>();
    private boolean isEndOfDay; // конец дня
    private final AtomicInteger workingBakerCounter;
    protected final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    /**
     * bakery constructor.
     */
    public Bakery(ArrayList<Baker> bakers, ArrayList<Courier> couriers, Storage storage) {
        this.bakers = bakers;
        this.couriers = couriers;
        isEndOfDay = false;
        this.storage = storage;
        workingBakerCounter = new AtomicInteger(0);

        for (Baker baker : bakers) {
            baker.setBakery(this);
        }

        for (Courier courier : couriers) {
            courier.setBakery(this);
        }
    }

    public List<Baker> getBakers() {
        return bakers;
    }

    public List<Courier> getCouriers() {
        return couriers;
    }

    public Storage getStorage() {
        return storage;
    }

    public int getWorkingBakers() {
        return workingBakerCounter.get();
    }

    public boolean hasWorkedBakers() {
        return workingBakerCounter.get() <= 0;
    }

    public boolean hasFreeOrders() {
        return !freeOrders.isEmpty();
    }

    public boolean isEndOfDay() {
        return isEndOfDay;
    }

    protected void addOrder(Order order) {
        try {
            lock.writeLock().lock();
            freeOrders.add(order);

        } finally {
            lock.writeLock().unlock();
        }
    }

    protected Order takeFirstOrder() {
        return freeOrders.removeFirst();
    }

    protected void registerBaker(boolean needRegister) {
        try {
            lock.writeLock().lock();
            if (needRegister) {
                workingBakerCounter.incrementAndGet();
            } else {
                workingBakerCounter.decrementAndGet();
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    protected Order takeOrder() {
        Order order;

        try {
            lock.writeLock().lock();
            order = takeFirstOrder();
        } catch (NoSuchElementException e) {
            order = null;
        } finally {
            lock.writeLock().unlock();
        }

        return order;
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

        OrderGenerator thrr = new OrderGenerator(this);
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