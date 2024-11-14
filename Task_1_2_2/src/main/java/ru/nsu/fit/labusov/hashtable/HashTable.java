package ru.nsu.fit.labusov.hashtable;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * HashTable class.
 */
public class HashTable<K, V> implements Iterator<AbstractMap.SimpleEntry<K, V>> {
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
        try {
            int index = keys.indexOf(key);

            if (index == -1) {
                keys.add(key);
                values.add(value);
            } else {
                values.set(index, value);
            }
        } catch (ConcurrentModificationException e) {
            System.out.println(e.getMessage());
            throw new ConcurrentModificationException("Impossible to do this during iteration");
        }

    }

    /**
     * Remove pair in HashTable method.
     */
    public void remove(K key, V value) {
        try {
            if (!keys.contains(key) || !value.equals(this.get(key))) {
                throw new NoSuchElementException("No such pair in hash table");
            }
            int index = keys.indexOf(key);

            keys.remove(key);
            values.remove(index);
        } catch (ConcurrentModificationException e) {
            System.out.println(e.getMessage());
            throw new ConcurrentModificationException("Impossible to do this during iteration");
        }
    }

    /**
     * Update pair in HashTable method.
     */
    public void update(K key, V value) {
        try {
            int index = keys.indexOf(key);

            values.set(index, value);
        } catch (ConcurrentModificationException e) {
            System.out.println(e.getMessage());
            throw new ConcurrentModificationException("Impossible to do this during iteration");
        }
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
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        HashTable<Object, Object> table = (HashTable<Object, Object>) obj;

        if (this.size() == 0 && this.size() == table.size()) {
            return true;
        }

        if (this.size() != table.size()) {
            return false;
        }

        if (this.keys.get(0).getClass() != table.keys.get(0).getClass()) {
            return false;
        }

        while (this.hasNext()) {
            K thisKey = this.next().getKey();
            V thisValue = this.get(thisKey);

            if (thisValue == null && table.get(thisKey) != null
                    || thisValue != null && table.get(thisKey) == null) {
                return false;
            }

            if (!table.keys.contains(thisKey)) {
                return false;
            }

            if (thisValue != null && table.get(thisKey) != null) {
                if (!table.get(thisKey).equals(thisValue)) {
                    return false;
                }
            }
        }

        return true;
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
    public boolean hasNext() {
        return keysIndex < keys.size();
    }

    @Override
    public AbstractMap.SimpleEntry<K, V> next() {
        if (keysIndex < keys.size()) {
            return new AbstractMap.SimpleEntry<>(keys.get(keysIndex),
                    this.get(keys.get(keysIndex++)));
        }

        throw new NoSuchElementException("No elements");
    }
}