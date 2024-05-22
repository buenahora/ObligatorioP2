package TADs.binarytree;

import TADs.linkedlist.MyList;

import java.util.List;

/**
 * @author pegardan
 *
 */
public interface BinaryTree<T extends Comparable<T>> {

	void add(T oElement);

	void remove(T oElement);

	boolean contains(T oElement);

	T find(T oElement);

	List<T> preOrder();

	List<T> posOrder();

	List<T> inOrder();

}
