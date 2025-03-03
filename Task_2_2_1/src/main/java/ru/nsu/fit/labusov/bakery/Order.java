package ru.nsu.fit.labusov.bakery;

/**
 * Order class.
 */
public class Order {
    private final String name;
    private final int orderNumber;
    private String status;

    /**
     * Order constructor.
     */
    public Order(String name, int orderNumber) {
        this.name = name;
        this.orderNumber = orderNumber;
        this.status = "free";
    }

    public String getName() {
        return this.name;
    }

    public int getOrderNumber() {
        return this.orderNumber;
    }

    public String getStatus() {
        return this.status;
    }

    protected void reserveOrder() {
        this.status = "reserved";
    }

    protected void readyOrder() {
        this.status = "ready to send";
    }

    protected void sentOrder() {
        this.status = "delivered";
    }
}