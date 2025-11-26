package implementations;

import implementations.MyArrayList;
import utilities.Iterator;
import utilities.StackADT;
import java.util.NoSuchElementException;

public class MyStack<E> implements StackADT<E> {
	private MyArrayList<E> arr;

	@Override
	public void push(E element) {
		arr.add(element);	
	}

	@Override
	public E pop() {
		if (arr.size() > 0)
		{
			return arr.remove(0);
		}
		else 
		{
		return null;
		}
	}

	@Override
	public E peek() {
		if (arr.size() > 0)
		{
			return arr.get(0);
		}
		else
		{
		return null;
		}
	}

	@Override
	public boolean isEmpty() {
		if (arr.size() == 0)
		{
			return true;
		}
		else
		{
		return false;
		}
	}

	@Override
	public int size() {
		return arr.size();
		
	}

	@Override
	public void clear() {

		arr.clear();
	}

	@Override
	public boolean contains(E element) {
		return arr.contains(element);
	}

	@Override
	public Iterator<E> iterator() {
		return arr.iterator();
	}

	@Override
	public int search(E element) throws NoSuchElementException{

		for (int i = 0; i <= arr.size(); i++)
		{
			if (arr.get(i) == element)
			{
				return i;
			}
		}
		
		throw new NoSuchElementException("No such element exists.");
	}


	@Override
	public E[] toArray(E[] array) {
		return arr.toArray(array);
	}

	@Override
	public E[] toArray() {
		return (E[]) arr.toArray();
	}

	@Override
	public boolean stackOverflow() {
		
		return false; //My stack is always able to grow.
	}

}
