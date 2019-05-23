package alg;


public interface ArraySort
{

  static <E extends Object & Comparable<? super E>>
  void insertionSort(E[] a)
  {
	int n = a.length;

	for (int i = 1; i < n; i++)
	{
	  E key = a[i];

	  int j = i - 1;
	  while (j >= 0 && a[j].compareTo(key) > 0)
	  {
		a[j + 1] = a[j];
		j--;
	  }

	  a[j + 1] = key;
	}
  }


  static <E extends Object & Comparable<? super E>>
  void selectionSort(E[] a)
  {
	int n = a.length;

	for (int i = 0; i < n - 1; i++)
	{
	  int min = i;
	  for (int j = i + 1; j < n; j++)
	  {
		if (a[j].compareTo(a[min]) < 0)
		  min = j;
	  }

	  if (min == i)
		continue;

	  E temp = a[i];
	  a[i] = a[min];
	  a[min] = temp;
	}
  }


  static <E extends Object & Comparable<? super E>>
  void bubbleSort(E[] a)
  {
	int n = a.length;
	boolean sorted = false;

	while (!sorted)
	{
	  sorted = true;
	  for (int i = 1; i < n; i++)
	  {
		if (a[i].compareTo(a[i - 1]) < 0)
		{
		  E temp = a[i];
		  a[i] = a[i - 1];
		  a[i - 1] = temp;
		  sorted = false;
		}
	  }
	}
  }


}