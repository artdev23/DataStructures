package struct;


import java.util.ArrayList;


public class HashTable<K, V>
{

  private ArrayList<ListADT<Entry>> blocks;

  private static final int DEFAULT_BLOCKS_COUNT = 1000;


  public HashTable()
  {
	blocks = new ArrayList<>(DEFAULT_BLOCKS_COUNT);

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
	block.tryDelete(entry);
	block.insertFirst(entry);
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
  }


  private int hash(K key)
  {
	return key.hashCode() % DEFAULT_BLOCKS_COUNT;
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