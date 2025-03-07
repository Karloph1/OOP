package ru.nsu.fit.labusov.bakery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class.
 */
public class BakeryTest {
    /**
     * Order tests.
     */
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

    @Test
    void orderEqualsTest() {
        Order order1 = new Order("123", 1);
        Order order2 = new Order("123", 1);

        Assertions.assertEquals(order1, order2);
    }

    @Test
    void orderToStringTest() {
        Order order = new Order("123", 1);

        Assertions.assertEquals("Order 1, 123. free", order.toString());
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


    /**
     * Storage tests.
     */
    @Test
    void storageIsExistFreeSpaceTrueTest() {
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

        storage.putPizza(baker, order);
        storage.putPizza(baker, order1);
        storage.putPizza(baker, order2);

        List<Order> orders = new ArrayList<>();
        orders.add(order);
        orders.add(order1);

        Assertions.assertEquals(orders, storage.takePizzas(courier));
    }


    /**
     * Parser tests.
     */
    @Test
    void parserParseTest() {
        Parser parser = new Parser();
        Bakery bakery = parser.parse("build/resources/test/data.json");

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

        Storage storage = new Storage(8);

        Bakery bakery1 = new Bakery(bakers, couriers, storage);

        Assertions.assertEquals(bakery, bakery1);
    }


    /**
     * Bakery tests.
     */
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