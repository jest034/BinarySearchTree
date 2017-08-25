
// Implements a BST with TreeNode nodes
import java.util.Stack;
import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
// Normally, TreeNode and MyTreeSet would be "generic" (type-specific)
// classes and hold Comparable objects, but this is beyond the scope of
// the Java Methods book. Without @SuppressWarnings, this class would give
// three "Unchecked cast" warnings.
public class MyBST {
	private TreeNode root; // holds the root of this BST
	// Constructor: creates an empty BST.

	public MyBST() {
		root = null;
	}

	// Returns true if this BST contains value; otherwise returns false.
	public boolean contains(Object value) {
		return contains(root, value);
	}

	// Adds value to this BST, unless this tree already holds value.
	// Returns true if value has been added; otherwise returns false.
	public boolean add(Object value) {
		if (contains(value))
			return false;
		root = add(root, value);
		return true;
	}

	// Removes value from this BST. Returns true if value has been
	// found and removed; otherwise returns false.
	public boolean remove(Object value) {
		if (!contains(value))
			return false;
		root = remove(root, value);
		return true;
	}

	// Returns a string representation of this BST.
	public String toString() {
		String str = toString(root);
		if (str.endsWith(", "))
			str = str.substring(0, str.length() - 2);
		return "[" + str + "]";
	}

	// *************** Private helper methods: *********************
	// Returns true if the BST rooted at node contains value;
	// otherwise returns false (recursive version).
	private boolean contains(TreeNode node, Object value) {
		if (node == null)
			return false;
		else {
			int diff = ((Comparable<Object>) value).compareTo(node.getValue());
			if (diff == 0)
				return true;
			else if (diff < 0)
				return contains(node.getLeft(), value);
			else // if (diff > 0)
				return contains(node.getRight(), value);
		}
	}

	/*
	 * // Iterative version: private boolean contains(TreeNode node, Object value) {
	 * while (node != null) { int diff =
	 * ((Comparable<Object>)value).compareTo(node.getValue()); if (diff == 0) return
	 * true; else if (diff < 0) node = node.getLeft(); else // if (diff > 0) node =
	 * node.getRight(); } return false; }
	 */
	// Adds value to the BST rooted at node. Returns the
	// root of the new tree.
	// Precondition: the tree rooted at node does not contain value.
	private TreeNode add(TreeNode node, Object value) {
		if (root == null) {
			TreeNode one = new TreeNode(value);
			return one;
		}
		int diff = ((Comparable<Object>) value).compareTo(node.getValue());
		if (diff < 0) {
			if (node.getLeft() == null) {
				TreeNode one = new TreeNode(value);
				node.setLeft(one);
				return root;
			}
			add(node.getLeft(), value);
		} else {
			if (node.getRight() == null) {
				TreeNode one = new TreeNode(value);
				node.setRight(one);
				return root;
			}
			add(node.getRight(), value);
		}
		return root;
	}

	// Removes value from the BST rooted at node.
	// Returns the root of the new tree.
	// Precondition: the tree rooted at node contains value.
	private TreeNode remove(TreeNode node, Object value) {
		int diff = ((Comparable<Object>) value).compareTo(node.getValue());
		if (diff < 0) {
			if (node.getValue() == value) {
				return removeRoot(node);
			}
			node.setLeft(remove(node.getLeft(), value));
		} else {
			if (node.getValue() == value) {
				return removeRoot(node);
			}
			node.setRight(remove(node.getRight(), value));
		}
		return node;
	}

	// Removes the root of the BST rooted at root
	// replacing it with the smallest node from the right subtree.
	// Returns the root of the new tree.
	private TreeNode removeRoot(TreeNode root) // set root to null if last
	{
		/* YOUR CODE HERE */
		if (root.getRight() == null && root.getLeft() != null) {
			root.setValue(root.getLeft().getValue());
			if (root.getLeft().getLeft() == null && root.getLeft().getRight() == null)
				root.setLeft(null);
			else if (root.getLeft().getLeft() != null && root.getLeft().getRight() == null)
				root.setLeft(root.getLeft().getLeft());
			else if (root.getLeft().getLeft() == null && root.getLeft().getRight() != null) {
				root.setRight(root.getLeft().getRight());// changed this to setRight() from setLeft()
				root.setLeft(null);
			} else if (root.getLeft().getLeft() != null && root.getLeft().getRight() != null) {
				root.setLeft(root.getLeft().getLeft());
				root.setLeft(root.getLeft().getRight());
			}
			return root;
		} else if (root.getRight() == null && root.getLeft() == null) {
			return null;
		} else if (root.getRight().getLeft() == null) {
			root.setValue(root.getRight().getValue());
			root.setRight(null);
			return root;
		}
		TreeNode temp = root.getRight();
		while (temp.getLeft().getLeft() != null) {
			temp = temp.getLeft();
		}
		root.setValue(temp.getLeft().getValue());
		if (temp.getLeft().getRight() != null) {
			temp.setLeft(temp.getLeft().getRight());
		}
		temp.setLeft(null);
		return root;
	}

	// Returns a string representation of the tree rooted at node.
	private String toString(TreeNode node) {
		if (node == null)
			return "";
		else
			return toString(node.getLeft()) + node.getValue() + ", " + toString(node.getRight());
	}
}