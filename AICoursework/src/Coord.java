
public class Coord {

	//x,y coords for a block in the board
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
	
	// creates a new coordiate with position (x,y)
	public Coord(int x ,int y) {
		this.x = x;
		this.y = y;
	}
}
