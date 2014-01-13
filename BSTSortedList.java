import java.util.Iterator;
/**
 * This class is designed to create a Binary Search Tree/Sorted List
 * to store Keys and provide functionality implemented
 * in the form of Insert, delete, lookup, size and isEmpty (and their
 * relative helper methods). Also can be used to create an iterator.
 * 
 * bugs: None known
 * 
 * @author Steven Volocyk
 * 
 */
public class BSTSortedList<K extends Comparable<K>> implements SortedListADT<K> {
    private BSTnode<K> root;  // the root node
    private int numItems;     // the number of items in the sorted list
    /**
     * Constructor for the binary search tree
     */
    public void BST() {
    	root = null;
    }
    /**
     * Method for inserting a new node and subsequent Key into the BST
     * @param k Key
     */
    public void insert(K key) {
    	root = insert(root, key);
    }
    /**
     * Private helper method for insertion
     * Compares Keys for organization of tree
     * and will increment numItems if successful addition.
     * @param n BST node root
     * @param key 
     * @return BST node
     */
	private BSTnode<K> insert(BSTnode<K> n, K key) {
	    if(n == null) {
	    	numItems++;
	    	return new BSTnode<K>(key, null, null);
	    		
	    }
	    if(n.getKey().compareTo(key) > 0) {
	    	n.setLeft(insert(n.getLeft(), key));
	    	return n;
	    }
	    else {
	    	n.setRight(insert(n.getRight(), key));
	    	return n;
	    }
	   }
    /**
     * Traverses the BST for a specific key and deletes it.
     * @param key
     * @return True if delete is successful, else false.
     */
	public boolean delete(K key) {
		int tmp = numItems;
		root = delete(root, key);
		if(tmp == numItems) {
			return false;
		} else{return true;}
	}	
	/**
	 * Helper method that will perform a post successor replacement after
	 * determining which node/key is to be removed.
	 * @param n root BST node
	 * @param key
	 * @return BST node used to replace key deleted.
	 */
	public BSTnode<K> delete(BSTnode<K> n, K key) {
		if (n == null) {
			return null;
		}
		if(n.getKey().compareTo(key) == 0) {
			if (n.getLeft() == null && n.getRight() == null) {
				numItems--;
				return null;
			}
			if (n.getLeft() == null) {
				numItems--;
				return n.getRight();
			}
			if (n.getRight() == null) {
				numItems--;
				return n.getLeft();
			}
				
			K smallVal = smallest(n.getRight());
			n.setKey(smallVal);
			n.setRight(delete(n.getRight(), smallVal));
			numItems--;
			return n;	
			}
		else if(n.getKey().compareTo(key) > 0 ) {
			n.setLeft(delete(n.getLeft(), key));
			return n;
		}
		else {
			n.setRight(delete(n.getRight(), key));
			return n;
		}
	}
	/**
	 * 	This is a helper method designed to find a suitable
	 *  pre successor replacement for the node being removed.
	 *  Finds the smallest key value.
	 * @param n node being removed
	 * @return key
	 */
	private K smallest(BSTnode<K> n) {
		if (n.getLeft() == null) {
			return n.getKey();
		} else {
			return smallest(n.getLeft());
		}

	}
	/**
	 * Traverses the BST to find a matching key.
	 * @param Key
	 * @return K key
	 */
	public K lookup(K key) {
		K value = lookup(root, key);		// not sure if this works correctly yet.
		return value;
	}
	/**
	 * Helper method for comparing keys and returning 
	 * matched or unmatched key.
	 * @param n root node
	 * @param key
	 * @return matching key or null if not found.
	 */
	private K lookup(BSTnode<K> n, K key) {
		if (n == null) {
			return null;
		}
		if (n.getKey().compareTo(key) == 0) {
			return n.getKey();
		}
		if (n.getKey().compareTo(key) > 0) {
			return lookup(n.getLeft(), key);
		}
		else {
			return lookup(n.getRight(), key);
		}			
	}
	/**
	 * @return number of items in BST
	 */
	public int size() {
		return numItems;
	}
		
	/**
	 * return true if tree is empty else false
	 */
	public boolean isEmpty() {
		if(numItems == 0) {
			return true;
		}
		return false;
	}
	/**
	 * @return new iterator over the BST.
	 */
	public Iterator<K> iterator() {
		return new BSTSortedListIterator<K>(root);
	}
	

}


