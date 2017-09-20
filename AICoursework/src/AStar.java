import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AStar {
	
	private static final boolean printSol = false;

	///the start board and size of board that we want is passed to the function
	//also an int to represent the heuristic we wish to use, 1 is the number out of place
	public AStar(int[][] board, int n, int heur)
	{
		
		System.out.println("-----------");
		System.out.println("A*");
		//heur 1 = noWrongBlocks, 2 = totalBlockDistance
		System.out.println("Heurstic:    " + heur);
		System.out.println("-----------");
		
		//Create a root node and add it to the queue
		Node root = new Node(new State(n, board));
		//System.out.println("START BOARD");
		//root.getState().printBoard();
		treeSearch(root,heur);
		

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
	
	public void treeSearch(Node r, int h) {
		//We need this to so we know how many nodes we have checked
				int itCount = 1;
				//we want to store the nodes separately so we can order them in terms of f(x) cost
				ArrayList<Node> nodeChildren = new ArrayList<Node>();
				Node bestNode = r;
				boolean done = false;
				
				while (!done)
				{
					Node tmp = bestNode;

					//Check if we have solved the board yet
					if (!tmp.getState().isGoalState()) 
					{
						//first create and store the children for the node
						ArrayList<State> tmpChildren = tmp.getState().genChildren();


						for (State s : tmpChildren)
						{
							double heurCost = 0;
							if( h == 1) {
								heurCost = s.calcWrongBlocks();
								//System.out.println("wrong: " + heurCost);
							}
							else if (h ==2) {
								heurCost = s.calcDistance();
								//System.out.println("dis: " + heurCost);
							}
							else heurCost = 0;
							// We add 1 to the cost of the new node becuase this problem always has cost of 1 for a move
							Node newNode = new Node(tmp,s, tmp.getCost()+ 1, heurCost);
							//If we haven't already expanded the state, add the node to the queue 
							
							if (!isRepeat(newNode))
							{
								//newNode.getState().printBoard();
								nodeChildren.add(newNode);
							}

						}
						if(!nodeChildren.isEmpty()) {
							bestNode = nodeChildren.get(0);
							
							for (Node nd : nodeChildren)
							{
								if (bestNode.getfCost() > nd.getfCost())
								{
									bestNode = nd;
								}
							}
							
							nodeChildren.remove(bestNode);
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
						done = true;
					}
				}
	}
}
