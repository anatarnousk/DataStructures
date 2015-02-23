/*  Assignment for Data Structures
 * 	Author: Anastasiya Tarnouskaya 
 * 	Date: February 16th, 2015
 * 
 * 
 */


//this class is able to insert, search and find the depth of BSTs
public class RBTree {
	RBnode root; 

	//this function inserts value (int) into tree (RBtree)
	static void RB_insert(RBTree tree, int value){
		tree.root = rec_RB_insert(tree.root, value);
		tree.root.colour = 'B';

	}//end RB_insert

	//function inserts value (int) into tree (RBtree)
	static RBnode rec_RB_insert(RBnode current, int value){

		if (current.isLeaf == true){
			return new RBnode(value, 'R'); //returns a red node w/ 2 black leaves
		}//end if

		//current < value so insertion takes place on the right side
		if (current.val < value){
			current.right = rec_RB_insert(current.right, value);

			//check for colour balance on tree 
			if (current.colour == 'R'){
				return current;
			}//end if

			//checks if current's child is red 
			else if (current.right.colour == 'R'){

				//checks if current's grandchild is red. If yes, has to be fixed. 
				if (has_Red_child(current.right)){
					return RB_fix_R(current,value);
				}//end elif

				//no rebalance needed b/c we don't have 2 reds in a row
				else{
					return current;
				}//end else

			}//end elif

			//this means current's right child is black; no red-red conflict
			else{
				return current;
			}//end else

		}//end elif

		//current > value so insertion takes place on the left side
		else{
			current.left = rec_RB_insert(current.left, value);
			if (current.colour == 'R'){
				return current;
			}//end if

			//checks to see if current's left child is red
			else if (current.left.colour == 'R'){

				//if current's grandchild is also red, tree has to be fixed
				if (has_Red_child(current.left) == true){
					return RB_fix_L(current,value);
				}//end if

				//grandchild is black; no r-r conflict; no rebalance needed
				else{
					return current;
				}

			}//end elif

			//current's left child is black; no red-red conflict; no rebalance needed
			else{
				return current;
			}//end else

		}//end else

	}//end rec_RB_insert

	//this function returns true of current has a red child
	static boolean has_Red_child(RBnode current){
		return (current.right.colour == 'R' || current.left.colour =='R');
	}//end has_red_child
	
	static RBnode RB_fix_L(RBnode current, int value){
	    RBnode sibling = current.right;
	    RBnode child = current.left;
	    
	    if (sibling.colour == 'R'){
	       child.colour = 'B';
	       sibling.colour = 'B';
	       current.colour = 'R';
	       return current;
	    }//end if

	    else{ //sibling colour is black; need to rotate
	       
	    	//use x to figure out which rotation needed
	       if (value < child.val){ //single rotation case (LL situation)
	    	   
	          RBnode grandchild = child.left; //identify important grandchild
	          
	          //fix pointers
	          current.left = child.right;
	          child.right = current;

	          //fix colours
	          child.colour = 'B';
	          current.colour = 'R';
	          
	          //return root of subtree
	          return child;
	       }
	       
	       else{ //double rotation case (LR situation)

	          RBnode grandchild = child.right; //identify important grandchild

	          //fix pointers
	          child.right = grandchild.left;
	          current.left = grandchild.right;
	          grandchild.left = child;
	          grandchild.right = current;

	          //fix colours
	          grandchild.colour = 'B';
	          current.colour = 'R';
	          
	          //return new root of subtree
	          return grandchild;
	       }//end else 
	       
	    }//end else
	        		  
	}//end RB_fix_L
	
	static RBnode RB_fix_R(RBnode current, int value){
	    RBnode sibling = current.left;
	    RBnode child = current.right;
	    
	    if (sibling.colour == 'R'){
	       child.colour = 'B';
	       sibling.colour = 'B';
	       current.colour = 'R';
	       return current;
	    }//end if

	    else{ //sibling colour is black; need to rotate
	       
	    	//use x to figure out which rotation needed
	       if (value > child.val){ //single rotation case (LL situation)
	    	   
	          RBnode grandchild = child.right; 
	    	   
	          //fix pointers
	          current.right = child.left;
	          child.left = current;

	          //fix colours
	          child.colour = 'B';
	          current.colour = 'R';
	          
	          //return root of subtree
	          return child;
	       }
	       
	       else{ //double rotation case (LR situation)

	          RBnode grandchild = child.left; //identify important grandchild

	          //fix pointers
	          child.left = grandchild.right;
	          current.right = grandchild.left;
	          grandchild.right = child;
	          grandchild.left = current;

	          //fix colours
	          grandchild.colour = 'B';
	          current.colour = 'R';
	          
	          //return new root of subtree
	          return grandchild;
	       }//end else 
	       
	    }//end else
	        		  
	}//end RB_fix_R
	
	//this method displays a list of all values visited on the search path to val
		public static void search(int val, RBTree tree){
			
			boolean done = false;
			RBnode current = tree.root;
			
			while (done == false){
				if (current == null){
					System.out.print("...wait, the value you entered is not in this tree!\n");
					done = true;
				}
				else if (current.val == val){
					System.out.print(current.val + "(" + current.colour + ")\n");
					done = true;
				}
				else if (current.val > val){
					System.out.print(current.val + "(" + current.colour + "), ");
					current = current.left;
				}
				else{
					System.out.print(current.val + "(" + current.colour + "), ");
					current = current.right;
				}
			}
		}//end search
	
	//this function returns the depth of a RB tree
	public static int RB_total_depth(RBTree mytree){
		if (mytree == null){
			return 0;
		}
		else{
			return RB_total_depth2(mytree.root, 1);
		}
	}//end total_depth
	
	//helper function for finding the depth of a RB tree
	public static int RB_total_depth2(RBnode current, int toAdd){
		
		if (current == null || current.val == null){
			return 0;
		}
		
		else {
			return toAdd + RB_total_depth2(current.left, (toAdd + 1)) + RB_total_depth2((current.right), (toAdd + 1));
		}//end if 
		
	}//end total_depth2
	
	//main function (for testing)
	public static void main(String[] args){
		return;
	}//end main function
	
}
