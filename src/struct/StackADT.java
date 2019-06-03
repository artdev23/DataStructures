package struct;


public interface StackADT<E>
{

  void push(E val);

  E pop();

  boolean isEmpty();

  int size();

  class UnderflowException
		  extends RuntimeException
  {

	UnderflowException()
	{
	  super("Стек опустошен");
	}

  }

}