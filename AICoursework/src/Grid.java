
//Not Used In Project Ultimately
public class Grid {

	private int[][] mat;
	private Coord posAg;
	
	//initiialises an nxn grid
	public Grid(int n) {
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
				posAg = new Coord(n-1,0);
				
				mat = startArr;
	}
	
	private class Coord {
		private int x,y;
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public Coord(int x ,int y) {
			this.x = x;
			this.y = y;
		}
		
	}
}
