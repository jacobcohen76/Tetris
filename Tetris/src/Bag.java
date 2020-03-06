import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Bag<T>
{
	private LinkedList<T> elements;
	private Random rand;
	private boolean shuffled;
	
	public Bag()
	{
		elements = new LinkedList<T>();
		rand = new Random();
		shuffled = false;
	}
	
	public Bag(Collection<T> items)
	{
		this();
		addAll(items);
	}
	
	public boolean addAll(Collection<T> items)
	{
		shuffled = false;
		return elements.addAll(items);
	}
	
	public boolean add(T item)
	{
		shuffled = false;
		return elements.add(item);
	}
	
	public void shuffle()
	{
		Collections.shuffle(elements, rand);
		shuffled = true;
	}
	
	public boolean isEmpty()
	{
		return elements.isEmpty();
	}
	
	public int size()
	{
		return elements.size();
	}
	
	public T pull()
	{
		if(shuffled == false)
			shuffle();
		return elements.poll();
	}
}
