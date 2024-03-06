package org.example;

/**
 * Order class.
 */
public class Order {
    String name;
    int orderNumber;
    String status;

    /**
     * Order constructor.
     */
    public Order(String name, int orderNumber) {
        this.name = name;
        this.orderNumber = orderNumber;
        this.status = "free";
    }

    public void reserveOrder() {
        this.status = "reserved";
    }

    public void readyOrder() {
        this.status = "ready to send";
    }

    public void sentOrder() {
        this.status = "delivered";
    }

    public Order getOrder() {
        return this;
    }
}