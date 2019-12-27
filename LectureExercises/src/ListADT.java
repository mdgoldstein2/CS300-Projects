public interface ListADT<E> {
  //Modeled off of Java's List<E> interface,
  //BUT only cover the CORE methods
  
  //Implementors should be Linear
  //Should have an "index" concept
  
  public void add(E item);
  public void add(int position, E item);
  
  public E get(int position);
  public E remove(int position); //Our remove returns the removed item
  
  public boolean contains(E item); //return true if item is present, false otherwise
  public int size();
  public boolean isEmpty();
  public int indexOf(E item);
}