package struct;


public class ListQueue<E>
		implements QueueADT<E>
{

  private TwoWayLinkedList<E> list;


  public ListQueue()
  {
	list = new TwoWayLinkedList<>();
  }


  @Override
  public void enqueue(E val)
  {
	list.insertLast(val);
  }


  @Override
  public E dequeue()
  {
	E e = list.first();
	list.deleteFirst();

	return e;
  }


  @Override
  public boolean isEmpty()
  {
	return list.isEmpty();
  }

}