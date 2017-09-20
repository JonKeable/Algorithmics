import java.util.Random;

public class AvgDepthTester {
	public static void main(String[] args) {
		//No of data points to collect
		int noTests = 100;
		Random r = new Random();
		for(int i = 0; i < noTests; i++) {
			BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
			int noEls = i*100;
			for(int j = 0; j < noEls; j++){
				tree.add(r.nextInt());
			}
			double avgDepth = tree.avgDepth();
			System.out.println(noEls + "\t" + avgDepth);
		}
	}

}
