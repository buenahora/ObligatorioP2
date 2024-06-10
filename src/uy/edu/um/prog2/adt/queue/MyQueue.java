package uy.edu.um.prog2.adt.queue;
import Classes.Exceptions.QueueExceptions.EmptyQueueException;

public interface MyQueue<T> {

	void enqueue(T value);

	T dequeue() throws EmptyQueueException;
	
	boolean contains(T value);

	T get(int i);
	
	int size();

	boolean isEmpty();
}
