package uy.edu.um.prog2.adt.closedhash;

public class HashBucket<K,V> {
    private K key;

    private V value;

    private boolean deleted;

    public HashBucket(K key, V value) {
        this.key = key;
        this.value = value;
        this.deleted = false;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void delete() {
        deleted = true;
    }

    public String toString() {
        return "(" + this.key.toString() + ", " + this.value.toString() + ")";
    }

    public void setValue(V valorNuevo) {
        this.value = valorNuevo;
    }
}