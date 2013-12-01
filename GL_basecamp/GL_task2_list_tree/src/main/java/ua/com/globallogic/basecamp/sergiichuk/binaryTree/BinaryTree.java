package ua.com.globallogic.basecamp.sergiichuk.binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree<T extends Comparable<T>> implements Tree<T> {
    private Node<T> root;
    private int size;

    public BinaryTree() {

    }

    public BinaryTree(T rootData) {
	root = new Node<T>(rootData);
	size++;
    }

    public T getRoot() {
	if (root != null)
	    return root.data;
	return null;
    }

    /**
     * @throws IllegalArgumentException
     *             if element value is null
     */
    public void add(T element) {
	if (element == null)
	    throw new IllegalArgumentException("Element can't be null");
	if (root != null) {
	    if (element.compareTo(root.data) < 0) {
		addElementToLeaf(root.leftLeaf, root, element);
	    } else if (element.compareTo(root.data) >= 0) {
		addElementToLeaf(root.rightLeaf, root, element);
	    }
	} else {
	    root = new Node<T>(element);
	}
	size++;
    }

    /**
     * This method set element to given leaf of Tree
     * 
     * @param leaf
     *            leaf to which element have to be set
     * @param element
     *            element which have to be set
     */
    private void addElementToLeaf(Node<T> leaf, Node<T> parent, T element) {
	if (leaf == null) {
	    leaf = new Node<T>(element);
	    leaf.parentNode = parent;
	    if (element.compareTo(parent.data) < 0) {
		parent.leftLeaf = leaf;
	    } else if (element.compareTo(parent.data) >= 0) {
		parent.rightLeaf = leaf;
	    }
	} else if (element.compareTo(leaf.data) < 0) {
	    addElementToLeaf(leaf.leftLeaf, leaf, element);
	} else {
	    addElementToLeaf(leaf.rightLeaf, leaf, element);
	}
    }

    /**
     * Tries to remove element from leaf
     * 
     * @param leaf
     *            leaf to remove element from
     * @param parent
     *            parent leaf of 'leaf'
     * @param element
     *            element to remove from
     * @return removed element or null
     * 
     * @see Deleting of binary tree element, wiki - http://goo.gl/Ps3mcw
     */
    private T removeElementFromLeaf(Node<T> leaf, Node<T> parent, T element) {
	T elementToReturn = null;
	// if needed element was found in current leaf
	if (element.compareTo(leaf.data) == 0) {
	    elementToReturn = leaf.data;
	    // if both children are present
	    if (leaf.rightLeaf != null && leaf.leftLeaf != null) {
		// get left leaf of current leaf.rightLeaf
		Node<T> nodeToBeSwappedWithDeletedOne = findLeftLeafInSubtree(leaf.rightLeaf);
		// we'll swap data of current leaf with data or
		// currentLeaf->rightLeaf->leftLeaf
		leaf.data = nodeToBeSwappedWithDeletedOne.data;
		// if there is left leaf in leaf.rightleaf we'll recursively
		// delete that leaf, which data we have had exchange with
		// current leaf, giving currentLeaf->rightLeaf as parent of
		// exchanged one
		if (nodeToBeSwappedWithDeletedOne != leaf.rightLeaf)
		    removeElementFromLeaf(nodeToBeSwappedWithDeletedOne,
			    leaf.rightLeaf, nodeToBeSwappedWithDeletedOne.data);
		// else we'll recursively delete leaf, which data we have had
		// exchange with current leaf, giving currentLeaf as parent of
		// exchanged one
		else
		    removeElementFromLeaf(nodeToBeSwappedWithDeletedOne, leaf,
			    nodeToBeSwappedWithDeletedOne.data);
		// if leaf has only "left" child
	    } else if (leaf.leftLeaf != null && leaf.rightLeaf == null) {
		// if current leaf is leaf.parent left child
		if (leaf.equals(parent.leftLeaf)) {
		    parent.leftLeaf = leaf.leftLeaf;
		    // if current leaf is leaf.parent right child
		} else {
		    parent.rightLeaf = leaf.leftLeaf;
		}
		changeParentLink(leaf, parent);
		leaf.data = null;
		leaf = null;
		// if the leaf has only "right" child
	    } else if (leaf.rightLeaf != null && leaf.leftLeaf == null) {
		// if current leaf is leaf.parent left child
		if (leaf.equals(parent.leftLeaf)) {
		    parent.leftLeaf = leaf.rightLeaf;
		    // if current leaf is leaf.parent right child
		} else {
		    parent.rightLeaf = leaf.rightLeaf;
		}
		changeParentLink(leaf, parent);
		leaf.data = null;
		leaf = null;
		// if leaf has no childs
	    } else {
		if (leaf.equals(parent.leftLeaf)) {
		    parent.leftLeaf = null;
		} else {
		    parent.rightLeaf = null;
		}
		leaf.data = null;
		leaf = null;
	    }
	    // if element to remove is 'smaller' than current leaf data and
	    // current leaf has left leaf
	} else if (element.compareTo(leaf.data) < 0 && leaf.leftLeaf != null) {
	    elementToReturn = removeElementFromLeaf(leaf.leftLeaf, leaf,
		    element);
	    // if element to remove is 'bigger' than current leaf data and
	    // current leaf has right leaf
	} else if (element.compareTo(leaf.data) > 0 && leaf.rightLeaf != null) {
	    elementToReturn = removeElementFromLeaf(leaf.rightLeaf, leaf,
		    element);
	}
	return elementToReturn;
    }

    /**
     * Return left leaf of root if it is present
     * 
     * @param subtreeRoot
     * @return left leaf of root if it is present or root if not
     */
    private Node<T> findLeftLeafInSubtree(Node<T> subtreeRoot) {
	Node<T> currentNode = subtreeRoot;
	if (currentNode.leftLeaf != null)
	    currentNode = currentNode.leftLeaf;
	return currentNode;
    }

    /**
     * Change parent link of leafToChange children links to parent, if children
     * are present
     * 
     * @param leafToChange
     *            leaf, which children links will be changed
     * @param parent
     *            parent to which children parent links will be set
     */
    private void changeParentLink(Node<T> leafToChange, Node<T> parent) {
	if (parent != null && leafToChange != null) {
	    if (leafToChange.leftLeaf != null) {
		leafToChange.leftLeaf.parentNode = parent;
	    }
	    if (leafToChange.rightLeaf != null) {
		leafToChange.rightLeaf.parentNode = parent;
	    }
	}
    }

    /**
     * @throws IllegalArgumentException
     *             if element value is null
     */
    public T remove(T element) {
	if (element == null)
	    throw new IllegalArgumentException("Element can't be null");
	if (root != null) {
	    T removedElement = removeElementFromLeaf(root, root, element);
	    if (removedElement != null)
		size--;
	    return removedElement;
	}
	return null;

    }

    public List<T> find(T element) {
	List<T> parentList = new ArrayList<T>();
	if (root != null) {
	    final Node<T> startNode = root;
	    Node<T> desiredNode = getNodeByDataValue(startNode, element);
	    while (desiredNode.parentNode != null) {
		parentList.add(desiredNode.parentNode.data);
		desiredNode = desiredNode.parentNode;
	    }
	}
	return parentList;
    }

    /**
     * @throws IllegalArgumentException
     *             if element value is null
     */
    public boolean contains(T element) {
	if (element == null)
	    throw new IllegalArgumentException("Element can't be null");
	if (root != null) {
	    final Node<T> startNode = root;
	    return getNodeByDataValue(startNode, element) != null;
	}
	return false;
    }

    /**
     * Return Node<T> by element value, starting search of element from rootNode
     * 
     * @param rootNode
     *            root node to start search from
     * @param element
     *            element value to be searched in nodes
     * @return Node<T> with given element value or null, if no such Node was
     *         found
     */
    private Node<T> getNodeByDataValue(Node<T> rootNode, T element) {
	if (element.equals(rootNode.data))
	    return rootNode;
	else {
	    if (element.compareTo(rootNode.data) < 0
		    && rootNode.leftLeaf != null) {
		return getNodeByDataValue(rootNode.leftLeaf, element);
	    } else if (element.compareTo(rootNode.data) > 0
		    && rootNode.rightLeaf != null) {
		return getNodeByDataValue(rootNode.rightLeaf, element);
	    }
	}
	return null;
    }

    public int size() {
	return size;
    }

    /**
     * Recursively delete nodes from given rootNode
     * 
     * @param rootNode
     *            root node from which start deleting all children
     */
    private void deleteLeafs(Node<T> rootNode) {
	if (rootNode.leftLeaf != null) {
	    deleteLeafs(rootNode.leftLeaf);
	}
	if (rootNode.rightLeaf != null)
	    deleteLeafs(rootNode.rightLeaf);
	rootNode.leftLeaf = null;
	rootNode.rightLeaf = null;
	rootNode.data = null;
	rootNode = null;
    }

    public void clear() {
	if (root != null) {
	    deleteLeafs(root);
	    size = 0;
	}
    }

    /**
     * Prints data value of given node
     * 
     * @param node
     *            node to print data value of
     */
    private void printNode(Node<T> node) {
	System.out.print(node.data + " ");
    }

    private void printTreeLeaves(Node<T> rootNode, int level) {
	if (rootNode != null) {

	    printTreeLeaves(rootNode.rightLeaf, level + 1);
	    for (int i = 0; i < level; i++)
		System.out.print("   ");
	    printNode(rootNode);
	    System.out.println();
	    printTreeLeaves(rootNode.leftLeaf, level + 1);

	}

    }

    public void printTree() {
	if (root != null) {
	    final Node<T> startNode = root;
	    printTreeLeaves(startNode, 0);
	}
    }

    public void printTreeAcross() {
	if (root != null) {
	    Queue<Node<T>> queue = new LinkedList<Node<T>>();
	    final Node<T> startNode = root;

	    queue.add(startNode);
	    while (!queue.isEmpty()) {
		Node<T> x = queue.poll();
		printNode(x);
		if (x.leftLeaf != null) {
		    queue.add(x.leftLeaf);
		}
		if (x.rightLeaf != null) {
		    queue.add(x.rightLeaf);
		}
	    }
	}
    }

    private void printLeafChildrenInDepth(Node<T> rootNode) {
	if (rootNode.leftLeaf != null) {
	    printNode(rootNode.leftLeaf);
	    printLeafChildrenInDepth(rootNode.leftLeaf);
	}
	if (rootNode.rightLeaf != null) {
	    printNode(rootNode.rightLeaf);
	    printLeafChildrenInDepth(rootNode.rightLeaf);
	}
    }

    public void printTreeInDepth() {
	if (root != null) {
	    final Node<T> startNode = root;
	    printNode(startNode);
	    printLeafChildrenInDepth(startNode);
	}
    }

    @SuppressWarnings("hiding")
    private class Node<T extends Comparable<T>> {
	private Node<T> leftLeaf;
	private Node<T> rightLeaf;
	private Node<T> parentNode;
	private T data;

	public Node(T data) {
	    this.data = data;
	}
    }

}
