/*  Assignment 2 for Data Structures
 * 	Author: Anastasiya Tarnouskaya
 * 	Date: February 16th, 2015
 * 
 * 
 */

// The Binary Search Tree class makes, searches through and finds the depth of Binary
//Search Trees, with the BST's being made up of individual BSTnodes (see BST node class).
public class BinarySearchTree{
	BSTnode root;
	
	//this method inserts val into tree
	public static void insert(int val, BinarySearchTree tree){
		if (tree.root == null){
			tree.root = new BSTnode(val);
		}
		else{
			insert2(val,tree);
		}
	}//end insert
	
	//helper function for inserting values into a BST
	public static void insert2(int val, BinarySearchTree tree){
		
		BSTnode parent = tree.root;
		BSTnode current = parent;
		boolean left = true;
		
		while (true){
			
			//current node is null => end of tree reached => time to add val
			if (current == null){
				
				if (left == true){
					parent.left = new BSTnode(val);
					return;
				}//end if
				
				else{
					parent.right = new BSTnode(val);
					return;
				}//end else
				
			}//end if 
			
			parent = current;
			
			//current > value we want to add so go to left subtree
			if (BSTnode.getVal(current) >= val){
				left = true;
				current = parent.left;
			}//end if 
			
			//current < value we want to add so go to right subtree
			else{
				left = false;
				current = parent.right;
			}//end else
			
		}//end while
		
	}//end insert2
	
	//this method displays a list of all values visited on the search path to val
	public static void search(int val, BinarySearchTree tree){
		
		boolean done = false;
		BSTnode current = tree.root;
		
		while (done == false){
			if (current == null){
				System.out.print("...wait, the value you entered is not in this tree!");
				done = true;
			}
			else if (BSTnode.getVal(current) == val){
				System.out.print(BSTnode.getVal(current) + "\n");
				done = true;
			}
			else if (BSTnode.getVal(current) > val){
				System.out.print(BSTnode.getVal(current) + ", ");
				current = current.left;
			}
			else{
				System.out.print(BSTnode.getVal(current) + ", ");
				current = current.right;
			}
		}
	}//end search
	
	//this function returns the depth of a BST
	public static int BST_total_depth(BinarySearchTree tree){
			return BST_total_depth2(tree.root, 1);
	}
	
	//helper function for finding the depth of a BST
	public static int BST_total_depth2(BSTnode current, int toAdd){
		if (current == null){
			return 0;
		}
		else {
			return toAdd + BST_total_depth2(current.left, toAdd + 1) + BST_total_depth2((current.right), toAdd + 1);
		}//end if 	
	}
	
	//main function (for testing)
	public static void main(String[] args){
		return;
	}//end main function
	
}//end class

