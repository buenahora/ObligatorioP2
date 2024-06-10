package uy.edu.um.adt.closedhash;

import uy.edu.um.adt.linkedlist.MyLinkedListImpl;

public class ClosedHashImpl<K,V extends Comparable<V>> implements ClosedHash<K,V> {
    private HashBucket<K, V>[] closedHash;
    private int size;
    private int capacity;
    private static final double load_factor = 0.75;//si la tabla se llena a 75% se duplica su tamaño

    public ClosedHashImpl(int capacity) {
        closedHash = new HashBucket[capacity];
        size = 0;
    }

    @Override
    public void insertar(K key, V valor) throws DuplicateKey {
        if (size >= load_factor * closedHash.length) {
            resize();
        }

        int index = Math.abs(key.hashCode()) % closedHash.length;
        int originalIndex = index;
        int i = 0;

        while (closedHash[index] != null && !closedHash[index].isDeleted()) {
            if (closedHash[index].getKey().equals(key)) {
                throw new DuplicateKey();
            }
            i++;
            index = (originalIndex + i) % closedHash.length;
        }

        if (closedHash[index] == null || closedHash[index].isDeleted()) {
            size++;
        }

        closedHash[index] = new HashBucket<>(key, valor);
    }

    @Override
    public void changeValue(K key, V valorNuevo) {
        if (size != 0) {
            int index = Math.abs(key.hashCode()) % closedHash.length;
            int originalIndex = index;
            int i = 0;

            while (closedHash[index] != null && !closedHash[index].isDeleted()) {
                if (closedHash[index].getKey().equals(key)) {
                    closedHash[index].setValue(valorNuevo);
                    return;
                }
                i++;
                index = (originalIndex + i) % closedHash.length;
            }

            System.out.println("La clave ingresada no existe.");
        } else {
            System.out.println("La tabla está vacía.");
        }
    }


    @Override
    public V getValue(K key) {
        int index = Math.abs(key.hashCode()) % closedHash.length;
        int originalIndex = index;
        int i = 0;

        while (closedHash[index] != null) {
            if (!closedHash[index].isDeleted() && closedHash[index].getKey().equals(key)) {
                return closedHash[index].getValue();
            }
            i++;
            index = (originalIndex + i) % closedHash.length;
        }

        return null;
    }

    @Override
    public void delete(K key) {
        int index = key.hashCode() % closedHash.length;
        int originalIndex = index;
        int i = 0;

        while (closedHash[index] != null) {
            if (!closedHash[index].isDeleted() && closedHash[index].getKey().equals(key)) {
                closedHash[index].delete();
                size--;
                return;
            }
            i++;
            index = (originalIndex + i) % closedHash.length;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    private void resize() throws DuplicateKey {
        HashBucket<K, V>[] oldTable = closedHash;
        closedHash = new HashBucket[oldTable.length * 2];
        size = 0;

        for (HashBucket<K, V> cubeta : oldTable) {
            if (cubeta != null && !cubeta.isDeleted()) {
                try {
                    insertar(cubeta.getKey(), cubeta.getValue());
                }catch (Exception e) {}

            }
        }
    }

    public String toString() {
        StringBuilder tempString = new StringBuilder();
        tempString.append("[");
        for(int i = 0;i<closedHash.length;i++) {
            if(i == closedHash.length-1) {
                if(closedHash[i] == null || closedHash[i].isDeleted()) {//closedHash[i].isDeleted(): las tumbas son impresas como valor null (las tumbas son valores borrados, pero no pueden ser eliminados del hash por la naturaleza del metodo de insercion)
                    tempString.append("null");
                } else {
                    tempString.append(closedHash[i].toString());
                }
            } else {
                if(closedHash[i] == null || closedHash[i].isDeleted()) {//idem a lo de arriba
                    tempString.append("null, ");
                } else {
                    tempString.append(closedHash[i].toString());
                    tempString.append(", ");
                }
            }
        }
        tempString.append("]");
        return tempString.toString();
    }
}