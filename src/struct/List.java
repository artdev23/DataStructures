package struct;


public interface List<E>
{

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


  boolean isEmpty();

}