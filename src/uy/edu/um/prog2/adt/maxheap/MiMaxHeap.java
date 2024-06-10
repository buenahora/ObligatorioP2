package uy.edu.um.prog2.adt.maxheap;
import uy.edu.um.prog2.adt.maxheap.MyHeap;


//import java.util.Arrays;

public class MiMaxHeap<T extends Comparable<T>> implements MyHeap<T> {
    private T[] heapArray;
    private int size;
    private int capacity;

    @SuppressWarnings("unchecked")
    public MiMaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heapArray = (T[]) new Comparable[capacity];
    }

    @Override
    public T delete() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty.");
        }
        T maxElement = heapArray[0];
        heapArray[0] = heapArray[size - 1];
        size--;
        heapifyDown(0);
        return maxElement;
    }

    @Override
    public T get() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty.");
        }
        return heapArray[0];
    }

    @Override
    public void insert(T element) {
        if (size == capacity) {
            throw new IllegalStateException("Heap is full.");
        }
        heapArray[size] = element;
        size++;
        heapifyUp(size - 1);
    }

    @Override
    public int size() {
        return size;
    }

    private void heapifyDown(int index) {
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        int largest = index;

        if (leftChild < size && heapArray[leftChild].compareTo(heapArray[largest]) > 0) {
            largest = leftChild;
        }
        if (rightChild < size && heapArray[rightChild].compareTo(heapArray[largest]) > 0) {
            largest = rightChild;
        }

        if (largest != index) {
            swap(index, largest);
            heapifyDown(largest);
        }
    }

    private void heapifyUp(int index) {
        int parent = (index - 1) / 2;
        if (parent >= 0 && heapArray[index].compareTo(heapArray[parent]) > 0) {
            swap(index, parent);
            heapifyUp(parent);
        }
    }

    private void swap(int i, int j) {
        T temp = heapArray[i];
        heapArray[i] = heapArray[j];
        heapArray[j] = temp;
    }

    public static void main(String[] args) {
        MiMaxHeap<Integer> maxHeap = new MiMaxHeap<>(10);
        maxHeap.insert(5);
        maxHeap.insert(10);
        maxHeap.insert(3);
        maxHeap.insert(8);

        System.out.println("Max element: " + maxHeap.get()); // Should print 10
        System.out.println("Heap size: " + maxHeap.size()); // Should print 4

        while (maxHeap.size() > 0) {
            System.out.print(maxHeap.delete() + " "); // Should print 10 8 5 3
        }
    }
}
