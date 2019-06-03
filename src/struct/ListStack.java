package struct;


public class ListStack<E>
		implements StackADT<E>
{

  private SinglyLinkedList<E> list;


  public ListStack()
  {
	list = new SinglyLinkedList<>();
  }


  @Override
  public void push(E val)
  {
	list.insertFirst(val);
  }


  @Override
  public E pop()
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


  @Override
  public int size()
  {
	return list.size();
  }

}