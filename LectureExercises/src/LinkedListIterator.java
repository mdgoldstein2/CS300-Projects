import java.util.Iterator;

/*
 * DIRECT iterator for our LinkedList data structure class
 */
public class LinkedListIterator<E> implements Iterator<E> {
  // FIELDS
  private ListNode<E> curr;

  // CONSTRUCTOR
  public LinkedListIterator(ListNode<E> head) {
    curr = head;
  }

  // METHODS
  @Override
  public boolean hasNext() {
    return curr != null;
  }

  @Override
  public E next() {
    // Get the data from current item
    E item = curr.getData();
    // Increment curr reference
    curr = curr.getNext();
    // Return the item we retrieved
    return item;
  }
}
