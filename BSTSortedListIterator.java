///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  (WebDictionary.java)
// Files:            (BSTSortedListIterator.java)
// Semester:         CS302 Spring 2013
//
// Author:           (Steven Volocyk Sevenex@gmail.com)
// CS Login:         (volocyk)
// Lecturer's Name:  (Jim Skrentny)
// Lab Section:      (Lecture 2, 204 Educational Sciences)
//                   
// Credits:          
//////////////////////////// 80 columns wide /////////////////////////////////

import java.util.*;

/**
 * BSTSortedListIterator implements an iterator for a binary search tree (BST)
 * implementation of a Sorted List.
 * 
 * Author: Steven Volocyk
 * 
 * bugs: none known.
 */
public class BSTSortedListIterator<K extends Comparable<K>> 
implements Iterator<K> {

	private Stack<BSTnode<K>> stack; //for keeping track of nodes
	private BSTnode<K> mNode;
	/**
	 * Constructor for Iterator. 
	 * @param root
	 */
	public BSTSortedListIterator(BSTnode<K> root) {
		stack = new Stack<BSTnode<K>>();
		stackBuilder(root);				
		}
	/**
	 * Recursive helper method for building stack.
	 * @param n
	 */
	private void stackBuilder(BSTnode<K> n) {
		if (n == null) {
			return;
		}
		if (n.getRight() != null) {
			stackBuilder(n.getRight());	
		}	
		stack.push(n);
		if (n.getLeft() != null) { 
			stackBuilder(n.getLeft());			
		}	
	} 
	/**
	 * Checks to see if the stack is empty.
	 */
    public boolean hasNext() {
    	if (stack.isEmpty()) {
    		return false;
    	}
    	else {return true;}
    }
    /**
     * Pop's the next node on the stack and returns the key.
     */
    public K next() {
    	if(hasNext()) {
    		mNode = stack.pop(); 
   	    	return mNode.getKey();
    	}
    	return null;
    	
    	//Hint: Remember you are using pre-order traversal. The next node is at 
    	//the top of the stack. Pop it, then push its right child. Recursively 
    	//push all its left children onto the stack as done in the constructor.
    	//Finally, return the key from the popped node.
    }

    public void remove() {
        // DO NOT CHANGE: you do not need to implement this method
        throw new UnsupportedOperationException();
    }
}


