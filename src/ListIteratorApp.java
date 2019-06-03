import struct.ListADT;
import struct.SinglyLinkedList;

import java.util.Iterator;

import static java.lang.System.out;


public class ListIteratorApp
{

  public static void main(String[] args)
  {
	ListADT<String> list = new SinglyLinkedList<>();

	list.insertFirst("JavaScript");
	list.insertFirst("C#");
	list.insertFirst("Java");
	list.insertLast("Scala");

	displayForEach(list);
	out.println();

	list.delete("JavaScript");
	displayIterator(list);
  }


  static void displayForEach(ListADT<String> list)
  {
	for (String e : list)
	{
	  out.println(e);
	}
  }


  static void displayIterator(ListADT<String> list)
  {
	Iterator<String> it = list.iterator();

	while (it.hasNext())
	{
	  String e = it.next();
	  out.println(e);
	}
  }

}