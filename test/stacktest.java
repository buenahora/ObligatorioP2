package Tests;

import Classes.Exceptions.StackExceptions.EmptyStackException;
import uy.edu.um.prog2.adt.stack.MyStack;
import org.junit.Before;
import org.junit.Test;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;

import static org.junit.jupiter.api.Assertions.*;


public class stacktest {
    private MyStack<Integer> stack = new MyLinkedListImpl<>();

    @Before
    public void reiniciar() {
        stack = new MyLinkedListImpl<>();
    }

    @Test
    public void testPush() {
        stack.push(1);
        assertEquals(1, stack.size());

        stack.push(2);
        assertEquals(2, stack.size());

        stack.push(3);
        assertEquals(3, stack.size());

        stack.push(4);
        assertEquals(4, stack.size());

        stack.push(5);
        assertEquals(5, stack.size());
    }


    @Test(expected = EmptyStackException.class)
    public void testPopVacio() throws EmptyStackException {
        stack.pop();
    }
    @Test
    public void testPop() throws EmptyStackException {

        stack.push(1);
        stack.push(1);
        stack.push(1);
        stack.push(1);
        stack.push(1);

        stack.pop();
        assertEquals(4, stack.size());

        stack.pop();
        assertEquals(3, stack.size());

        stack.pop();
        assertEquals(2, stack.size());

        stack.pop();
        assertEquals(1, stack.size());

        stack.pop();
        assertEquals(0, stack.size());
    }

    @Test
    public void testPeek() {

        stack.push(1);
        assertEquals(1, stack.peek());

        stack.push(2);
        assertEquals(2, stack.peek());

        stack.push(3);
        assertEquals(3, stack.peek());
    }

    @Test
    public void testSize() {
        assertEquals(0, stack.size());

        stack.push(1);
        assertEquals(1, stack.size());

        stack.push(2);
        assertEquals(2, stack.size());

        stack.push(3);
        assertEquals(3, stack.size());

        stack.push(4);
        assertEquals(4, stack.size());

        stack.push(5);
        assertEquals(5, stack.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(stack.isEmpty());

        stack.push(1);
        assertFalse(stack.isEmpty());

    }
}
