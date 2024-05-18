package TADs.stack;


import Classes.Exceptions.EmptyStackException;

public interface MyStack<T> {

	void push(T value);
	
	T pop() throws EmptyStackException;
	
	T peek();
	
	int size();

	T get(int i);
}