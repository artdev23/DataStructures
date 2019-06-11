package struct;


import java.util.ArrayList;
import java.util.function.BiConsumer;

import static java.lang.Math.round;


public class HashTable<K, V>
{

  private ArrayList<ListADT<Entry>> blocks;
  private int size;

  private static final int DEFAULT_BLOCKS_COUNT = 1000;


  public HashTable()
  {
	blocks = new ArrayList<>(DEFAULT_BLOCKS_COUNT);
	size = 0;

	for (int i = 0; i < DEFAULT_BLOCKS_COUNT; i++)
	{
	  blocks.add(new SinglyLinkedList<>());
	}
  }


  public void insert(K key, V value)
  {
	int h = hash(key);
	ListADT<Entry> block = blocks.get(h);

	Entry entry = new Entry(key, value);
	boolean deleted = block.tryDelete(entry);
	block.insertFirst(entry);
	if (!deleted)
	  size++;

	checkBlockLength();
  }


  public V find(K key)
  {
	int h = hash(key);
	ListADT<Entry> block = blocks.get(h);

	Entry entry = block.find(x -> x.key().equals(key));

	if (entry == null)
	  throw new IllegalArgumentException("Запись по данному ключу отсутствует");

	return entry.value();
  }


  public void delete(K key)
  {
	int h = hash(key);
	ListADT<Entry> block = blocks.get(h);

	Entry entry = block.find(x -> x.key().equals(key));

	if (entry == null)
	  throw new IllegalArgumentException("Запись по данному ключу отсутствует");

	block.delete(entry);
	size--;
  }


  public void iterate(BiConsumer<K, V> action)
  {
	blocks.stream()
		  .filter(x -> !x.isEmpty())
		  .forEach(x ->
				   {
					 for (Entry e : x)
					 {
					   action.accept(e.key(), e.value());
					 }
				   });
  }


  public void clear()
  {
	blocks.stream()
		  .filter(x -> !x.isEmpty())
		  .forEach(ListADT::clear);

	size = 0;
  }


  public boolean isEmpty()
  {
	return size == 0;
  }


  public int size()
  {
	return size;
  }


  private void checkBlockLength()
  {
	if (size < blocks.size() / 2)
	  return;

	long count = blocks.stream()
					   .filter(x -> x.size() > 10)
					   .count();

	long percent = round(count * 100f / blocks.size());

	if (percent > 20)
	  extend();
  }


  private void extend()
  {
	ArrayList<ListADT<Entry>> temp = blocks;

	blocks = new ArrayList<>(blocks.size() * 2);

	for (int i = 0; i < DEFAULT_BLOCKS_COUNT; i++)
	{
	  blocks.add(new SinglyLinkedList<>());
	}

	rehashing(temp);
  }


  private void rehashing(ArrayList<ListADT<Entry>> temp)
  {
	for (ListADT<Entry> block : temp)
	{
	  for (Entry e : block)
	  {
		int h = hash(e.key());
		blocks.get(h).insertFirst(e);
	  }
	}
  }


  private int hash(K key)
  {
	return key.hashCode() % blocks.size();
  }


  private class Entry
		  extends Pair<K, V>
  {

	Entry(K key, V value)
	{
	  super(key, value);
	}


	@Override
	public boolean equals(Object obj)
	{
	  if (obj.getClass() != Entry.class)
		return false;

	  K k = ((Entry) obj).key();

	  return key().equals(k);
	}


	K key()
	{
	  return first;
	}


	V value()
	{
	  return second;
	}

  }


}