package struct;


import static java.lang.Math.floor;


public class Heap<E extends Comparable<? super E>>
{

  private DynArray<E> A;


  public Heap()
  {
	A = new DynArray<>();
  }


  public E max()
  {
	return A.get(1);
  }


  public void insert(E key)
  {
	A.add(key);
	int i = A.size();

	while (i > 1 && A.get(parent(i)).compareTo(A.get(i)) < 0)
	{
	  A.swap(i, parent(i));
	  i = parent(i);
	}
  }


  public E extractMax()
  {
	if (isEmpty())
	  throw new UnderflowException();

	E max = A.get(1);

	E last = A.get(A.size());
	A.set(1, last);
	A.delete(A.size());

	maxHeapify(1);

	return max;
  }


  public boolean isEmpty()
  {
	return A.size() < 1;
  }


  private void maxHeapify(int i)
  {
	int l = left(i);
	int r = right(i);

	int largest;
	if (l <= A.size() && A.get(l).compareTo(A.get(i)) > 0)
	  largest = l;
	else
	  largest = i;

	if (r <= A.size() && A.get(r).compareTo(A.get(largest)) > 0)
	  largest = r;

	if (largest != i)
	{
	  A.swap(i, largest);
	  maxHeapify(largest);
	}
  }


  private int parent(int i)
  {
	float h = i / 2f;
	return (int) floor(h);
  }


  private int left(int i)
  {
	return 2 * i;
  }


  private int right(int i)
  {
	return 2 * i + 1;
  }


  public static class UnderflowException
		  extends RuntimeException
  {

	UnderflowException()
	{
	  super("Heap опустошен");
	}

  }


}