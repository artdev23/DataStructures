package struct;


public class PriorityQueue<E extends Comparable<? super E>>
		implements QueueADT<E>
{

  private Heap<E> heap;


  public PriorityQueue()
  {
	heap = new Heap<>();
  }


  @Override
  public void enqueue(E val)
  {
	heap.insert(val);
  }


  @Override
  public E dequeue()
  {
	return heap.extractMax();
  }


  @Override
  public boolean isEmpty()
  {
	return heap.isEmpty();
  }

}