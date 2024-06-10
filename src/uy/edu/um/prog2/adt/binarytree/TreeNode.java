package uy.edu.um.prog2.adt.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pegardan
 *
 */
public class TreeNode<T extends Comparable<T>> {

	private T value;

	private TreeNode<T> left;

	private TreeNode<T> right;

	public TreeNode(T oValue) {
		this.value = oValue;
	}

	public void add(T oElement) {
		int nValue = oElement.compareTo(value);
		TreeNode<T> oElementToAdd = new TreeNode<T>(oElement);

		if (nValue > 0) {

			if (right == null) {

				right = oElementToAdd;

			} else {

				right.add(oElement);

			}

		} else {

			if (left == null) {

				left = oElementToAdd;

			} else {

				left.add(oElement);

			}
		}

	}

	public TreeNode<T> remove(T oElement) {
		int nValue = ((Comparable<T>) oElement).compareTo(value);

		if (nValue > 0) {

			if (right != null) {

				right = right.remove(oElement);

			}

		} else if (nValue < 0) {

			if (left != null) {

				left = left.remove(oElement);

			}
		} else if (left != null && right != null) {

			// Encontre el elemento a eliminar

			value = right.findMin();

			right = right.remove(value);

		} else {

			if (left != null) {

				return left;

			} else {

				return right;

			}

		}

		return this;
	}

	public List<T> inOrderTraverse() {
		List<T> colList = new ArrayList<T>();

		if (left != null) {

			colList.addAll(left.inOrderTraverse());

		}

		colList.add(value);

		if (right != null) {

			colList.addAll(right.inOrderTraverse());

		}

		return colList;
	}

	public List<T> preOrderTraverse() {
		List<T> colList = new ArrayList<T>();

		colList.add(getValue());

		if (left != null) {

			colList.addAll(left.preOrderTraverse());

		}

		if (right != null) {

			colList.addAll(right.preOrderTraverse());

		}

		return colList;
	}

	public List<T> postOrderTraverse() {
		List<T> colList = new ArrayList<T>();

		if (left != null) {

			colList.addAll(left.postOrderTraverse());

		}

		if (right != null) {

			colList.addAll(right.postOrderTraverse());

		}

		colList.add(getValue());

		return colList;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public TreeNode<T> getLeft() {
		return left;
	}

	public void setLeft(TreeNode<T> left) {
		this.left = left;
	}

	public TreeNode<T> getRight() {
		return right;
	}

	public void setRigth(TreeNode<T> rigth) {
		this.right = rigth;
	}

	public T findMin() {
		T oReturn = value;

		if (left != null) {

			oReturn = left.findMin();

		}

		return oReturn;
	}

}
