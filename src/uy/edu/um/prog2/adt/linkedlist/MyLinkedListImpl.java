package uy.edu.um.prog2.adt.linkedlist;

import Classes.Exceptions.QueueExceptions.EmptyQueueException;
import Classes.Exceptions.StackExceptions.EmptyStackException;
import uy.edu.um.prog2.adt.queue.MyQueue;
import uy.edu.um.prog2.adt.stack.MyStack;

public class MyLinkedListImpl<T extends Comparable<T> > implements MyList<T>, MyQueue<T>, MyStack<T> {

    private Node<T> first;

    private Node<T> last;

    public MyLinkedListImpl() {
        this.first = null;
        this.last = null;
    }


    @Override
    public void add(T value) {
        addToTheEnd(value);
    }

    private void addToBeginning(T value) {
        if (value != null) {

            Node<T> elementToAdd = new Node<>(value);

            if (this.first == null) { // si la lista es vacia

                this.first = elementToAdd;
                this.last = elementToAdd;

            } else { // en caso de no ser vacia se agrega al comienzo

                elementToAdd.setNext(this.first);
                this.first = elementToAdd;
            }

        } else {
            // si el elemento es vacio se ignora
        }
    }

    private void addToTheEnd(T value) {
        if (value != null) {

            Node<T> elementToAdd = new Node<>(value);

            if (this.first == null) { // si la lista es vacia

                this.first = elementToAdd;
                this.last = elementToAdd;

            } else { // en caso de no ser vacia se agrega al final

                this.last.setNext(elementToAdd);
                this.last = elementToAdd;
            }

        } else {
            // si el elemento es vacio se ignora
        }
    }

    public Node<T> getFirst() {
        return this.first;
    }
    public T get(int position) {

        if(position > this.size() - 1) {
            throw new NullPointerException();
        }

        T valueToReturn = null;
        int tempPosition = 0;
        Node<T> temp = this.first;

        // Se busca el nodo que corresponde con la posicion
        while (temp != null && tempPosition != position) {

            temp = temp.getNext();
            tempPosition++;

        }

        // si se encontro la posicion se retorna el valor
        // en caso que se haya llegado al final y no se llego a la posicion se retorna null
        if (tempPosition == position) {


            valueToReturn = temp.getValue();

        }

        return valueToReturn;
    }

    public boolean contains(T value) {
        boolean contains = false;
        Node<T> temp = this.first;

        while (temp != null && !temp.getValue().equals(value)) {

            temp = temp.getNext();

        }

        if (temp != null) { // Si no se llego al final de la lista, se encontro el valor

            contains = true;

        }

        return contains;
    }

    public void sort() {
        if (size() <= 1) {
            return; // Already sorted
        }
        quickSort(0, size() - 1);
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            // pi is partitioning index, arr[pi] is now at right place
            int pi = partition(low, high);

            // Recursively sort elements before partition and after partition
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    private int partition(int low, int high) {
        T pivot = get(high);
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (get(j).compareTo(pivot) <= 0) {
                i++;

                // swap arr[i] and arr[j]
                T temp = get(i);
                set(i, get(j));
                set(j, temp);
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        T temp = get(i + 1);
        set(i + 1, get(high));
        set(high, temp);

        return i + 1;
    }

    private void set(int index, T value) {
        Node<T> current = first;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        current.setValue(value);
    }
    public void remove(T value) {
        Node<T> beforeSearchValue = null;
        Node<T> searchValue = this.first;

        // Busco el elemento a eliminar teniendo en cuenta mantener una referencia al elemento anterior
        while (searchValue != null && !searchValue.getValue().equals(value)) {

            beforeSearchValue = searchValue;
            searchValue = searchValue.getNext();

        }

        if (searchValue != null) { // si encontre el elemento a eliminar

            // Verifico si es el primer valor (caso borde) y no es el ultimo
            if (searchValue == this.first && searchValue != this.last) {

                Node<T> temp = this.first;
                this.first = this.first.getNext(); // salteo el primero

                temp.setNext(null); // quito referencia del elemento eliminado al siguiente.

                // Verifico si es el primer valor (caso borde) y no el primero
            } else if (searchValue == this.last && searchValue != this.first) {

                beforeSearchValue.setNext(null);
                this.last = beforeSearchValue;

                // Si es el primer valor y el ultimo (lista de un solo valor)
            } else if (searchValue == this.last && searchValue == this.first) {

                this.first = null;
                this.last = null;

            } else { // resto de los casos

                beforeSearchValue.setNext(searchValue.getNext());
                searchValue.setNext(null);

            }

        }
    }

    private T removeLast() { // esta operación remueve el último elemento y retorna el elemento eliminado
        T valueToRemove = null;

        if (this.last != null) {
            valueToRemove = this.last.getValue();

            remove(valueToRemove);
        }

        return valueToRemove;
    }

    public int size() {
        int size = 0;

        Node<T> temp = this.first;

        while (temp != null) {

            temp = temp.getNext();
            size++;

        }

        return size;
    }

    // Operaciones particulares a Queue

    public void enqueue(T value) {
        addToBeginning(value);
    }

    public T dequeue() throws EmptyQueueException {
        if (this.last == null) { // si la queue esta vacia

            throw new EmptyQueueException();
        }

        return removeLast();
    }

    // Operaciones particulares a Stack

    public void push(T value) {
        addToTheEnd(value);
    }

    public T pop() throws EmptyStackException {
        if (this.last == null) { // si la pila esta vacia

            throw new EmptyStackException();
        }

        return removeLast();
    }

    public T peek() {
        T valueToReturn = null;

        if (this.last != null) {
            valueToReturn = this.last.getValue();
        }

        return valueToReturn;
    }

    public boolean isEmpty() {
        return (this.first == null && this.last==null);
    }
}
