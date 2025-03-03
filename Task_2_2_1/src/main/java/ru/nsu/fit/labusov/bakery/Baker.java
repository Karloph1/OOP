package ru.nsu.fit.labusov.bakery;

/**
 * Baker class.
 */
public class Baker implements Runnable {
    private final int velocity; // скорость готовки
    private final String threadName;
    private final Thread thread;
    private Bakery bakery;

    /**
     * Baker constructor.
     */
    public Baker(int velocity, String threadName) {
        this.velocity = velocity;
        this.threadName = threadName;
        thread = new Thread(this, threadName);
    }

    public void setBakery(Bakery bakery) {
        this.bakery = bakery;
    }

    public Thread getThread() {
        return this.thread;
    }

    public int getVelocity() {
        return this.velocity;
    }

    public String getThreadName() {
        return this.threadName;
    }

    private void pizzaTransfer(Order order) throws InterruptedException {
        bakery.getStorage().getPizza(this, order);
    }

    private boolean tryToCook() { // готовка пиццы
        Order order = null;
        boolean result = false;
        if (bakery.hasFreeOrders()) {
            order = bakery.takeOrder();
        }

        if (order != null) {
            order.reserveOrder();
            System.out.printf("[%d] [%s]\n", order.getOrderNumber(), order.getStatus());
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
            System.out.printf("[%d] [%s]\n", order.getOrderNumber(), order.getStatus());
            result = true;
        }

        return result;
    }

    /**
     * run function.
     */
    @Override
    public void run() {
        bakery.registerBaker(true);

        try {
            while (thread.isAlive()) { // пока поток жив
                if (!tryToCook()) { // если не получилось взять заказ
                    if (bakery.isEndOfDay()) { // если день закончился
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
            bakery.registerBaker(false);
        }
    }
}