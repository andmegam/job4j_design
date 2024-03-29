package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= table.length * LOAD_FACTOR) {
            expand();
        }
        boolean rsl = false;
        int index = indexFor(key);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            rsl = true;
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(K key) {
        int hashCode = key == null ? 0 : key.hashCode();
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(K key) {
        return (capacity - 1) & hash(key);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> kvMapEntry : table) {
            if (kvMapEntry != null) {
                int index = indexFor(kvMapEntry.key);
                if (newTable[index] == null) {
                    newTable[index] = kvMapEntry;
                }
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        V value = null;
        int index = indexFor(key);
        if (table[index] != null) {
            if (isEqualsKey(table[index].key, key)) {
                value = table[index].value;
            }
        }
        return value;
    }

    private boolean isEqualsKey(K key1, K key2) {
        int code1 = key1 == null ? 0 : key1.hashCode();
        int code2 = key2 == null ? 0 : key2.hashCode();
        return (code1 == code2) && Objects.equals(key1, key2);
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = indexFor(key);
        if (table[index] != null && isEqualsKey(table[index].key, key)) {
            table[index] = null;
            rsl = true;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {

        return new Iterator<>() {
            final int expectModCount = modCount;
            int cursor = 0;

            @Override
            public boolean hasNext() {
                if (modCount != expectModCount) {
                    throw new ConcurrentModificationException();
                }
                while (cursor < table.length && table[cursor] == null) {
                    cursor++;
                }
                return cursor < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[cursor++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
