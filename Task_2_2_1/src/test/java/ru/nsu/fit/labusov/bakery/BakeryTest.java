package ru.nsu.fit.labusov.bakery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
 * Test class.
 */
public class BakeryTest {
    /*public static void main(String[] argc) {
        Parser.parser("Task_2_2_1/src/test/resources/data.json");
        bakers = Parser.getBakers();
        couriers = Parser.getCouriers();
        capacity = Parser.getStorage();
        Storage storage = new Storage(capacity);
        Bakery bakery = new Bakery(bakers, couriers, storage);
        bakery.initialisingProcess();
    }

     */


    /**
     * Baker tests.
     */
    @Test
    void bakerGetVelocityTest() {
        Baker baker = new Baker(100, "test");

        Assertions.assertEquals(100, baker.getVelocity());
    }

    @Test
    void bakerGetThreadNameTest() {
        Baker baker = new Baker(100, "test");

        Assertions.assertEquals("test", baker.getThreadName());
    }


    /**
     * Courier tests.
     */
    @Test
    void courierGetCapacityTest() {
        Courier courier = new Courier(100, "test");

        Assertions.assertEquals(100, courier.getCapacity());
    }

    @Test
    void courierGetTakenOrdersTest() {
        Courier courier = new Courier(100, "test");

        Assertions.assertEquals(new ArrayList<>(), courier.getTakenOrders());
    }

    /**
     * Order tests.
     */
    @Test
    void orderGetNameTest() {
        Order order = new Order("test", 1);

        Assertions.assertEquals("test", order.getName());
    }

    @Test
    void orderGetOrderNumberTest() {
        Order order = new Order("test", 1);

        Assertions.assertEquals(1, order.getOrderNumber());
    }

    @Test
    void orderGetStatusAfterCreatingTest() {
        Order order = new Order("test", 1);

        Assertions.assertEquals("free", order.getStatus());
    }

    @Test
    void orderGetStatusAfterReservingTest() {
        Order order = new Order("test", 1);

        order.reserveOrder();

        Assertions.assertEquals("reserved", order.getStatus());
    }

    @Test
    void orderGetStatusAfterCookingTest() {
        Order order = new Order("test", 1);

        order.readyOrder();

        Assertions.assertEquals("ready to send", order.getStatus());
    }

    @Test
    void orderGetStatusAfterDeliveryTest() {
        Order order = new Order("test", 1);

        order.sentOrder();

        Assertions.assertEquals("delivered", order.getStatus());
    }

    /**
     * Storage tests.
     */
    @Test
    void storageGetCapacityTest() {
        Storage storage = new Storage(10);

        Assertions.assertEquals(10, storage.getCapacity());
    }

    @Test
    void storageGetCompletedOrdersNumberTest() {
        Storage storage = new Storage(10);

        Assertions.assertEquals(0, storage.getCompletedOrdersNumber());
    }

    @Test
    void storageIsExistFreeSpaceTest() {
        Storage storage = new Storage(10);

        Assertions.assertTrue(storage.isExistFreeSpace());
    }
}