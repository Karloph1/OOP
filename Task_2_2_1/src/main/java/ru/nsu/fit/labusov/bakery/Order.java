package ru.nsu.fit.labusov.bakery;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Order order = (Order) o;

        if (orderNumber != order.orderNumber) {
            return false;
        }
        if (!Objects.equals(name, order.name)) {
            return false;
        }

        return Objects.equals(status, order.status);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + orderNumber;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order " + orderNumber + ", " + name + ". " + status;
    }
}