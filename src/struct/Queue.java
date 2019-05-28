package struct;


public class Queue<E extends Comparable<? super E>>
		implements QueueADT<E>
{

  private DynArray<E> Q;
  private int head;
  private int tail;

  private static final int DEFAULT_ARRAY_CAPACITY = 100;


  public Queue()
  {
	Q = new DynArray<>(DEFAULT_ARRAY_CAPACITY);
	head = 1;
	tail = 1;
  }


  @Override
  public void enqueue(E val)
  {
	if (head == tail + 1 || (head == 1 && tail == Q.capacity()))
	  throw new OverflowException();

	Q.set(tail, val);

	if (tail == Q.capacity())
	  tail = 1;
	else
	  tail++;
  }


  @Override
  public E dequeue()
  {
	if (isEmpty())
	  throw new UnderflowException();

	E elem = Q.get(head);

	if (head == Q.capacity())
	  head = 1;
	else
	  head = head + 1;

	return elem;
  }


  @Override
  public boolean isEmpty()
  {
	return head == tail;
  }


}