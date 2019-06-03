package struct;


public class ArrayStack<E extends Comparable<? super E>>
		implements StackADT<E>
{

  private DynArray<E> S;
  private int top;

  private static final int DEFAULT_ARRAY_CAPACITY = 100;


  public ArrayStack()
  {
	S = new DynArray<>(DEFAULT_ARRAY_CAPACITY);
	top = 0;
  }


  @Override
  public void push(E val)
  {
	S.insert(val, top + 1);
	top++;
  }


  @Override
  public E pop()
  {
	if (isEmpty())
	  throw new UnderflowException();

	E elem = S.get(top);
	S.delete(top);
	top--;

	return elem;
  }


  @Override
  public boolean isEmpty()
  {
	return top == 0;
  }


  @Override
  public int size()
  {
	return S.size();
  }


}