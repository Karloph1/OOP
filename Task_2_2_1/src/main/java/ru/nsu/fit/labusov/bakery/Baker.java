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

    protected Thread getThread() {
        return this.thread;
    }

    public String getThreadName() {
        return this.threadName;
    }

    private void pizzaTransfer(Order order) throws InterruptedException {
        bakery.putPizzaToStorage(this, order);
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
            System.out.printf("[%d] [%s]\n", order.getOrderNumber(), order.getStatus());
            try {
                pizzaTransfer(order);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
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
                            //wait();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Baker baker = (Baker) o;

        if (velocity != baker.velocity) {
            return false;
        }

        return threadName.equals(baker.threadName);
    }

    @Override
    public int hashCode() {
        int result = velocity;
        result = 31 * result + threadName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Baker " + threadName + " - " + velocity;
    }
}