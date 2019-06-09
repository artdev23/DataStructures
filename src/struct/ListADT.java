package struct;


import java.util.function.Consumer;
import java.util.function.Predicate;


public interface ListADT<E>
		extends Iterable<E>
{

  void iterate(Consumer<E> action);

  boolean contains(E val);

  E find(Predicate<E> predicate);


  void insertFirst(E val);

  void insertLast(E val);

  void insertAfter(E after, E val);

  void insertBefore(E before, E val);


  void delete(E val);

  void deleteFirst();

  void deleteLast();

  void deleteAfter(E after);

  void deleteBefore(E before);

  boolean tryDelete(E val);


  void clear();


  boolean isEmpty();

  E first();

  int size();

}