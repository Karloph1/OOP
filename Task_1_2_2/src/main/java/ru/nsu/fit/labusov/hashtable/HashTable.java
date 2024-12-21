package ru.nsu.fit.labusov.hashtable;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * HashTable class.
 */
public class HashTable<K, V> implements Iterable<AbstractMap.SimpleEntry<K, V>> {
    private final ArrayList<K> keys;
    private final ArrayList<V> values;
    private int keysIndex = 0;

    public HashTable() {
        keys = new ArrayList<>();
        values = new ArrayList<>();
    }

    /**
     * Put pair in HashTable method.
     */
    public void put(K key, V value) {
        int index = keys.indexOf(key);

        if (index == -1) {
            keys.add(key);
            values.add(value);
        } else {
            values.set(index, value);
        }
    }

    /**
     * Remove pair in HashTable method.
     */
    public void remove(K key, V value) {
        if (!keys.contains(key) || !value.equals(this.get(key))) {
            throw new NoSuchElementException("No such pair in hash table");
        }
        int index = keys.indexOf(key);

        keys.remove(key);
        values.remove(index);
    }

    /**
     * Update pair in HashTable method.
     */
    public void update(K key, V value) {
        int index = keys.indexOf(key);

        values.set(index, value);
    }

    /**
     * Get value in HashTable method.
     */
    public V get(K key) {
        int index = keys.indexOf(key);

        if (index == -1) {
            return null;
        }

        return values.get(index);
    }

    /**
     * Has key value in HashTable method.
     */
    public boolean hasValue(K key) {
        int index = keys.indexOf(key);

        return index != -1 && values.get(index) != null;
    }

    public int size() {
        return keys.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HashTable<?, ?> hashTable = (HashTable<?, ?>) o;

        if (keysIndex != hashTable.keysIndex) {
            return false;
        }
        if (!keys.equals(hashTable.keys)) {
            return false;
        }

        return values.equals(hashTable.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keys, values);
    }

    @Override
    public String toString() {
        if (values.isEmpty()) {
            return "{}";
        }

        StringBuilder stringBuilder = new StringBuilder("{");

        for (K key : keys) {
            stringBuilder.append(key).append("=").append(values.get(keys.indexOf(key)))
                    .append(", ");
        }

        return stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length())
                .append("}").toString();
    }

    @Override
    public Iterator<AbstractMap.SimpleEntry<K, V>> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return keysIndex < keys.size();
            }

            @Override
            public AbstractMap.SimpleEntry<K, V> next() {
                if (keysIndex < keys.size()) {
                    return new AbstractMap.SimpleEntry<>(keys.get(keysIndex),
                            values.get(keysIndex++));
                }

                throw new NoSuchElementException("No elements");
            }
        };
    }
}