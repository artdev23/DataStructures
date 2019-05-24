package struct;


public interface QueueADT<E>
{

  void enqueue(E val);

  E dequeue();

  boolean isEmpty();


  class OverflowException
		  extends RuntimeException
  {

	OverflowException()
	{
	  super("Очередь переполнена");
	}

  }

  class UnderflowException
		  extends RuntimeException
  {

	UnderflowException()
	{
	  super("Очередь опустошена");
	}

  }

}