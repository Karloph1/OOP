package ru.nsu.fit.labusov.hashtable;

import java.util.AbstractMap;
import java.util.Iterator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * HashTable test.
 */
public class HashTableTest {

    @Test
    void hashTableCreateTest() {
        HashTable<Object, Object> c = new HashTable<>();

        Assertions.assertEquals(new HashTable<>(), c);
    }

    @Test
    void hashTablePutTest() {
        HashTable<Object, Object> c = new HashTable<>();
        c.put(1, 3);

        HashTable<Object, Object> c1 = new HashTable<>();

        Assertions.assertNotEquals(c1, c);
    }

    @Test
    void hashTableRemoveTest() {
        HashTable<Object, Object> c = new HashTable<>();
        c.put(1, 3);

        HashTable<Object, Object> c1 = new HashTable<>();
        Assertions.assertNotEquals(c1, c);

        c.remove(1, 3);
        Assertions.assertEquals(c1, c);
    }

    @Test
    void hashTableGetTest() {
        HashTable<Integer, Integer> c = new HashTable<>();
        c.put(1, 5);

        Assertions.assertEquals(5, c.get(1));
    }

    @Test
    void hashTableUpdateTest() {
        HashTable<Integer, Integer> c = new HashTable<>();
        c.put(20, 10);
        Assertions.assertEquals(10, c.get(20));

        c.update(20, 20);
        Assertions.assertEquals(20, c.get(20));
    }

    @Test
    void hashTableHasValueTest() {
        HashTable<Integer, Integer> c = new HashTable<>();
        c.put(10, 5);

        Assertions.assertTrue(c.hasValue(10));
        Assertions.assertFalse(c.hasValue(1));
    }

    @Test
    void hashTableSizeTest() {
        HashTable<Integer, Integer> c = new HashTable<>();
        c.put(10, 5);
        c.put(2, 4);

        Assertions.assertEquals(2, c.size());
    }

    @Test
    void hashTableEqualsTest() {
        HashTable<Integer, Double> c = new HashTable<>();
        c.put(10, 1.0);
        c.put(2, 0.0);
        c.put(-10, 2.5);

        HashTable<Integer, Double> c1 = new HashTable<>();
        c1.put(10, 1.0);
        c1.put(2, 0.0);
        c1.put(-10, 2.5);

        Assertions.assertEquals(c1, c);
    }

    @Test
    void hashTableToStringTest() {
        HashTable<Integer, Integer> c = new HashTable<>();
        c.put(10, 5);
        c.put(2, 4);

        Assertions.assertEquals("{10=5, 2=4}", c.toString());

        HashTable<Integer, Integer> c1 = new HashTable<>();
        Assertions.assertEquals("{}", c1.toString());
    }

    @Test
    void hashTableIteratorTest() {
        HashTable<Integer, Integer> c = new HashTable<>();
        c.put(10, 4);
        c.put(2, 4);
        c.put(3, 4);
        c.put(1, 4);

        for (AbstractMap.SimpleEntry<Integer, Integer> pair : c) {
            Assertions.assertEquals(4, pair.getValue());
        }
    }

    @Test
    void hashTableMultipleIteratorTest() {
        HashTable<Integer, Integer> c = new HashTable<>();
        c.put(10, 4);
        c.put(2, 4);
        c.put(3, 4);
        c.put(1, 4);

        Iterator<AbstractMap.SimpleEntry<Integer, Integer>> iterator = c.iterator();
        Iterator<AbstractMap.SimpleEntry<Integer, Integer>> iterator1 = c.iterator();
        Iterator<AbstractMap.SimpleEntry<Integer, Integer>> iterator2 = c.iterator();

        while (iterator.hasNext()) {
            Assertions.assertEquals(4, iterator.next().getValue());
        }

        while (iterator1.hasNext()) {
            Assertions.assertEquals(4, iterator1.next().getValue());
        }

        while (iterator2.hasNext()) {
            Assertions.assertEquals(4, iterator2.next().getValue());
        }
    }
}
