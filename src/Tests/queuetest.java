package Tests;
import Classes.Exceptions.EmptyQueueException;
import Classes.Exceptions.EmptyStackException;
import TADs.linkedlist.MyLinkedListImpl;
import TADs.queue.MyQueue;
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
}
