package struct;


public class SinglyLinkedList<E>
		implements List<E>
{

  private Node<E> head;


  public SinglyLinkedList()
  {
	head = null;
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
  }


  @Override
  public void insertLast(E val)
  {
	Node<E> x = new Node<>(val);
	addAtEnd(x);
  }


  @Override
  public void insertAfter(E after, E val)
  {
	Node<E> node = find(after);

	if (node == null)
	  throw new IllegalArgumentException("Элемента нет в списке: " + after);

	insertAfter(node, new Node<>(val));
  }


  @Override
  public void insertBefore(E before, E val)
  {
	Pair<Node<E>, Node<E>> elem = findNodeAndBefore(before);

	if (elem.first == null)
	  throw new IllegalArgumentException("Элемента нет в списке: " + before);

	Node<E> x = new Node<>(val);

	if (elem.second == null)
	{
	  addAtBegin(x);
	}
	else
	{
	  insertAfter(elem.second, x);
	}
  }


  @Override
  public void delete(E val)
  {
	deleteElem(val);
  }


  @Override
  public void deleteFirst()
  {
	deleteAtBegin();
  }


  @Override
  public void deleteLast()
  {
	deleteAtEnd();
  }


  @Override
  public void deleteAfter(E after)
  {
	Node<E> node = find(after);

	if (node == null)
	  throw new IllegalArgumentException("Элемента нет в списке: " + after);

	deleteAfter(node);
  }


  @Override
  public void deleteBefore(E before)
  {
	Pair<Node<E>, Node<E>> elem = findNodeAndBefore(before);

	if (elem.first == null)
	  throw new IllegalArgumentException("Элемента нет в списке: " + before);

	if (elem.second == null)
	  deleteAtBegin();
	else
	  deleteAfter(elem.second);
  }


  @Override
  public boolean isEmpty()
  {
	return head == null;
  }


  private Node<E> find(E val)
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


  private Pair<Node<E>, Node<E>> findNodeAndBefore(E val)
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


  private void addAtBegin(Node<E> x)
  {
	x.next = head;
	head = x;
  }


  private void addAtEnd(Node<E> x)
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


  private void insertAfter(Node<E> after, Node<E> x)
  {
	x.next = after.next;
	after.next = x;
  }


  private void deleteElem(E val)
  {
	Pair<Node<E>, Node<E>> elem = findNodeAndBefore(val);

	if (elem == null)
	  throw new IllegalArgumentException("Удаляемого элемента нет в списке");

	Node<E> delElem = elem.first;
	Node<E> prev = elem.second;

	if (prev != null)
	  prev.next = delElem.next;
	else
	  head = delElem.next;
  }


  private void deleteAtBegin()
  {
	if (isEmpty())
	  throw new UnderflowException();

	head = head.next;
  }


  private void deleteAtEnd()
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


  private void deleteAfter(Node<E> after)
  {
	after.next = after.next.next;
  }


  private class Node<T>
  {

	T val;
	Node<T> next;


	public Node(T value)
	{
	  val = value;
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

}