
public class Solve {
	
	private int n;
	private int[][] startBoard;
	
	public Solve(int n) {
		this.n = n;
		startBoard = genStart(n);
	}
	
	public int[][] genStart(int n) {
		
		// arr[x][y]
		int[][] startArr = new int[n][n]; 
		
		// j is x axis, i is y axis
		// bottom left corner is 0j,0i
		// 0 represents a blank space
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <n; j++) {
				startArr[j][i] = 0;
			}
		}
		
		//here 1,2,3 ... are A,B,C...
		for (int i = 0; i< n; i++) {
			// i.e. (0,0) := 1 , (1,0) := 2 ...
			startArr[i][0] = i + 1;
		}
		
		// -1 is the agent
		startArr[n-1][0] = -1;
		
		return startArr;
	}
	
	public void depthFirst() {
		DepthFirst df = new DepthFirst(startBoard,n);
		
	}

	public void breadthFirst() {
		BreadthFirst bf = new BreadthFirst(startBoard,n);
		
	}

	public void ids() {
		IterativeDeepening ids = new IterativeDeepening(startBoard,n);
	}
	
	public void aStar() {
		AStar as = new AStar(startBoard,n,1);
		as = new AStar(startBoard,n,2);
	}
}
