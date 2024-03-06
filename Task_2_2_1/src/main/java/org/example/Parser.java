package org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Parser class.
 */
public class Parser {
    private static JsonArray bakers = new JsonArray();
    private static JsonArray couriers = new JsonArray();
    private static int storage;

    /**
     * parser function.
     */
    public static void parser(String fileName) {
        try (FileReader reader = new FileReader(fileName)) {
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
            bakers = json.getAsJsonArray("bakers");
            couriers = json.getAsJsonArray("couriers");
            storage = json.get("storage").getAsInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * get function.
     */
    public static ArrayList<Baker> getBakers() {
        ArrayList<Baker> bakersList = new ArrayList<>();
        for (JsonElement bakerElement : bakers) {
            JsonObject bakerObj = bakerElement.getAsJsonObject();
            int velocity = bakerObj.get("velocity").getAsInt();
            String threadName = bakerObj.get("threadName").getAsString();
            Baker baker = new Baker(velocity, threadName);
            bakersList.add(baker);
        }
        return bakersList;
    }

    /**
     * get function.
     */
    public static ArrayList<Courier> getCouriers() {
        ArrayList<Courier> couriersList = new ArrayList<>();
        for (JsonElement courierElement : couriers) {
            JsonObject bakerObj = courierElement.getAsJsonObject();
            int capacity = bakerObj.get("capacity").getAsInt();
            String threadName = bakerObj.get("threadName").getAsString();
            Courier courier = new Courier(capacity, threadName);
            couriersList.add(courier);
        }
        return couriersList;
    }

    public static int getStorage() {
        return storage;
    }
}
