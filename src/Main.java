import struct.DynArray;

import java.util.Random;

import static java.lang.System.out;


public class Main
{

  public static void main(String[] args)
  {
	DynArray<Number> array = new DynArray<>(40_000);

	fillRand(array);
	out.println(array);
  }


  private static void fillRand(DynArray<Number> a)
  {
	Random rand = new Random();

	for (int i = 1; i < a.size(); i++)
	{
	  int val = rand.nextInt(1000);
	  a.set(i, val);
	}
  }

}