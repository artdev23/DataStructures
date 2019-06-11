package struct;


import java.util.function.Consumer;


public interface ListADT<E>
		extends Iterable<E>
{

  void iterate(Consumer<E> action);

  boolean contains(E val);


  void insertFirst(E val);

  void insertLast(E val);

  void insertAfter(E after, E val);

  void insertBefore(E before, E val);


  void delete(E val);

  void deleteFirst();

  void deleteLast();

  void deleteAfter(E after);

  void deleteBefore(E before);


  void clear();


  boolean isEmpty();

  E first();

  int size();

}