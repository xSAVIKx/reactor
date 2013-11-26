package ua.com.globallogic.basecamp.sergiichuk.binaryTree;

import java.util.List;

public interface Tree<T> {

    /**
     * Returns root element of tree
     * 
     * @return root element or null, if root is not set
     */
    public T getRoot();

    /**
     * Adds element to tree
     * 
     * @param element
     *            object to be added
     */
    public void add(T element);

    /**
     * Tries to remove given object from tree.
     * 
     * @param element
     *            object to be removed
     * @return removed object or null if object wasn't found
     */
    public T remove(T element);

    /**
     * Find all parents of given element
     * 
     * @param element
     *            element, which parents we need to find
     * @return list of element parent's or empty list, if element has no parents
     *         or element was not found
     */
    public List<T> find(T element);

    /**
     * Returns true if this tree contains the specified element.
     * 
     * @param element
     *            element whose presence in this tree is to be tested
     * @return true if this list contains the specified element
     */
    public boolean contains(T element);

    /**
     * Returns current amount of items in this tree
     * 
     * @return current amount of items in this tree
     */
    public int size();

    /**
     * Clear all elements in this tree
     */
    public void clear();

    /**
     * Prints all elements of this tree across Example:
     * 
     * <pre>
     * <pre>
     * tree:     5
     *        4     6
     *       2 4   5  7
     *      1   4    6 7
     * </pre>
     * 
     * will be printed as: 5 4 6 2 4 5 7 1 4 6 7 
     */
    public void printTreeAcross();

    /**
     * Prints all elements of this tree in depth Example:
     * 
     * <pre>
     * tree:     5
     *        4     6
     *       2 4   5  7
     *      1   4    6 7
     * </pre>
     * 
     * will be printed as: 5 4 2 1 4 4 6 5 7 6 7 
     */
    public void printTreeInDepth();

    /**
     * Prints all elements of this tree as pseudo-graphic rotated tree
     * 
     * <pre>
     * tree:     5
     *        4     6
     *       2 4   5  7
     *      1   4    6 7
     * </pre>
     * 
     * <pre>
     * will be printed as:
     *        7
     *      7
     *        6 
     *    6   
     *      5
     *  5
     *        4
     *      4
     *    4
     *      2
     *        1   
     * </pre>
     */
    public void printTree();
}
