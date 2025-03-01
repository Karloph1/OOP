package ru.nsu.fit.labusov.bakery;

import java.util.ArrayList;

/**
 * Test class.
 */
public class BakeryTest {
    static ArrayList<Baker> bakers = new ArrayList<>();
    static ArrayList<Courier> couriers = new ArrayList<>();
    static int capacity;

    /**
     * main function.
     */
    public static void main(String[] argc) {
        Parser.parser("Task_2_2_1/src/test/resources/data.json");
        bakers = Parser.getBakers();
        couriers = Parser.getCouriers();
        capacity = Parser.getStorage();
        Storage storage = new Storage(capacity);
        Bakery bakery = new Bakery(bakers, couriers, storage);
        bakery.initialisingProcess();
    }
}