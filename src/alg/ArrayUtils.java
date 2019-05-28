package alg;


import struct.ArrayStack;


public final class ArrayUtils
{

  public static <E extends Comparable<? super E>>
  void reverse(E[] array)
  {
	int last = array.length - 1;
	ArrayStack<E> st = new ArrayStack<>();

	for (int i = 0; i <= last; i++)
	{
	  st.push(array[i]);
	}

	for (int i = 0; i <= last; i++)
	{
	  array[i] = st.pop();
	}
  }


  public static <E>
  void swap(E[] array, int index, int index2)
  {
	if (index == index2)
	  return;

	E temp = array[index];
	array[index] = array[index2];
	array[index2] = temp;
  }

}