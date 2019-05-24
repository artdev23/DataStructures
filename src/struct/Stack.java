package struct;


public class Stack<E extends Comparable<? super E>>
{

  private DynArray<E> S;
  private int top;

  private static final int DEFAULT_ARRAY_CAPACITY = 100;


  public Stack()
  {
	S = new DynArray<>(DEFAULT_ARRAY_CAPACITY);
	top = 0;
  }


  public void push(E val)
  {
	S.insert(val, top + 1);
	top++;
  }


  public E pop()
  {
	if (isEmpty())
	  throw new UnderflowException();

	E elem = S.get(top);
	S.delete(top);
	top--;

	return elem;
  }


  public boolean isEmpty()
  {
	return top == 0;
  }


  public static class UnderflowException
		  extends RuntimeException
  {

	UnderflowException()
	{
	  super("Стек опустошен");
	}

  }

}