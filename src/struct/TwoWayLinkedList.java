package struct;


public class TwoWayLinkedList<E>
		extends SinglyLinkedList<E>
		implements ListADT<E>
{

  private Node<E> tail;


  public TwoWayLinkedList()
  {
	super();
	tail = null;
  }


  @Override
  public boolean contains(E val)
  {
	return super.contains(val);
  }


  @Override
  public void insertFirst(E val)
  {
	super.insertFirst(val);
  }


  @Override
  public void insertLast(E val)
  {
	super.insertLast(val);
  }


  @Override
  public void insertAfter(E after, E val)
  {
	super.insertAfter(after, val);
  }


  @Override
  public void insertBefore(E before, E val)
  {
	super.insertBefore(before, val);
  }


  @Override
  public void delete(E val)
  {
	super.delete(val);
  }


  @Override
  public void deleteFirst()
  {
	super.deleteFirst();
  }


  @Override
  public void deleteLast()
  {
	super.deleteLast();
  }


  @Override
  public void deleteAfter(E after)
  {
	super.deleteAfter(after);
  }


  @Override
  public void deleteBefore(E before)
  {
	super.deleteBefore(before);
  }


  @Override
  public void clear()
  {
	super.clear();
	tail = null;
  }


  @Override
  protected void addAtBegin(Node<E> x)
  {
	if (isEmpty())
	  tail = x;

	super.addAtBegin(x);
  }


  @Override
  protected void addAtEnd(Node<E> x)
  {
	if (isEmpty())
	{
	  addAtBegin(x);
	  return;
	}

	tail.next = x;
	tail = x;
  }


  @Override
  protected void insertAfter(Node<E> after, Node<E> x)
  {
	super.insertAfter(after, x);

	if (after == tail)
	  tail = x;
  }


  @Override
  protected void deleteElem(Node<E> node, Node<E> prev)
  {
	super.deleteElem(node, prev);

	if (node == tail)
	  tail = prev;
  }


  @Override
  protected void deleteAtBegin()
  {
	super.deleteAtBegin();

	if (isEmpty())
	  tail = null;
  }


  @Override
  protected void deleteAtEnd()
  {
	super.deleteAtEnd();

	if (isEmpty())
	  tail = null;
  }


  @Override
  protected void deleteAfter(Node<E> after)
  {
	if (after.next == tail)
	  tail = after;

	super.deleteAfter(after);
  }

}