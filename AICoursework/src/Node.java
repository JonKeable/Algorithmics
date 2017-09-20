
public class Node {

	//The current state of the node
	private State state;
	//The parent node from which this one was derived
	private Node parent;
	// The action taken to move form the parent to this node
	// l = left, r = right, u = up, d = down
	private char action;
	// Path cost g(x)
	private int gCost;
	// The heuristic cost h(x) for the A* algo
	private double hCost;
	// f(x) = g(x) + h(x)
	private double fCost;
	// The depth in the search tree for this node
	private int depth;
	
	// constructor for the root node
	public Node(State s)
	{
		state = s;
		parent = null;
		gCost = 0;
		depth = 0;
	}
	
	public Node(Node par, State s, int c, double h)
	{
		parent = par;
		state = s;
		gCost = c;
		hCost = h;
		fCost = h + c;
	}
	
	public State getState()
	{
		return state;
	}

	public Node getParent()
	{
		return parent;
	}


	public int getCost()
	{
		return gCost;
	}
	
	public double getfCost() {
		return fCost;
	}
}
