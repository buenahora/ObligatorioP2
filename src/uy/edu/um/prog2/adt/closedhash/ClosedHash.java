package uy.edu.um.prog2.adt.closedhash;

public interface ClosedHash<K,V> {
    void insertar(K key, V valor) throws DuplicateKey;
    V getValue(K key);
    void delete(K key);
    int getSize();
    void resize() throws DuplicateKey;
    void changeValue(K key, V valor);
    String toString();
}
