package Tests;
import Classes.Exceptions.DuplicateKeyTreeException;
import Classes.Exceptions.EmptyQueueException;
import TADs.binarytree.SearchBinaryTreeImpl;

import TADs.linkedlist.MyList;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


public class binarytreetest {
    SearchBinaryTreeImpl<Integer> arbol;
    @Before
    public void setup() {
        arbol = new SearchBinaryTreeImpl<>();
    }

    @Test
    public void testAdd() throws DuplicateKeyTreeException {
        arbol.add(5);
        arbol.add(2);
        arbol.add(7);

        assertEquals(Integer.valueOf(5), arbol.root.getValue());
        assertEquals(Integer.valueOf(2), arbol.root.getLeft().getValue());
        assertEquals(Integer.valueOf(7), arbol.root.getRight().getValue());
    }

    @Test(expected = DuplicateKeyTreeException.class)
    public void testAddDuplicateKey() throws DuplicateKeyTreeException {
        arbol.add(5);
        arbol.add(5);

    }


    @Test
    public void testAddAndFind() throws DuplicateKeyTreeException {
        arbol.add(5);
        assertEquals(Integer.valueOf(5), arbol.find(5));
    }

//    @Test
//    public void testRemove() {
//        arbol.add(5);
//        arbol.add(2);
//        arbol.add(7);
//
//        arbol.remove(5);
//        assertEquals(Integer.valueOf(7), arbol.root.getValue());
//
//        arbol.remove(7);
//        assertEquals(Integer.valueOf(2), arbol.root.getValue());
//
//        arbol.remove(2);
//
//        arbol.add(5);
//        arbol.add(2);
//        arbol.add(7);
//
//        arbol.remove(2);
//        arbol.remove(7);
//
//        assertNull(arbol.root.getLeft());
//        assertNull(arbol.root.getRight());
//
//    }

    @Test
    public void testContains() throws DuplicateKeyTreeException {
        arbol.add(5);
        assertTrue(arbol.contains(5));
        assertFalse(arbol.contains(10));
    }

    @Test
    public void testInOrder() throws DuplicateKeyTreeException {
        arbol.add(5);
        arbol.add(10);
        arbol.add(3);
        arbol.add(4);
        arbol.add(8);

        List<Integer> inOrderList = arbol.inOrder();
        assertEquals(Integer.valueOf(3), inOrderList.get(0));
        assertEquals(Integer.valueOf(4), inOrderList.get(1));
        assertEquals(Integer.valueOf(5), inOrderList.get(2));
        assertEquals(Integer.valueOf(8), inOrderList.get(3));
        assertEquals(Integer.valueOf(10), inOrderList.get(4));
    }


    @Test
    public void testPostOrder() throws DuplicateKeyTreeException {
        arbol.add(5);

        arbol.add(10);
        arbol.add(3);

        arbol.add(9);
        arbol.add(11);

        arbol.add(2);
        arbol.add(4);
        List<Integer> postOrderList = arbol.posOrder();

        System.out.println(postOrderList);

        assertEquals(Integer.valueOf(2), postOrderList.get(0));
        assertEquals(Integer.valueOf(4), postOrderList.get(1));
        assertEquals(Integer.valueOf(3), postOrderList.get(2));

        assertEquals(Integer.valueOf(9), postOrderList.get(3));
        assertEquals(Integer.valueOf(11), postOrderList.get(4));
        assertEquals(Integer.valueOf(10), postOrderList.get(5));

        assertEquals(Integer.valueOf(5), postOrderList.get(6));

    }

    @Test
    public void testPreOrder() throws DuplicateKeyTreeException {
        arbol.add(5);

        arbol.add(10);
        arbol.add(3);

        arbol.add(9);
        arbol.add(11);

        arbol.add(2);
        arbol.add(4);

        List<Integer> preOrderList = arbol.preOrder();
        assertEquals(Integer.valueOf(5), preOrderList.get(0));
        assertEquals(Integer.valueOf(3), preOrderList.get(1));
        assertEquals(Integer.valueOf(2), preOrderList.get(2));
        assertEquals(Integer.valueOf(4), preOrderList.get(3));
        assertEquals(Integer.valueOf(10), preOrderList.get(4));
        assertEquals(Integer.valueOf(9), preOrderList.get(5));
        assertEquals(Integer.valueOf(11), preOrderList.get(6));

    }





}
