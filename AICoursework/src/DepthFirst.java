import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

public class DepthFirst {
	
	private static final boolean printSol = false;

	//the start board and size of board that we want is passed to the function
	public DepthFirst(int[][] board, int n)
	{
		System.out.println("-----------");
		System.out.println("DFS");
		System.out.println("-----------");
		
		//Create a root node and add it to the queue
		Node root = new Node(new State(n, board));
		//System.out.println("START BOARD");
		//root.getState().printBoard();
		//we use a stack as we want LIFO expansion
		Stack<Node> st = new Stack<Node>();
		st.add(root);
		treeSearch(st);
	}
	
	//Checks to see if we have already expanded a state
	private boolean isRepeat(Node n)
	{
		boolean repeat = false;
		Node thisNode = n;

		// goes up the tree checking if the state of this node has already been expanded
		//stops at the root node
		while (n.getParent() != null && !repeat)
		{
			if (n.getParent().getState().equals(thisNode.getState()))
			{
				repeat = true;
			}
			n = n.getParent();
		}

		return repeat;
	}
	
	public void treeSearch(Stack<Node> st)
	{
		//We need this to so we know how many nodes we have checked
		int itCount = 1;

		while (!st.isEmpty())
		{
			Node tmp = st.pop();

			//Check if we have solved the board yet
			if (!tmp.getState().isGoalState())
			{
				//first create and store the children for the node
				ArrayList<State> tmpChildren = tmp.getState().genChildren(); 

				//For DFS we want to avoid getting stuck down paths which arent likely to be
				//Good, like Up, Up, Up ..., so we randomise the order of the child nodes
				Collections.shuffle(tmpChildren);
				
				// Loop through the children, this time we use a stack for DFS
				for (State s : tmpChildren)
				{
					// We add 1 to the cost of the new node becuase this problem always has cost of 1 for a move
					Node newNode = new Node(tmp,s, tmp.getCost()+ 1, 0);
					//If we haven't already expanded the state, add the node to the queue 
					if (!isRepeat(newNode))
					{
						//newNode.getCurState().printBoard();
						st.add(newNode);
					}
				}
				itCount++;
			}
			// We have a solution!
			else
			{
				//We now trace the path we took through the tree to get our solution
				Stack<Node> path = new Stack<Node>();
				
				// we use do while for the case when the start state is also the goal state
				do
				{
					path.push(tmp);
					tmp = tmp.getParent();
				}
				//While the node is not the root
				while (tmp.getParent() != null);
				path.push(tmp);

				// The depth of the path
				int depth = path.size();

				System.out.println("SOLUTION");
				
				// prints all the board states in order of start to goal
				// avoid enhanched for loop to avoid popping problems, although should still work
				for (int i = 0; i < depth; i++)
				{
					tmp = path.pop();
					if (printSol) tmp.getState().printBoard();
				}
				
				System.out.println("Solution Cost: " + tmp.getCost());
				//This is what we will use for our plot
				System.out.println("The number of nodes expanded: "+ itCount);
				st.clear();
			}
		}
	}
}
