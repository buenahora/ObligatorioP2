package TADs.binarytree;

import TADs.linkedlist.MyList;

public class TreeNode<K extends Comparable<K>, V> {

	private K key;
	
	private V value;

	private TreeNode<K, V> left;

	private TreeNode<K, V> right;

	public TreeNode(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public void add(K key, V value) {		
		TreeNode<K, V> elementToAdd = new TreeNode<>(key, value);
		if (key.compareTo(this.key) > 0) {

			if (right == null) {

				right = elementToAdd;

			} else {

				right.add(key, value);

			}
		} else {
			if (left == null) {

				left = elementToAdd;
			} else {

				left.add(key, value);
			}
		}

	}

	public TreeNode<K, V> remove(K key) {
        TreeNode<K, V> elementToReturn = this;

		if (key.compareTo(this.key) > 0) {

			if (right != null) {

				right = right.remove(key);

			}

		} else if (key.compareTo(this.key) < 0) {

			if (left != null) {

				left = left.remove(key);

			}
		} else if (left != null && right != null) {

			// Encontre el elemento a eliminar

			TreeNode<K, V> min = right.findMin();
			
			this.key = min.getKey();
			this.value = min.getValue();

			right = right.remove(min.getKey());

		} else {

			if (left != null) {

                elementToReturn = left;

			} else {

                elementToReturn = right;

			}

		}

		return elementToReturn;
	}

	public void inOrderTraverse(MyList<K> traverse) {
		if (left != null) {

			left.inOrderTraverse(traverse);

		}

		traverse.add(this.getKey());

		if (right != null) {

			right.inOrderTraverse(traverse);

		}
	}
	//postOrden
	public void postOrderTraverse(MyList<K> traverse) {
		if (left != null) {

			left.inOrderTraverse(traverse);

		}

		if (right != null) {

			right.inOrderTraverse(traverse);

		}
		traverse.add(this.getKey());
	}
	//preOrden
	public void preOrderTraverse(MyList<K> traverse) {
		traverse.add(this.getKey());

		if (left != null) {

			left.inOrderTraverse(traverse);

		}

		if (right != null) {

			right.inOrderTraverse(traverse);

		}
	}

	public V getValue() {
		return value;
	}
	
	public K getKey() {
		return key;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public TreeNode<K, V> getLeft() {
		return left;
	}

	public void setLeft(TreeNode<K, V> left) {
		this.left = left;
	}

	public TreeNode<K, V> getRight() {
		return right;
	}

	public void setRigth(TreeNode<K, V> rigth) {
		this.right = rigth;
	}

	public TreeNode<K, V> findMin() {
		 TreeNode<K, V> oReturn = this;

		if (left != null) {

			oReturn = left.findMin();

		}

		return oReturn;
	}

}
