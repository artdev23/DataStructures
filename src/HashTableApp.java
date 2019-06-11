import struct.HashTable;

import static java.lang.System.out;


public class HashTableApp
{


  public static void main(String[] args)
  {
	HashTable<String, Integer> table = new HashTable<>();

	table.insert("JavaScript", 40);
	table.insert("C#", 80);
	table.insert("Java", 70);
	table.insert("Scala", 100);

	display(table);

	table.delete("JavaScript");
	display(table);

	out.println("Java: " + table.find("Java"));
	out.println();

	table.clear();
	display(table);
	out.println("table is " + (table.isEmpty() ? "empty" : "not empty"));
  }


  private static void display(HashTable<String, Integer> table)
  {
	out.println("{");
	table.iterate((k, v) -> out.println("\t" + k + ": " + v));
	out.println("}");
	out.println();
  }

}