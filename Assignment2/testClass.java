/*  Assignment 2 for Data Structures
 * 	Author: Anastasiya Tarnouskaya
 * 	Date: February 16th, 2015
 * 
 * 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//This is the testing class for the Assignment and is where all the data came from
public class testClass {
	public static void main(String[] args){
		
		//for random integer generation 
		Random randInt = new Random(); 
		
		//how many repetitions of each set size we are going to do
		int numTrials = 500;
		
		//n = set size we are going to test our BSTs and RBtrees with
		for (int n=20; n <= 2500; n*=5){
			System.out.println("Set size (n) = " + n );
			System.out.print("After " + numTrials + " trials: (Table shows relative distribution of R values)\n\n");
			
			int rbDepth = 0;
			int bstDepth = 0;
			
			float cat1 = 0;
			float cat2 = 0;
			float cat3 = 0;
			float cat4 = 0;
			float cat5 = 0;

			//numTrials repetitions of finding trees' depths
			for (int k=0; k < numTrials; k++){
				
				//make a new RBTree
				RBTree rbtree = new RBTree();
				rbtree.root = new RBnode('B'); //to avoid a null tree
				
				//make a new BST
				BinarySearchTree bst = new BinarySearchTree();

				//stores nums to avoid duplicates 
				List<Integer> nums= new ArrayList<Integer>(); 

				//insert n identical values into a BST and a RBTree
				for (int i = 0; i < n; i++){

					//value to be added to the respective trees
					int toAdd = randInt.nextInt(n);

					//to avoid duplicates (condition for RBtrees to work)
					while (nums.contains(toAdd)){
						toAdd = randInt.nextInt(n);
					}//end while
					
					nums.add(toAdd);
					
					BinarySearchTree.insert(toAdd,bst);
					RBTree.RB_insert(rbtree, toAdd);
					
				}//n values have been added to each tree 
				
				rbDepth += RBTree.RB_total_depth(rbtree);
				bstDepth += BinarySearchTree.BST_total_depth(bst);
								
				float r = (float) BinarySearchTree.BST_total_depth(bst)/ (float) RBTree.RB_total_depth(rbtree);
				
				if (r<1){
					cat1 += 0.2;
				}
				else if (r<1.25){
					cat2 += 0.2;
				}
				else if (r<1.5){
					cat3 += 0.2;
				}
				else if (r<1.75){
					cat4 += 0.2;
				}
				else{
					cat5 += 0.2;
				}
						
								
			}//have completed numTrials repetitions
			
			System.out.println("                      R = BST.depth / RB.depth\n");
			System.out.println("R < 1    1 <= R < 1.25    1.25 <= R < 1.5    1.5 < R <= 1.75    R > 1.75");
			System.out.print("  " + (int) cat1 + "%     ");
			System.out.print("     " + (int) cat2 + "%         ");
			System.out.print("      " + (int) cat3 + "%          ");
			System.out.print("      " + (int) cat4 + "%        ");
			System.out.println("      " + (int) cat5 + "%      \n");

			
			int avgRBdepth = rbDepth/numTrials;
			int avgBSTdepth = bstDepth/numTrials;
			
			System.out.print("    Average depth of RB tree: " + avgRBdepth);
			System.out.print("    Average depth of BST: " + avgBSTdepth);
			System.out.print("\n--------------------------------------------------------------------------------\n");
			
			
		}
	}//end main		
}//end class
