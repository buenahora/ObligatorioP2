package Tests;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.adt.linkedlist.MyList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class linkedlisttest {

    private MyList<Integer> lista;
    @Before
    public void setup() {
        lista = new MyLinkedListImpl<>();
    }

    @Test
    public void testAdd() {
        lista.add(5);
        assertEquals(1, lista.size());
        assertEquals(Integer.valueOf(5), lista.get(0));
    }

    @Test
    public void testRemove() {
        lista.add(5);
        lista.remove(5);
        assertEquals(0, lista.size());
    }

    @Test
    public void testSize() {
        lista.add(5);
        lista.add(10);
        assertEquals(2, lista.size());
    }

    @Test
    public void testGet() {
        lista.add(5);
        lista.add(10);
        lista.add(15);
        lista.add(20);
        assertEquals(Integer.valueOf(15), lista.get(2));
    }

    @Test
    public void testContains() {
        lista.add(5);
        lista.add(10);
        assertTrue(lista.contains(10));
        assertFalse(lista.contains(15));
    }

    @Test
    public void testGetPrimero() {
        lista.add(5);
        lista.add(10);
        lista.add(15);
        lista.add(20);
        assertEquals(Integer.valueOf(5), lista.getFirst().getValue());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(lista.isEmpty());
        lista.add(5);
        assertFalse(lista.isEmpty());
    }
    @Test(expected = NullPointerException.class)
    public void testGetVacio() throws NullPointerException {
        lista.get(10);
    }

}
