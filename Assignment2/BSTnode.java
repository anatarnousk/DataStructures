/*  Assignment 2 for Data Structures
 * 	Author: Anastasiya Tarnouskaya
 * 	Date: February 16th, 2015
 * 
 * 
 */

// The BST node class makes nodes to be used by the Binary Search Tree class.  
public class BSTnode{
	int val;
	BSTnode left;
	BSTnode right;

	
	//BST node constructor
	BSTnode(int val){
		this.val = val;
		left = null;
		right = null;
	}
	
	//gets integer node values from BSTs
	public static int getVal(BSTnode node){
		return node.val;
	}
		
}//end class
