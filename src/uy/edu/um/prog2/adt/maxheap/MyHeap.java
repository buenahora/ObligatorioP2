package uy.edu.um.prog2.adt.maxheap;

public interface MyHeap<T extends Comparable<T>> {

    /**
     * Elimina el minimo o máximo dependiendo si es un Heap minimo o maximo (Ver constructor parametro isHeapMin)
     * @return
     */
	T delete();

    /**
     * Obtiene el minimo o maximo dependiendo si es un heap minimo o maximo
     * @return
     */
	T get();
	
	void insert(T element);
	
	int size();
	
}
