import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import junit.framework.TestCase;

public class TestBinaryTree extends TestCase {

	@Test
	public void testAdd() {
		Random r = new Random();
		BinarySearchTree testTree = new BinarySearchTree<Double>();
		assertTrue("Initial tree should be empty", testTree.size() == 0);
		
		Double e = r.nextDouble();
		testTree.add(e);
		assertTrue("Tree should now contain 1 node", testTree.size() == 1);
		
		testTree = new BinarySearchTree<Integer>();
		//A value between 100 and 199
		int noNodes = r.nextInt(100) + 100;
		
		for(int i = 0; i < noNodes; i++) {
			testTree.add(i);
		}
		
		assertEquals("Size not equal to number of nodes added", noNodes, testTree.size());
	}
	
	@Test
	public void testRemove() {
		Random r = new Random();
		BinarySearchTree testTree = new BinarySearchTree<Double>();
		assertTrue("Initial tree should be empty", testTree.size() == 0);
		
		Double e = r.nextDouble();
		testTree.add(e);
		assertTrue("Tree should now contain 1 node", testTree.size() == 1);
		
		assertTrue("Failed to remove element",testTree.remove(e));
		assertTrue("Tree should now contain 0 nodes", testTree.size() == 0);
		
		
		testTree = new BinarySearchTree<Integer>();
		int noNodes = 1000;
		int noNodesRem = 500;
		
		for(int i = 0; i < noNodes; i++) {
			testTree.add(i);
		}
		for(int i = 0; i < noNodesRem; i++) {
			testTree.remove(i);
		}
		
		assertTrue("Wrong number of elements left in tree", testTree.size() == noNodes - noNodesRem);
	}
	
	@Test
	public void testContains() {
		Random r = new Random();
		BinarySearchTree<Integer> testTree = new BinarySearchTree<Integer>();
		int noNodes = 1000;
		for (int i = 0; i < noNodes; i++) {
			testTree.add(i);
		}
		for (int i = 0; i < noNodes; i++) {
			assertTrue("Tree contains all added elements",testTree.contains(i));
		}
		assertTrue("Tree contains method returns true for elements not present", !(testTree.contains(noNodes) || (testTree.contains(-1))));
	}
	
	@Test
	public void testIterator() {
		BinarySearchTree<Integer> testTree = new BinarySearchTree<Integer>();
		//Because we are adding unique nodes we can use this set to test if the iterator returns all the correct values
		Set<Integer> nodesSet = new TreeSet<Integer>();
		int noNodes = 1000;
		for (int i = 0; i < noNodes; i++) {
			testTree.add(i);
			nodesSet.add(i);
		}
		Iterator<Integer> it = testTree.iterator();
		while(it.hasNext()) {
			Integer i = it.next();
			assertTrue("Element returned by iterator should not be present", nodesSet.contains(i));
			nodesSet.remove(i);
		}
		
		assertTrue("Not enough elements returned", nodesSet.size() == 0);
		
		// Test if exception thrown when next called when hasNext is false
		boolean eThrown = false;
		try {
			it.next();
		} catch(NoSuchElementException e) {
			eThrown = true;
		}
		
		assertTrue("Excetion not thrown when next() is called when hasNext() is false", eThrown);
	}

}
