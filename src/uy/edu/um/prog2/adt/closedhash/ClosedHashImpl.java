package uy.edu.um.prog2.adt.closedhash;

import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.adt.linkedlist.MyList;

public class ClosedHashImpl<K extends Comparable<K>, V> implements ClosedHash<K, V> {
    private HashBucket<K, V>[] closedHash;
    private int size;
    private int capacity;
    private static final double LOAD_FACTOR = 0.75; // resize when 75% full
    private final MyList<K> keys = new MyLinkedListImpl<>();

    public MyList<K> getKeys() {
        return keys;
    }

    @SuppressWarnings("unchecked")
    public ClosedHashImpl(int initialCapacity) {
        this.capacity = initialCapacity;
        this.closedHash = new HashBucket[initialCapacity];
        this.size = 0;
    }

    @Override
    public void insertar(K key, V valor) throws DuplicateKey {
        if (size >= LOAD_FACTOR * capacity) {
            resize();
        }

        int index = getIndex(key);
        int originalIndex = index;
        int i = 0;

        while (closedHash[index] != null && !closedHash[index].isDeleted()) {
            if (closedHash[index].getKey().equals(key)) {
                throw new DuplicateKey();
            }
            i++;
            index = (originalIndex + i) % capacity;
        }

        if (closedHash[index] == null || closedHash[index].isDeleted()) {
            size++;
        }

        closedHash[index] = new HashBucket<>(key, valor);
        keys.add(key);
    }

    @Override
    public void changeValue(K key, V valorNuevo) {
        if (size == 0) {
            System.out.println("La tabla está vacía.");
            return;
        }

        int index = getIndex(key);
        int originalIndex = index;
        int i = 0;

        while (closedHash[index] != null && !closedHash[index].isDeleted()) {
            if (closedHash[index].getKey().equals(key)) {
                closedHash[index].setValue(valorNuevo);
                return;
            }
            i++;
            index = (originalIndex + i) % capacity;
        }

        System.out.println("La clave ingresada no existe.");
    }

    @Override
    public V getValue(K key) {
        int index = getIndex(key);
        int originalIndex = index;
        int i = 0;

        while (closedHash[index] != null) {
            if (!closedHash[index].isDeleted() && closedHash[index].getKey().equals(key)) {
                return closedHash[index].getValue();
            }
            i++;
            index = (originalIndex + i) % capacity;
        }

        return null;
    }

    public Boolean contains(K key) {
        return getValue(key) != null;
    }

    @Override
    public void delete(K key) {
        int index = getIndex(key);
        int originalIndex = index;
        int i = 0;

        while (closedHash[index] != null) {
            if (!closedHash[index].isDeleted() && closedHash[index].getKey().equals(key)) {
                closedHash[index].delete();
                size--;
                return;
            }
            i++;
            index = (originalIndex + i) % capacity;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    public void resize() throws DuplicateKey {
        HashBucket<K, V>[] oldTable = closedHash;
        capacity *= 2;
        closedHash = new HashBucket[capacity];
        size = 0;

        for (HashBucket<K, V> bucket : oldTable) {
            if (bucket != null && !bucket.isDeleted()) {
                insertar(bucket.getKey(), bucket.getValue());
            }
        }
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    @Override
    public String toString() {
        StringBuilder tempString = new StringBuilder("[");
        for (int i = 0; i < closedHash.length; i++) {
            if (i > 0) {
                tempString.append(", ");
            }
            if (closedHash[i] == null || closedHash[i].isDeleted()) {
                tempString.append("null");
            } else {
                tempString.append(closedHash[i].toString());
            }
        }
        tempString.append("]");
        return tempString.toString();
    }
}