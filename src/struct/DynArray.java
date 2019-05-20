package struct;


import alg.ArraySort;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;

import static java.lang.System.arraycopy;
import static java.util.Arrays.*;


public class DynArray<E extends Object & Comparable<? super E>>
		implements Iterable<E>, Cloneable
{

  private E[] elem;
  private int size;


  @SuppressWarnings("unchecked")
  public DynArray(int size)
  {
	if (size <= 0)
	  throw new IllegalArgumentException("size должен быть натуральным числом");

	elem = (E[]) new Object[size];
	this.size = size;
  }


  public DynArray(E[] array)
  {
	if (array == null)
	  throw new IllegalArgumentException("array is null");

	if (array.length == 0)
	  throw new IllegalArgumentException("array is empty");

	elem = copyOf(array, array.length);
	size = array.length;
  }


  public E get(int index)
  {
	int i = indexCheck(index);

	return elem[i];
  }


  public void set(int index, E val)
  {
	int i = indexCheck(index);

	elem[i] = val;
  }


  public void sort(Consumer<E[]> alg)
  {
	alg.accept(elem);
  }


  public void insertionSort()
  {
	ArraySort.insertionSort(elem);
  }


  public void selectionSort()
  {
	ArraySort.selectionSort(elem);
  }


  public void bubbleSort()
  {
	ArraySort.bubbleSort(elem);
  }


  public int indexOf(E e)
  {
	for (int i = 0; i < size; i++)
	{
	  if (elem[i].equals(e))
		return i + 1;
	}

	return -1;
  }


  public void insert(E val, int index)
  {
	int i = indexCheck(index);

	// Расширить массив
	extend();

	if (size - 1 - i >= 0)
	{
	  arraycopy(elem, i, elem, i + 1, size - 1 - i);
	}

	elem[i] = val;
	size++;
  }


  public void delete(E e)
  {
	int index = indexOf(e);

	if (index == -1)
	  throw new IllegalArgumentException("Удаляемого элемента нет в массиве");

	int i = index - 1;

	if (size - 1 - i >= 0)
	{
	  arraycopy(elem, i + 1, elem, i, size - 1 - i);
	}

	elem[size - 1] = null;
	size--;
  }


  public int size()
  {
	return size;
  }


  private int indexCheck(int index)
  {
	if (index <= 0)
	  throw new IllegalArgumentException("index должен быть натуральным числом");

	if (index > size)
	  throw new IllegalArgumentException("index выходит за границу");

	return index - 1;
  }


  @SuppressWarnings("unchecked")
  private void extend()
  {
	if (size < elem.length)
	  return;

	E[] temp = elem;

	elem = (E[]) new Object[size * 2];

	arraycopy(temp, 0, elem, 0, size);
  }


  @Override
  public Iterator<E> iterator()
  {
	return stream(elem).iterator();
  }


  @Override
  public DynArray<E> clone()
  {
	return new DynArray<>(elem);
  }


  @Override
  public String toString()
  {
	return Arrays.toString(elem);
  }

}