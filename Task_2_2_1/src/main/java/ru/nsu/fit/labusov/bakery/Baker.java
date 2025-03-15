package ru.nsu.fit.labusov.bakery;

/**
 * Baker class.
 */
public class Baker implements Runnable {
    private final int velocity;
    private final String threadName;
    private final Thread thread;
    private Order cookingOrder;
    private LockQueue<Order> freeOrders;
    private Bakery bakery;
    private Storage storage;


    /**
     * Baker constructor.
     */
    public Baker(int velocity, String threadName) {
        this.velocity = velocity;
        this.threadName = threadName;
        thread = new Thread(this, threadName);
        this.freeOrders = new LockQueue<>(10);
    }

    /**
     * set method.
     */
    public void setBakery(Bakery bakery) {
        this.bakery = bakery;
        this.freeOrders = bakery.getFreeOrders();
        this.storage = bakery.getStorage();
    }

    protected Thread getThread() {
        return this.thread;
    }

    public String getThreadName() {
        return this.threadName;
    }

    private boolean tryToTakeFreeOrder() {
        try {
            cookingOrder = freeOrders.take();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }

        return cookingOrder != null;
    }

    private void cooking(Order order) {
        order.reserveOrder();
        try {
            Thread.sleep(this.velocity);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        order.readyOrder();
        try {
            storage.putPizza(this, order);
            cookingOrder = null;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * run function.
     */
    @Override
    public void run() {
        bakery.registerBaker(true);

        try {
            while (thread.isAlive()) {
                if (tryToTakeFreeOrder()) {
                    cooking(cookingOrder);
                } else {
                    if (bakery.isEndOfDay()) {
                        return;
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