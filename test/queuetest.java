package Tests;
import Classes.Exceptions.QueueExceptions.EmptyQueueException;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.adt.queue.MyQueue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class queuetest {
    private MyQueue<Integer> queue;

    @Before
    public void setup() {
        queue = new MyLinkedListImpl<>();
    }

    @Test
    public void testEnqueue() {
        queue.enqueue(1);
        Assert.assertEquals(1, queue.size());

        queue.enqueue(1);
        Assert.assertEquals(2, queue.size());
    }

    @Test
    public void testDequeue() throws EmptyQueueException {
        queue.enqueue(1);
        int dequeuedValue = queue.dequeue();
        Assert.assertEquals(1, dequeuedValue);
    }

    @Test(expected = EmptyQueueException.class)
    public void testDequeueVacio() throws EmptyQueueException {
        queue.dequeue();
    }

    @Test
    public void testContains() {
        queue.enqueue(1);
        Assert.assertTrue(queue.contains(1));
        Assert.assertFalse(queue.contains(2));
    }

    @Test
    public void testSize() {
        Assert.assertEquals(0, queue.size());
        queue.enqueue(1);
        Assert.assertEquals(1, queue.size());
    }

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(queue.isEmpty());
        queue.enqueue(1);
        Assert.assertFalse(queue.isEmpty());
    }

    @Test
    public void testGet() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        Assert.assertEquals(Integer.valueOf(3), queue.get(0));
        Assert.assertEquals(Integer.valueOf(2), queue.get(1));
        Assert.assertEquals(Integer.valueOf(1), queue.get(2));
    }

    @Test(expected = NullPointerException.class)
    public void testGetVacio() throws NullPointerException {
        queue.get(5);
    }
}
