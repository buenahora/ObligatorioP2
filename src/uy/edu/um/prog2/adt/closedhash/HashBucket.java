package uy.edu.um.prog2.adt.closedhash;

class HashBucket<K, V> {
    private K key;
    private V value;
    private boolean deleted;

    public HashBucket(K key, V value) {
        this.key = key;
        this.value = value;
        this.deleted = false;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void delete() {
        this.deleted = true;
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }
}