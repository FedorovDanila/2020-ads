package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class MyHashTable<Key, Value> implements HashTable<Key, Value> {
    private static class Elem<Key, Value> {
        Key key;
        Value value;
        Elem<Key, Value> next;

        Elem(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    private static final double FACTOR = 0.6;

    private Elem<Key, Value>[] table;
    private int size;
    private int count = 0;

    MyHashTable(int initialSize) {
        size = initialSize;
        table = new Elem[size];
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Elem<Key, Value> temp = table[countIndex(key)];
        while (temp != null && !temp.key.equals(key)) {
            temp = temp.next;
        }
        return temp == null ? null : temp.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        int index = countIndex(key);
        Elem<Key, Value> temp = table[index];
        if (temp == null) {
            table[index] = new Elem<>(key, value);
            ++count;
            return;
        }

        int i = 1;
        boolean notEqual;
        while ((notEqual = !temp.key.equals(key)) && temp.next != null) {
            temp = temp.next;
            ++i;
        }
        if (notEqual) {
            temp.next = new Elem<>(key, value);
            ++count;
            if ((double)size / i > FACTOR) {
                resize();
            }
        } else {
            temp.value = value;
        }
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        int index = countIndex(key);
        Elem<Key, Value> temp = table[index];
        if (temp == null) {
            return null;
        }

        if (temp.key.equals(key)) {
            --count;
            table[index] = temp.next;
            return temp.value;
        } else if (temp.next == null) {
            return null;
        }

        Elem<Key, Value> prevTemp = temp;
        temp = temp.next;
        boolean notEqual;
        while ((notEqual = !temp.key.equals(key)) && temp.next != null ) {
            prevTemp = temp;
            temp = temp.next;
        }

        if (notEqual) {
            return null;
        } else {
            prevTemp.next = temp.next;
            --count;
            return temp.value;
        }
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    private int countIndex(Key key) {
        return key.hashCode() % size;
    }

    private void resize() {
        size = (int) (size * 1.6);
        Elem<Key, Value>[] oldTable = table;
        table = new Elem[size];
        count = 0;
        for (var elem : oldTable) {
            while (elem != null) {
                put(elem.key, elem.value);
                elem = elem.next;
            }
        }
    }
}
