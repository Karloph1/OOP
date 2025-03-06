package ru.nsu.fit.labusov.bakery;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class.
 */
public class BakeryTest {
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
     * Order generator tests.
     */
    @Test
    void orderGeneratorGenerateNewOrderTest() {
        OrderGenerator orderGenerator = new OrderGenerator();

        Order order = orderGenerator.generateNewOrder();
        String[] species = new String[]{"Margarita", "4 cheeses", "Hawaii", "Peperoni", "Meat", "Seafood"};

        Assertions.assertTrue(Arrays.stream(species).anyMatch(x -> x.equals(order.getName())));
        Assertions.assertEquals(1, order.getOrderNumber());
    }

    @Test
    void orderGeneratorGetTotalOrders() {
        OrderGenerator orderGenerator = new OrderGenerator();
        orderGenerator.generateNewOrder();
        orderGenerator.generateNewOrder();

        Assertions.assertEquals(2, orderGenerator.getTotalOrders());
    }


    /**
     * Storage tests.
     */
    @Test
    void storageGetCompletedOrdersTest() {
        Storage storage = new Storage(10);

        Assertions.assertIterableEquals(new ArrayDeque<>(), storage.getCompletedOrders());
    }

    @Test
    void storageGetQueueTest() {
        Storage storage = new Storage(10);

        Assertions.assertIterableEquals(new ArrayDeque<>(), storage.getQueue());
    }

    @Test
    void storageGetCapacityTest() {
        Storage storage = new Storage(10);

        Assertions.assertEquals(10, storage.getCapacity());
    }

    @Test
    void storageIsExistFreeSpaceTest() {
        Storage storage = new Storage(10);

        Assertions.assertTrue(storage.isExistFreeSpace());
    }

    @Test
    void storageCheckFirstReservedPlaceTest() {
        Storage storage = new Storage(10);

        Assertions.assertTrue(storage.checkFirstReservedPlace("1"));
    }

    @Test
    void storageTakePizzasTest() {
        Baker baker = new Baker(200, "1");
        Courier courier = new Courier(2, "1");
        Order order = new Order("4 Cheeses", 1);
        Order order1 = new Order("Margarita", 2);
        Order order2 = new Order("Hawaii", 3);

        Storage storage = new Storage(10);

        storage.getPizza(baker, order);
        storage.getPizza(baker, order1);
        storage.getPizza(baker, order2);

        List<Order> orders = new ArrayList<>();
        orders.add(order);
        orders.add(order1);

        Assertions.assertEquals(orders, storage.takePizzas(courier));
    }


    /**
     * Parser tests.
     */
    @Test
    void parserGetBakersTest() {
        Parser parser = new Parser();
        parser.parse("build/resources/test/data.json");

        ArrayList<Baker> bakers = new ArrayList<>();
        bakers.add(new Baker(500, "1"));
        bakers.add(new Baker(1000, "2"));
        bakers.add(new Baker(200, "3"));
        bakers.add(new Baker(1001, "4"));
        bakers.add(new Baker(467, "5"));
        bakers.add(new Baker(786, "6"));

        Assertions.assertEquals(parser.getBakers(), bakers);
    }

    @Test
    void parserGetCouriersTest() {
        Parser parser = new Parser();
        parser.parse("build/resources/test/data.json");

        ArrayList<Courier> couriers = new ArrayList<>();
        couriers.add(new Courier(3, "1"));
        couriers.add(new Courier(7, "2"));
        couriers.add(new Courier(1, "3"));
        couriers.add(new Courier(4, "4"));
        couriers.add(new Courier(2, "5"));

        Assertions.assertEquals(parser.getCouriers(), couriers);
    }

    @Test
    void parserGetStorageTest() {
        Parser parser = new Parser();
        parser.parse("build/resources/test/data.json");

        Storage storage = new Storage(8);

        Assertions.assertEquals(parser.getStorage(), storage);
    }


    /**
     * Bakery tests.
     */
    @Test
    void bakeryGetBakersTest() {
        ArrayList<Baker> bakers = new ArrayList<>();
        bakers.add(new Baker(500, "1"));
        bakers.add(new Baker(1000, "2"));
        bakers.add(new Baker(200, "3"));
        bakers.add(new Baker(1001, "4"));
        bakers.add(new Baker(467, "5"));
        bakers.add(new Baker(786, "6"));

        List<Baker> bakers1 = new ArrayList<>();
        bakers1.add(new Baker(500, "1"));
        bakers1.add(new Baker(1000, "2"));
        bakers1.add(new Baker(200, "3"));
        bakers1.add(new Baker(1001, "4"));
        bakers1.add(new Baker(467, "5"));
        bakers1.add(new Baker(786, "6"));

        ArrayList<Courier> couriers = new ArrayList<>();
        Storage storage = new Storage(10);
        Bakery bakery = new Bakery(bakers, couriers, storage);

        Assertions.assertEquals(bakers1, bakery.getBakers());
    }

    @Test
    void bakeryGetCouriersTest() {
        ArrayList<Baker> bakers = new ArrayList<>();

        ArrayList<Courier> couriers = new ArrayList<>();
        couriers.add(new Courier(3, "1"));
        couriers.add(new Courier(7, "2"));
        couriers.add(new Courier(1, "3"));
        couriers.add(new Courier(4, "4"));
        couriers.add(new Courier(2, "5"));

        ArrayList<Courier> couriers1 = new ArrayList<>();
        couriers1.add(new Courier(3, "1"));
        couriers1.add(new Courier(7, "2"));
        couriers1.add(new Courier(1, "3"));
        couriers1.add(new Courier(4, "4"));
        couriers1.add(new Courier(2, "5"));

        Storage storage = new Storage(10);
        Bakery bakery = new Bakery(bakers, couriers, storage);

        Assertions.assertEquals(couriers1, bakery.getCouriers());
    }

    @Test
    void bakeryGetStorageTest() {
        ArrayList<Baker> bakers = new ArrayList<>();
        ArrayList<Courier> couriers = new ArrayList<>();
        Storage storage = new Storage(10);
        Storage storage1 = new Storage(10);

        Bakery bakery = new Bakery(bakers, couriers, storage);

        Assertions.assertEquals(storage1, bakery.getStorage());
    }

    @Test
    void bakeryGetTotalOrders() {
        ArrayList<Baker> bakers = new ArrayList<>();
        ArrayList<Courier> couriers = new ArrayList<>();
        Storage storage = new Storage(10);

        Bakery bakery = new Bakery(bakers, couriers, storage);

        Order order = new Order("hs", 1);
        Order order1 = new Order("h", 2);

        ArrayList<Order> orders = new ArrayList<>();
        orders.add(order);
        orders.add(order1);

        bakery.addOrder(order);
        bakery.addOrder(order1);

        Assertions.assertEquals(orders, bakery.getTotalOrders());
    }

    @Test
    void bakeryGetWorkingBakersTest() {
        ArrayList<Baker> bakers = new ArrayList<>();
        ArrayList<Courier> couriers = new ArrayList<>();

        Storage storage = new Storage(10);

        Bakery bakery = new Bakery(bakers, couriers, storage);
        bakery.registerBaker(true);

        Assertions.assertEquals(1, bakery.getWorkingBakers());
    }

    @Test
    void bakeryHasWorkedBakersTest() {
        ArrayList<Baker> bakers = new ArrayList<>();
        ArrayList<Courier> couriers = new ArrayList<>();
        Storage storage = new Storage(10);

        Bakery bakery = new Bakery(bakers, couriers, storage);
        Assertions.assertTrue(bakery.hasNotWorkedBakers());
    }

    @Test
    void bakeryFreeOrdersTest() {
        ArrayList<Baker> bakers = new ArrayList<>();
        ArrayList<Courier> couriers = new ArrayList<>();
        Storage storage = new Storage(10);

        Bakery bakery = new Bakery(bakers, couriers, storage);
        Assertions.assertFalse(bakery.hasFreeOrders());
    }

    @Test
    void bakeryAddOrderTest() {
        ArrayList<Baker> bakers = new ArrayList<>();
        ArrayList<Courier> couriers = new ArrayList<>();
        Storage storage = new Storage(10);

        Bakery bakery = new Bakery(bakers, couriers, storage);
        bakery.addOrder(new Order("4 cheeses", 1));

        Assertions.assertTrue(bakery.hasFreeOrders());
    }

    @Test
    void bakeryIsEndOfDayTest() {
        ArrayList<Baker> bakers = new ArrayList<>();
        ArrayList<Courier> couriers = new ArrayList<>();
        Storage storage = new Storage(10);

        Bakery bakery = new Bakery(bakers, couriers, storage);

        Assertions.assertFalse(bakery.isEndOfDay());
    }

    @Test
    void bakeryTakeOrderTest() {
        ArrayList<Baker> bakers = new ArrayList<>();
        ArrayList<Courier> couriers = new ArrayList<>();
        Storage storage = new Storage(10);

        Bakery bakery = new Bakery(bakers, couriers, storage);

        bakery.addOrder(new Order("4 cheeses", 1));
        bakery.takeOrder();

        Assertions.assertFalse(bakery.hasFreeOrders());
    }

    @Test
    void bakeryRegisterBakerTest() {
        ArrayList<Baker> bakers = new ArrayList<>();
        ArrayList<Courier> couriers = new ArrayList<>();
        Storage storage = new Storage(10);

        Bakery bakery = new Bakery(bakers, couriers, storage);
        bakery.registerBaker(true);
        Assertions.assertFalse(bakery.hasNotWorkedBakers());
    }

    @Test
    void bakeryInitialisingProcess() {
        ArrayList<Baker> bakers = new ArrayList<>();
        bakers.add(new Baker(500, "1"));
        bakers.add(new Baker(1000, "2"));
        bakers.add(new Baker(200, "3"));
        bakers.add(new Baker(1001, "4"));
        bakers.add(new Baker(467, "5"));
        bakers.add(new Baker(786, "6"));

        ArrayList<Courier> couriers = new ArrayList<>();
        couriers.add(new Courier(3, "1"));
        couriers.add(new Courier(7, "2"));
        couriers.add(new Courier(1, "3"));
        couriers.add(new Courier(4, "4"));
        couriers.add(new Courier(2, "5"));

        Storage storage = new Storage(10);

        Bakery bakery = new Bakery(bakers, couriers, storage);

        bakery.initialisingProcess();

        Assertions.assertTrue(bakery.getTotalOrders().stream().allMatch(x -> x.getStatus().equals("delivered")));
    }
}