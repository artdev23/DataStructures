import struct.DynArray;

import java.util.Random;
import java.util.function.Consumer;

import static java.lang.System.*;
import static java.util.concurrent.TimeUnit.NANOSECONDS;


public class Sort
{

  public static void main(String[] args)
  {
	DynArray<Integer> array = new DynArray<>(10_000);

	fillRand(array);

	out.println("Пузырьковая сортировка");
	checkExecTime(array, DynArray::bubbleSort);

	out.println("Сортировка выбором");
	checkExecTime(array, DynArray::selectionSort);

	out.println("Сортировка вставкой");
	checkExecTime(array, DynArray::insertionSort);
  }


  static void checkExecTime(DynArray<Integer> a, Consumer<DynArray<Integer>> alg)
  {
	DynArray<Integer> copy = a.clone();

	long start = nanoTime();
	alg.accept(copy);
	long finish = nanoTime();
	long t = NANOSECONDS.toMillis(finish - start);

	out.println(copy);
	out.println("Time of exec: " + t + " ms");
	out.println();
  }


  static void fillRand(DynArray<Integer> a)
  {
	Random rand = new Random();

	for (int i = 1; i <= a.size(); i++)
	{
	  int val = rand.nextInt(10_000);
	  a.set(i, val);
	}
  }

}