package org.example;

import java.util.NoSuchElementException;

/**
 * Baker class.
 */
public class Baker implements Runnable {
    private int velocity; // скорость готовки
    private String threadName;
    private Thread thread;

    public Baker(int velocity, String threadName) {
        this.velocity = velocity;
        this.threadName = threadName;
        thread = new Thread(this, threadName);
    }

    public Thread getThread() {
        return this.thread;
    }

    private void pizzaTransfer(Order order) throws InterruptedException { // складинирование пиццы на склад
        boolean isReserved = false;

        try {
            Storage.lock1.writeLock().lock();
            if (Storage.cantTransferOrderToStorage()) {
                Storage.reservePlace(threadName);
                isReserved = true;
            } else {
                Storage.putToStorage(order);
            }
        } finally {
            Storage.lock1.writeLock().unlock();
        }

        if (isReserved) {
            while (true) {
                try {
                    Storage.lock1.writeLock().lock();
                    if (Storage.isExistFreeSpace() && Storage.checkFirstReservedPlace(this.threadName)) {
                        Storage.unReservePlace();
                        Storage.putToStorage(order);
                        break;
                    }
                } finally {
                    Storage.lock1.writeLock().unlock();
                }

                Thread.sleep(100);
            }
        }
    }

    private boolean TryToCook() { // готовка пиццы
        Order order = null;
        boolean result = false;
        if (Bakery.hasFreeOrders()) {
            try {
                Bakery.lock.writeLock().lock();
                order = Bakery.takeOrder();
            } catch (NoSuchElementException e) {
                return false;
            } finally {
                Bakery.lock.writeLock().unlock();
            }
        }

        if (order != null) {
            order.reserveOrder();
            System.out.printf("[%d] [%s]\n", order.orderNumber, order.status);
            try {
                Thread.sleep(this.velocity);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            order.readyOrder();
            try {
                pizzaTransfer(order);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.printf("[%d] [%s]\n", order.orderNumber, order.status);
            result = true;
        }
        return result;
    }


    /**
     * run function.
     */
    @Override
    public void run() {

        try {
            Storage.lock1.writeLock().lock();
            Bakery.registerBaker();
        } finally {
            Storage.lock1.writeLock().unlock();
        }

        try {
            while (thread.isAlive()) { // пока поток жив
                if (!TryToCook()) { // если не получилось взять заказ
                    if (Bakery.isEndOfDay()) { // если день закончился
                        return;
                    } else {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        } finally {
            try {
                Storage.lock1.writeLock().lock();
                Bakery.unregisterBaker();
            } finally {
                Storage.lock1.writeLock().unlock();
            }
        }
    }
}