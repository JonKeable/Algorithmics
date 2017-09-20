import java.util.Random;

public class GraphMain {
	public static void main(String[] args) throws InterruptedException {
		Graph g = randomGraph(15, (float) 1);
		g.display("Random Graph");
		g.prim();
		g.kruskal();
	}
	
	public static Graph randomGraph(int n, float p) {
		if(p < 0 || p > 1) return null;
		else {
			Random r = new Random();
			Graph g = new Graph(n);
			for (int i = 0; i < n; i++) {
				g.setPos(r.nextDouble(), r.nextDouble(), i);
			}
			for(int i = 0; i < n; i++) {
				for (int j = i; j < n; j++) {
					if(r.nextDouble() <= p) {
						if (j != i) {
							double x = Math.abs(g.getPos(i).getX() - g.getPos(j).getX());
							double y = Math.abs(g.getPos(i).getY() - g.getPos(j).getY());
							double weight = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
							g.addEdge(i, j, weight);
						}
					}
				}
			}
			return g;
		}
	}
}
