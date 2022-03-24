/*************************************************************************
 *  Binary Search Tree class.
 *  Adapted from Sedgewick and Wayne.
 *
 *  @version 3.0 1/11/15 16:49:42
 *
 *  @author Eligijus Skersonas
 *
 *************************************************************************/

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;             // root of BST
    /**
     * Private node class.
     */
    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    // is the symbol table empty?
    public boolean isEmpty() { return size() == 0; }

    // return number of key-value pairs in BST
    public int size() { return size(root); }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    /**
     *  Search BST for given key.
     *  Does there exist a key-value pair with given key?
     *
     *  @param key the search key
     *  @return true if key is found and false otherwise
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     *  Search BST for given key.
     *  What is the value associated with given key?
     *
     *  @param key the search key
     *  @return value associated with the given key if found, or null if no such key exists.
     */
    public Value get(Key key) { return get(root, key); }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

    /**
     *  Insert key-value pair into BST.
     *  If key already exists, update with new value.
     *
     *  @param key the key to insert
     *  @param val the value associated with key
     */
    public void put(Key key, Value val) {
        if (val == null) { delete(key); return; }
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    /**
     * Tree height.
     *
     * Asymptotic worst-case running time using Theta notation: Theta(N)
     *	worst case the BST is full(i.e all leaves are at the last level and the last level is full) 
     *	the function will go through all nodes since size(x.left) is always = size(x.right)
     * @return the number of links from the root to the deepest leaf.
     *
     * Example 1: for an empty tree this should return -1.
     * Example 2: for a tree with only one node it should return 0.
     * Example 3: for the following tree it should return 2.
     *   B
     *  / \
     * A   C
     *      \
     *       D
     */
    public int height() {
    	//
    	if(isEmpty()) return -1;
    	else if(size(root) == 1) return 0;
    	else return height(root, 0);

    }
    
    /**
     * if the no. of nodes to the left of the current node is less than the right hand side then we will not look at the left
     * if the no. of node to the left of the current node is more than the right hand side then we will not look at the right
     * otherwise if the no. of nodes on the left equals the no. of nodes on the right then we will look at both sides
     * best case -> Theta(Height)
     * worst case -> Theta(N)
     * @param x
     * @param currentHeight
     * @return height
     */
    private int height(Node x, int currentHeight) {
    	if(x == null) return currentHeight;
    	
    	if(size(x.left) < size(x.right)) return height(x.right, currentHeight+1);
    	else if(size(x.left) > size(x.right)) return height(x.left, currentHeight+1);
    	else {
    		int leftHeight, rightHeight;
    		leftHeight = height(x.left, (x.left == null)?currentHeight: currentHeight+1);
    		rightHeight = height(x.right, (x.right == null)?currentHeight: currentHeight+1);
    		return (leftHeight > rightHeight)? leftHeight: rightHeight;
    	}
    			
    }
    /**
     * Median key.
     * If the tree has N keys k1 < k2 < k3 < ... < kN, then their median key 
     * is the element at position (N-1)/2 (where "/" here is integer division)
     *
     * @return the median key, or null if the tree is empty.
     */
    public Key median() {
    	if((size(root)-1)/2 >= size()) return null;
    	return median(root, (size(root)-1)/2);
    }
    
    /**
     * if the node passed in is null return null
     * otherwise 
     * get the rank of t (current Node) and see if its less or more than the rankOfMedian or equal to it
     * 
     * if less
     * then we will go to the right subtree
     * here we pass in rankOfMedian-t-1 as a parameter because we lose the rank of the parent node since we do not store it
     * hence we the current node acts like a root of a new BST and all the ranks of this "pseudo new BST" have their ranks 
     * deduced by the rank of the parent node which is a constant hence we can still use these new values to find the median
     * 
     * if more 
     * then we will go to the left subtree
     * here there is no need the know the rank of the parent hence we do not deduce anything in the parameter
     * 
     * if equal 
     * return the key of the median
     * 
     * @param x
     * @param rankOfMedian
     * @return median
     */
    private Key median(Node x, int rankOfMedian) {
    	int t = size(x.left); // get rank of t
    	if (t > rankOfMedian) return median(x.left, rankOfMedian); 
    	else if (t < rankOfMedian) return median(x.right, rankOfMedian-t-1);
    	else return x.key;
    }


    /**
     * Print all keys of the tree in a sequence, in-order.
     * That is, for each node, the keys in the left subtree should appear before the key in the node.
     * Also, for each node, the keys in the right subtree should appear before the key in the node.
     * For each subtree, its keys should appear within a parenthesis.
     *
     * Example 1: Empty tree -- output: "()"
     * Example 2: Tree containing only "A" -- output: "(()A())"
     * Example 3: Tree:
     *   B
     *  / \
     * A   C
     *      \
     *       D
     *
     * output: "((()A())B(()C(()D())))"
     *
     * output of example in the assignment: (((()A(()C()))E((()H(()M()))R()))S(()X()))
     *
     * @return a String with all keys in the tree, in order, parenthesized.
     */
    public String printKeysInOrder() {
      if (isEmpty()) return "()";
      else if (size(root) == 1) return "(()" + root.key + "())";
      else {String output = ""; return printKeysInOrder(root, output);}
    }
    
    /**
     * 
     * @param x
     * @param output
     * @return output
     */
    private String printKeysInOrder(Node x, String output) {
    	output += "(";
    	if(x == null) return output + ")";
    	output = printKeysInOrder(x.left, output);
    	output += x.key;
    	output = printKeysInOrder(x.right,output);
    	output += ")";
    	return output;
    }
    
    /**
     * Pretty Printing the tree. Each node is on one line -- see assignment for details.
     *
     * @return a multi-line string with the pretty ascii picture of the tree.
     */
    public String prettyPrintKeys() {
    	if(root == null) return "-null\n";
    	else return prettyPrint(root, "");
    }
    
    /**
     * prefix is computed by concatenating prefix's current state with " |" if we go to the left subtree
     * 																   "  " if we go to the right subtree
     * then when we exit a function in the call stack then a previous version of prefix is restored hence why it works
     * when a we evaluate a subtree that has children and then all of a sudden the prefix goes to a previous version
     * 
     * we add prefix and the keys and \n where needed to produce the desired output 
     * 
     * @param node
     * @param prefix
     * @return output
     */
    private String prettyPrint(Node node, String prefix) {
    	String output = "";
    	if(node == null) {output += prefix + "-null\n"; return output;}
    	output += prefix + "-" + node.key + "\n";
    	output += prettyPrint(node.left, prefix + " |");
    	output += prettyPrint(node.right,prefix + "  ");
    	return output;
    }



    /**
     * Deletes a key from a tree (if the key is in the tree).
     * Note that this method works symmetrically from the Hibbard deletion:
     * If the node to be deleted has two child nodes, then it needs to be
     * replaced with its predecessor (not its successor) node.
     *
     * @param key the key to delete
     */
    public void delete(Key key) {
    	 root = delete(root, key);
    }
    
    /**
     * Using the example from the lecture notes I manipulated the method to replace the deleted node with its predecessor
     * 
     * First of all we find the key to be deleted 
     * then when found we go into the else statement and if the node has one child replace the link with the parent node
     * with a link to its child then the node to be deleted is garbage collected
     * however
     * if the node has two children then we will keep a copy of the current node in t and then look for its predecessor using
     * max(x.left) which looks for the max node in the left subtree
     * max will return the predecessor and we will store it in x
     * we will then set the left link of the predecessor to be the node to the left of t (deleteMax(x.right))
     *                  the right link of the predecessor to be the node to the right of t (t.right)
     * t then becomes out of scope and the node is garbage collected
     * 
     * the size of each node is updated 
     * 
     * @param x
     * @param key
     * @return
     */
    private Node delete(Node x, Key key) {
    	 if (x == null) return null;
    	 int cmp = key.compareTo(x.key);
    	 
    	 if (cmp < 0) x.left = delete(x.left, key);
    	 else if (cmp > 0) x.right = delete(x.right, key);
    	 else {
	    	 if (x.right == null) return x.left;
	    	 if (x.left == null) return x.right;
	    	 
	    	 Node t = x;
	    	 x = max(t.left);
	    	 x.left = deleteMax(t.left);
	    	 x.right = t.right;
    	 }
    	 
    	 x.N = size(x.left) + size(x.right) + 1;
    	 return x;
    } 
    
    private Node max(Node t) {
    	if(t.right == null) return t;
    	return max(t.right);
    }
    
    private Node deleteMax(Node x) {
	    if (x.right == null) return x.left;
	    x.right = deleteMax(x.right);
	    x.N = 1 + size(x.left) + size(x.right);
	    return x;
    }

}
