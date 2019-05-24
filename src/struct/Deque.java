package struct;


public class Deque<E extends Comparable<? super E>>
{

  private Stack<E> leftStack;
  private Stack<E> rightStack;


  public Deque()
  {
	leftStack = new Stack<>();
	rightStack = new Stack<>();
  }


  public void pushBack(E val)
  {
	leftStack.push(val);
  }


  public E popBack()
  {
	if (isEmpty())
	  throw new UnderflowException();

	if (!leftStack.isEmpty())
	  return leftStack.pop();

	Stack<E> st = new Stack<>();

	for (int i = 0; i <= rightStack.size() / 2; i++)
	{
	  E e = rightStack.pop();
	  st.push(e);
	}

	while (!rightStack.isEmpty())
	{
	  leftStack.push(rightStack.pop());
	}

	while (!st.isEmpty())
	{
	  rightStack.push(st.pop());
	}

	return leftStack.pop();
  }


  public void pushFront(E val)
  {
	rightStack.push(val);
  }


  public E popFront()
  {
	if (isEmpty())
	  throw new UnderflowException();

	if (!rightStack.isEmpty())
	  return rightStack.pop();

	Stack<E> st = new Stack<>();

	for (int i = 0; i <= leftStack.size() / 2; i++)
	{
	  E e = leftStack.pop();
	  st.push(e);
	}

	while (!leftStack.isEmpty())
	{
	  rightStack.push(leftStack.pop());
	}

	while (!st.isEmpty())
	{
	  leftStack.push(st.pop());
	}

	return rightStack.pop();
  }


  public boolean isEmpty()
  {
	return leftStack.isEmpty() && rightStack.isEmpty();
  }


  public static class UnderflowException
		  extends RuntimeException
  {

	UnderflowException()
	{
	  super("Дек опустошен");
	}

  }

}