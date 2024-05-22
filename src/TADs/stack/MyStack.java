package TADs.stack;


import Classes.Exceptions.StackExceptions.EmptyStackException;

public interface MyStack<T> {

	void push(T value);
	
	T pop() throws EmptyStackException;
	
	T peek();
	
	int size();

	boolean isEmpty();
}
