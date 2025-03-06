package ru.nsu.fit.labusov.bakery;

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
    private JsonArray bakers = new JsonArray();
    private JsonArray couriers = new JsonArray();
    private int storage;

    /**
     * parser function.
     */
    public void parse (String fileName) {
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
    public ArrayList<Baker> getBakers() {
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
    public ArrayList<Courier> getCouriers() {
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


    public Storage getStorage() {
        return new Storage(storage);
    }
}