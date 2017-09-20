import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularArrayRing<E> extends AbstractCollection<E> implements Ring<E>{

	private final static int startingSize = 32;
	private E[] ring;
	private int head,elementCount;
	private boolean isFull;
	
	public CircularArrayRing(){
		this(startingSize);
	}
	
	public CircularArrayRing(int size){
		//This is necessary because you cannot instantiate arrays of generic type
		ring = (E[]) new Object[size];
		//The head will be the latest addition to the ring
		head = elementCount = 0;
		isFull = false;
	}
	
	@Override
	public boolean add(E e){
		head = (head+1) % ring.length;
		ring[head] = e;
		// Only increments the element count when the ring is not full
		// becuase you cannot remove elments from the ring, we do not need
		// to worry about counting elements after the ring is filled
		if(!isFull){
			elementCount++;
			if(elementCount == ring.length) isFull = true; 
		}

		return true;
	}
	
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		//If the index is less than 0 or greater than or equal to (because elements indexed from 0) 
		// the number of elements in the array we throw an exception.
		if(index < 0 || index >= elementCount) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		else {
			int ringIndex = head-index;
			//If the (head - index) is positive we can simply return the element in that position
			if(ringIndex >= 0) return ring[ringIndex];
			//For negative values however we must add the length of the ring to the value to get the actual index we want
			//e.g. for a ring of 32, -1 would be  [31], and -5 would be [27]
			else return ring[ring.length+ringIndex];
		}
	}

	@Override
	public int size() {
		return elementCount;
	}

	@Override
	public Iterator<E> iterator() {
		Iterator<E> it = new Iterator<E>(){

			//stores the current index for the get() method, starting with 0
			int index = 0;
			
			@Override
			public boolean hasNext() {
				//If the index greater than or equal to the number of elements
				//And the next element is not null(shouldn't ever be false if the first part is true)
				return (index < size() && get(index) != null);  
			}

			@Override
			public E next() {
				//If the index is not valid throw an exception
				if((index) >= elementCount) throw new NoSuchElementException(Integer.toString(index));
				index++;
				return get(index-1);

			}
			
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
			
		};
		return it;
	
	}


}
