package struct;


import java.util.Iterator;
import java.util.function.Consumer;


public class SinglyLinkedList<E>
		implements ListADT<E>
{

  private Node<E> head;
  private int size;


  public SinglyLinkedList()
  {
	head = null;
	size = 0;
  }


  @Override
  public E first()
  {
	return head.val;
  }


  @Override
  public int size()
  {
	return size;
  }


  @Override
  public void iterate(Consumer<E> action)
  {
	for (E e : this)
	{
	  action.accept(e);
	}
  }


  @Override
  public boolean contains(E val)
  {
	return find(val) != null;
  }


  @Override
  public void insertFirst(E val)
  {
	Node<E> x = new Node<>(val);
	addAtBegin(x);
	size++;
  }


  @Override
  public void insertLast(E val)
  {
	Node<E> x = new Node<>(val);
	addAtEnd(x);
	size++;
  }


  @Override
  public void insertAfter(E after, E val)
  {
	Node<E> node = find(after);

	if (node == null)
	  throw new IllegalArgumentException("Элемента нет в списке: " + after);

	insertAfter(node, new Node<>(val));
	size++;
  }


  @Override
  public void insertBefore(E before, E val)
  {
	Pair<Node<E>, Node<E>> elem = findNodeAndBefore(before);

	if (elem.first == null)
	  throw new IllegalArgumentException("Элемента нет в списке: " + before);

	Node<E> x = new Node<>(val);

	if (elem.second == null)
	  addAtBegin(x);
	else
	  insertAfter(elem.second, x);
	size++;
  }


  @Override
  public void delete(E val)
  {
	if (isEmpty())
	  throw new UnderflowException();

	Pair<Node<E>, Node<E>> elem = findNodeAndBefore(val);

	if (elem == null)
	  throw new IllegalArgumentException("Удаляемого элемента нет в списке");

	deleteElem(elem.first, elem.second);
	size--;
  }


  @Override
  public void deleteFirst()
  {
	deleteAtBegin();
	size--;
  }


  @Override
  public void deleteLast()
  {
	deleteAtEnd();
	size--;
  }


  @Override
  public void deleteAfter(E after)
  {
	if (isEmpty())
	  throw new UnderflowException();

	Node<E> node = find(after);

	if (node == null)
	  throw new IllegalArgumentException("Элемента нет в списке: " + after);

	deleteAfter(node);
	size--;
  }


  @Override
  public void deleteBefore(E before)
  {
	if (isEmpty())
	  throw new UnderflowException();

	if (head.val.equals(before))
	  throw new IllegalArgumentException("Элемент является первым: " + before);

	RuntimeException ex =
			new IllegalArgumentException("Элемента нет в списке: " + before);

	if (head.next == null)
	  throw ex;

	Node<E> x = head;
	while (x.next.next != null)
	{
	  if (x.next.next.val.equals(before))
	  {
		deleteElem(x.next, x);
		size--;
		return;
	  }
	  x = x.next;
	}

	throw ex;
  }


  @Override
  public void clear()
  {
	Node<E> x = head;

	while (x != null)
	{
	  Node<E> next = x.next;
	  x.clear();
	  x = next;
	}

	head = null;
	size = 0;
  }


  @Override
  public boolean isEmpty()
  {
	return head == null;
  }


  protected Node<E> find(E val)
  {
	Node<E> x = head;

	while (x != null)
	{
	  if (x.val.equals(val))
		break;
	  x = x.next;
	}

	return x;
  }


  private Node<E> findBefore(E val)
  {
	if (isEmpty())
	  return null;

	Node<E> x = head;

	while (x.next != null)
	{
	  if (x.next.val.equals(val))
		return x;
	  x = x.next;
	}

	return null;
  }


  protected Pair<Node<E>, Node<E>> findNodeAndBefore(E val)
  {
	if (isEmpty())
	  return null;

	Node<E> x = head;

	if (x.val.equals(val))
	  return new Pair<>(x, null);

	while (x.next != null)
	{
	  if (x.next.val.equals(val))
		return new Pair<>(x.next, x);
	  x = x.next;
	}

	return null;
  }


  protected void addAtBegin(Node<E> x)
  {
	x.next = head;
	head = x;
  }


  protected void addAtEnd(Node<E> x)
  {
	if (isEmpty())
	{
	  addAtBegin(x);
	  return;
	}

	Node<E> cur = head;
	while (cur.next != null)
	{
	  cur = cur.next;
	}

	cur.next = x;
	x.next = null;
  }


  protected void insertAfter(Node<E> after, Node<E> x)
  {
	x.next = after.next;
	after.next = x;
  }


  protected void deleteElem(Node<E> node, Node<E> prev)
  {
	if (node == head)
	{
	  deleteAtBegin();
	  return;
	}

	prev.next = node.next;
  }


  protected void deleteAtBegin()
  {
	if (isEmpty())
	  throw new UnderflowException();

	head = head.next;
  }


  protected void deleteAtEnd()
  {
	if (isEmpty())
	  throw new UnderflowException();

	if (head.next == null)
	{
	  deleteAtBegin();
	  return;
	}

	Node<E> x = head;
	while (x.next.next != null)
	{
	  x = x.next;
	}

	deleteAfter(x);
  }


  protected void deleteAfter(Node<E> after)
  {
	if (after.next == null)
	  throw new IllegalArgumentException("Элемент является последним: " + after);

	after.next = after.next.next;
  }


  @Override
  public Iterator<E> iterator()
  {
	return new ListIterator<>(this);
  }


  protected static class Node<T>
  {

	T val;
	Node<T> next;


	public Node(T value)
	{
	  val = value;
	  next = null;
	}


	public void clear()
	{
	  val = null;
	  next = null;
	}

  }


  public static class UnderflowException
		  extends RuntimeException
  {

	UnderflowException()
	{
	  super("Список опустошен");
	}

  }

  private static class ListIterator<T>
		  implements Iterator<T>
  {

	private Node<T> cur;


	public ListIterator(SinglyLinkedList<T> list)
	{
	  cur = list.head;
	}


	@Override
	public boolean hasNext()
	{
	  return cur != null;
	}


	@Override
	public T next()
	{
	  if (cur == null)
		throw new IllegalStateException("Достигнут конец списка");

	  Node<T> node = cur;
	  cur = cur.next;

	  return node.val;
	}

  }

}