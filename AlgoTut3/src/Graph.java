import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Graph {
	
	private Position[] positions;
	private int noVertices = 0;
	private int noEdges = 0;
	private double[][] conMatrix;
	//public GraphDisplay gd;
	
	public Graph(int n) {
		if (n < 1) throw new RuntimeException("Must be at least 1 Vertex");
		else {
			noVertices = n;
			noEdges = 0;
			conMatrix = new double[n][n];
			for(int i = 0; i < noVertices; i++) {
				for (int j = 0; j < noVertices; j++) {
					conMatrix[i][j] = 0;
				}
			}
			positions = new Position[n];
		}
	}
	
	public void display(String name) {
		GraphDisplay gd = new GraphDisplay();
		gd.showInWindow(400,400, name);
		for(int i = 0; i < noVertices; i++) {
			Position p = positions[i];
			gd.addNode(i, p.getX(), p.getY());
		}
		for(int i = 0; i < noVertices; i++) {
			for(int j = i; j < noVertices; j++) {
				if(edgeExist(i,j)) {
					gd.addEdge(i, j);
				}
			}
		}
	}
	
	public void kruskal() {
		
		//Creates a copy of the graph without the edges
				Graph tree = new Graph(noVertices);

				for(int i = 0; i < noVertices; i++) {
					Position p = this.getPos(i);
					tree.setPos(p.getX(), p.getY(), i);
				}
				
		    	
    	 /* Create a disjoint sets object*/
	    DisjointSets dj = new DisjointSets(noVertices);
	    
	    PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
	    for(int i = 0; i < noVertices; i++) {
	    	for (int j = i; j < noVertices; j++) {
	    		if( edgeExist(i, j)) {
	    			pq.add(new Edge(i,j,this.getWeight(i, j)));
	    		}
	    	}
	    }
	    
	    int edgesAcc = 0;
	    while(edgesAcc < noVertices -1) {
	    	Edge edge = pq.poll();

		    /* Find to shortest edge (a,b) */

		    int finda = dj.find(edge.getVertA());
		    int findb = dj.find(edge.getVertB());

		    if (finda!=findb) {
		        // vertices a and b belong to different trees
		        dj.union(finda, findb);  // NOT dj.union(a,b)
		        /* add (a,b) to spanning tree */
		        tree.addEdge(edge.getVertA(), edge.getVertB(), 1);
		        edgesAcc ++;
		    }
	    }

	    tree.display("Kruskal");

	}
	
	public void prim() throws InterruptedException {
		//Creates a copy of the graph without the edges
		Graph tree = new Graph(noVertices);

		for(int i = 0; i < noVertices; i++) {
			Position p = this.getPos(i);
			tree.setPos(p.getX(), p.getY(), i);
		}

		
		double[] minDistances = new double[noVertices];
		for (int i = 0; i < noVertices; i++) {
			minDistances[i] = Double.MAX_VALUE;
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		int node = 0;
		for (int i = 0; i < noVertices-1; i++) {
			
			minDistances[node] = 0;
			List<Integer> neighbours = this.adjacent(node);
			for(int vertex : neighbours) {
				double distance = conMatrix[node][vertex];
				if (distance < minDistances[vertex]){
					minDistances[vertex] = distance;
					pq.add(new Edge(node, vertex, distance));
				}
			}
			Edge nextEdge;
			do {
				nextEdge = pq.poll();
				if (nextEdge == null) break;
			}
			while(minDistances[nextEdge.getVertB()] <= 0);
			if (nextEdge != null) {
				tree.addEdge(nextEdge.getVertA(), nextEdge.getVertB(), 1);
				node = nextEdge.getVertB();
				//Thread.sleep(500);
				//tree.gd.addEdge(nextEdge.getVertA(), node);
			}

		}
		
		
		
		tree.display("Min Spanning Tree");

	}
	
	public boolean setPos(double x, double y, int vertex) {
		if (vertex < noVertices){
			positions[vertex] = new Position(x,y);
			return true;
		}
		else return false;
	}
	
	public Position getPos(int vertex) {
		return positions[vertex];
	}
	
	public boolean edgeExist(int vertA, int vertB) {
		return(conMatrix[vertA][vertB] > 0);
	}
	
	//returns 0 when no edge
	public double getWeight(int vertA, int vertB) {
			return conMatrix[vertA][vertB];
	}
	
	public List<Integer> adjacent(int vertex) {
		List<Integer> adjList = new ArrayList<Integer>();
		for (int i = 0; i < noVertices; i++) {
			if (edgeExist(vertex, i)) {
				adjList.add(i);
			}
		}
		return adjList;
	}
	
	public boolean addEdge(int vertA, int vertB, double weight) {
		
		int bound = noVertices;
		if (vertA < bound && vertB < bound && weight > 0) {
			conMatrix[vertA][vertB] = weight;
			conMatrix[vertB][vertA] = weight;
			return true;
		}
		else return false;
	}
	
	public class Position {
		private double x, y;
		
		public Position(double x, double y){
			this.x = x;
			this.y = y;
		}
		
		public double getX(){
			return this.x;
		}
		
		public double getY(){
			return this.y;
		}
	}

	public class Edge implements Comparable<Edge>{
		private int vertA, vertB;
		private double d;
		
		public Edge(int vertA, int vertB, double distance) {
			this.vertA = vertA;
			this.vertB = vertB;
			this.d = distance;
		}
		
		public int getVertA() {
			return this.vertA;
		}
		
		public int getVertB() {
			return this.vertB;
		}
		
		public double getDist() {
			return this.d;
		}

		@Override
		public int compareTo(Edge edge) {
			double diff = this.getDist() - edge.getDist();
			if (diff > 0) return 1;
			else if (diff < 0) return -1;
			else return 0;
		}
	}

}
