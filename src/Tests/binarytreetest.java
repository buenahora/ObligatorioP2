package Tests;
import TADs.binarytree.MySearchBinaryTree;
import TADs.binarytree.MySearchBinaryTreeImpl;

import TADs.linkedlist.MyList;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;


public class binarytreetest {
    MySearchBinaryTree<Integer, Object> arbol;
    @Before
    public void setup() {
        arbol = new MySearchBinaryTreeImpl<>();
    }

    @Test
    public void testAdd() {
        arbol.add(5, 5);
        arbol.add(2, 2);
        arbol.add(7, 7);

        assertEquals(5, arbol.getRoot().getValue());
        assertEquals(2, arbol.getRoot().getLeft().getValue());
        assertEquals(7, arbol.getRoot().getRight().getValue());
    }


    @Test
    public void testAddAndFind() {
        arbol.add(5, "five");
        assertEquals("five", arbol.find(5));
    }

    @Test
    public void testRemove() {
        arbol.add(5, 5);
        arbol.add(2, 2);
        arbol.add(7, 7);

        arbol.remove(5);
        assertEquals(7, arbol.getRoot().getValue());

        arbol.remove(7);
        assertEquals(2, arbol.getRoot().getValue());

        arbol.remove(2);

        arbol.add(5, 5);
        arbol.add(2, 2);
        arbol.add(7, 7);

        arbol.remove(2);
        arbol.remove(7);

        assertNull(null, arbol.getRoot().getLeft());
        assertNull(null, arbol.getRoot().getRight());

    }

    @Test
    public void testContains() {
        arbol.add(5, "five");
        assertTrue(arbol.contains(5));
        assertFalse(arbol.contains(10));
    }

    @Test
    public void testInOrder() {
        arbol.add(5, "five");
        arbol.add(10, "ten");
        arbol.add(3, "three");
        MyList<Integer> inOrderList = arbol.inOrder();
        assertEquals(Integer.valueOf(3), inOrderList.get(0));
        assertEquals(Integer.valueOf(5), inOrderList.get(1));
        assertEquals(Integer.valueOf(10), inOrderList.get(2));
    }


    @Test
    public void testPostOrder() {
        // Similar to the inOrder test, but with postOrder
    }

    @Test
    public void testPreOrder() {
        // Similar to the inOrder test, but with preOrder
    }

    @Test
    public void testGetRoot() {
        arbol.add(5, "five");
        assertEquals(Integer.valueOf(5), arbol.getRoot().getKey());
        assertEquals("five", arbol.getRoot().getValue());
    }





}
