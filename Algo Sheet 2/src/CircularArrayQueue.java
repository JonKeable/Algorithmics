import java.util.NoSuchElementException;

public class CircularArrayQueue implements MyQueue{

	private final static int startingSize = 8;
	private Integer[] queue;
	//The head is where the least recent element added is, 
	//and the tail is the space after the most recent element
	private int head, tail, elementCount;
	
	public CircularArrayQueue() {
		this(startingSize);
	}
	
	public CircularArrayQueue(int initSize) {
		//Initialises the front and rear of the queue, as well as its size to 0
		head = tail = elementCount = 0;
		//Creates the new queue with a starting size
		queue = new Integer[initSize];
	}
	
	@Override
	public void enqueue(int e) {
		
		// If the queue is full, create a copy with double the capacity
		if(elementCount == queue.length) {
			doubleSize();
		}
		
		// Places the element at the rear of the queue
		queue[tail] = e;
		
		// The head of the queue moves one place forward
		// The reminder must be taken in order to loop back round to 0 in the circular array when necessary
		tail = (tail + 1) % queue.length;
		
		elementCount++;
	}



	@Override
	public int dequeue() throws NoSuchElementException{
		
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		
		// Gets the value of the head element, and then removes it from the queue
		int e = queue[head];
		queue[head] = null;
		
		//The head of the queue moves one place forward
		// The reminder must be taken in order to loop back round to 0 in the circular array when necessary
		head = (head + 1) % queue.length;
		
		elementCount--;
		
		// Returns the head element
		return e;
	}

	@Override
	public int noItems() {
		return elementCount;
	}

	@Override
	public boolean isEmpty() {
		return (elementCount == 0);
	}
	
	private void doubleSize() {
		// Creates a new array of double the size
		Integer[] newQueue = new Integer[queue.length * 2];
		int i = 0;
		// Moves the items from the original array to the new one
		while(!isEmpty()) {
			newQueue[i] = dequeue();
			i++;
		}
		head = 0;
		tail = i;
		//This has to be reassigned because we used dequeue to move the elements, which decrements the counter
		elementCount = i;
		queue = newQueue;
	}
	
	public int getCapacityLeft() {
		return queue.length - elementCount;
	}

}
