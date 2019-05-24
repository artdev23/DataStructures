package struct;


import alg.ArraySort;
import alg.ArrayUtils;

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

  private static final int DEFAULT_CAPACITY = 10;


  public DynArray()
  {
	this(DEFAULT_CAPACITY);
  }


  @SuppressWarnings("unchecked")
  public DynArray(int capacity)
  {
	if (capacity <= 0)
	  throw new IllegalArgumentException("capacity должен быть натуральным числом");

	elem = (E[]) new Object[capacity];
	size = 0;
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
	indexCheck(index);

	return elem[index - 1];
  }


  public void set(int index, E val)
  {
	indexCheck(index);

	elem[index - 1] = val;
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


  public void add(E val)
  {
	insert(val, size + 1);
  }


  public void insert(E val, int index)
  {
	indexCheck(index);
	int i = index - 1;

	// Расширить массив
	extend();

	if (size - 1 - i >= 0)
	{
	  arraycopy(elem, i, elem, i + 1, size - 1 - i);
	}

	elem[i] = val;
	size++;
  }


  public void delete(int index)
  {
	indexCheck(index);
	int i = index - 1;


	if (size - 1 - i > 0)
	{
	  arraycopy(elem, i + 1, elem, i, size - 1 - i);
	}

	elem[size - 1] = null;
	size--;
  }


  public void delete(E e)
  {
	int index = indexOf(e);

	if (index == -1)
	  throw new IllegalArgumentException("Удаляемого элемента нет в массиве");

	delete(index);
  }


  public E extract(int index)
  {
	indexCheck(index);
	E e = get(index);
	delete(index);

	return e;
  }


  public void swap(int index, int index2)
  {
	ArrayUtils.swap(elem, index, index2);
  }


  public int size()
  {
	return size;
  }


  public int capacity()
  {
	return elem.length;
  }


  private void indexCheck(int index)
  {
	if (index <= 0)
	  throw new IllegalArgumentException("index должен быть натуральным числом");

	if (index >= elem.length)
	  throw new IllegalArgumentException("index выходит за границу");
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