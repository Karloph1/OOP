package ru.nsu.fit.labusov.bakery;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Bakery class.
 */
public class Bakery {
    private final ArrayList<Baker> bakers;
    private final ArrayList<Courier> couriers;
    private final Storage storage;
    private final LockQueue<Order> freeOrders;
    private final ArrayList<Order> totalOrders;
    private boolean isEndOfDay;
    private final AtomicInteger workingBakerCounter;
    private OrderGenerator orderGenerator;
    protected final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    /**
     * bakery constructor.
     */
    public Bakery(ArrayList<Baker> bakers, ArrayList<Courier> couriers, Storage storage) {
        this.bakers = bakers;
        this.couriers = couriers;
        isEndOfDay = false;
        this.storage = storage;
        freeOrders = new LockQueue<>(100);
        totalOrders = new ArrayList<>();
        workingBakerCounter = new AtomicInteger(0);
        this.orderGenerator = new DefaultOrderGenerator();

        for (Baker baker : bakers) {
            baker.setBakery(this);
        }

        for (Courier courier : couriers) {
            courier.setStorage(this);
        }
    }

    public Storage getStorage() {
        return storage;
    }

    public List<Order> getTotalOrders() {
        return totalOrders;
    }

    public LockQueue<Order> getFreeOrders() {
        return freeOrders;
    }

    public void setOrderGenerator(OrderGenerator orderGenerator) {
        this.orderGenerator = orderGenerator;
        orderGenerator.setBakery(this);
    }

    public boolean hasNotWorkedBakers() {
        return workingBakerCounter.get() <= 0;
    }

    public boolean hasFreeOrders() {
        return !freeOrders.isEmpty();
    }

    public boolean isEndOfDay() {
        return isEndOfDay;
    }

    protected void addOrder(Order order) throws InterruptedException {
        freeOrders.put(order);
        totalOrders.add(order);
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

    /**
     * start function.
     */
    public void initialisingProcess() { // инициализация пиццерии
        for (Baker thr : bakers) {
            thr.getThread().start();
        }

        while (!bakers.isEmpty() && hasNotWorkedBakers()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for (Courier thr : couriers) {
            thr.getThread().start();
        }

        orderGenerator.setBakery(this);
        orderGenerator.getThread().start();
        long dayStart = System.currentTimeMillis();
        while (true) {
            boolean allThreadsDead = true;

            if (System.currentTimeMillis() - dayStart >= 3000) {
                isEndOfDay = true;
            }

            for (Baker thr : bakers) {
                if (thr.getThread().isAlive()) {
                    allThreadsDead = false;
                    break;
                }
            }

            for (Courier thr : couriers) {
                if (!allThreadsDead || thr.getThread().isAlive()) {
                    allThreadsDead = false;
                    break;
                }
            }

            if (isEndOfDay && allThreadsDead) {
                break;
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

        Bakery bakery = (Bakery) o;

        if (isEndOfDay != bakery.isEndOfDay) {
            return false;
        }
        if (!bakers.equals(bakery.bakers)) {
            return false;
        }
        if (!Objects.equals(couriers, bakery.couriers)) {
            return false;
        }
        if (!Objects.equals(storage, bakery.storage)) {
            return false;
        }
        if (!freeOrders.equals(bakery.freeOrders)) {
            return false;
        }
        if (!totalOrders.equals(bakery.totalOrders)) {
            return false;
        }
        return workingBakerCounter.get() == bakery.workingBakerCounter.get();
    }

    @Override
    public int hashCode() {
        int result = bakers.hashCode();
        result = 31 * result + (couriers != null ? couriers.hashCode() : 0);
        result = 31 * result + (storage != null ? storage.hashCode() : 0);
        result = 31 * result + freeOrders.hashCode();
        result = 31 * result + totalOrders.hashCode();
        result = 31 * result + (isEndOfDay ? 1 : 0);
        result = 31 * result + workingBakerCounter.hashCode();
        return result;
    }
}