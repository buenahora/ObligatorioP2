package uy.edu.um.adt.closedhash;

public interface ClosedHash<K,V> {
    void insertar(K key, V valor) throws DuplicateKey;
    V getValue(K key);
    void delete(K key);
    int getSize();
    private void resize() {

    }
    void changeValue(K key, V valor);
    String toString();
}
