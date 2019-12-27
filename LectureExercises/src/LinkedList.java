import java.util.Iterator;

public class LinkedList<E> implements ListADT<E>, Iterable<E> {

  // FIELDS
  ListNode<E> head; // Reference to the first node in the chain
  // To get the second node in the chain, ask head for head.getNext()
  ListNode<E> tail; // Reference to the last node in the chain (technically optional!)
  int size;
  // NOTE: Every node should have data! No "null" nodes.

  // CONSTRUCTOR
  public LinkedList() {
    // do something to our head reference
    // same for tail reference
    size = 0;
    // Because no null nodes, we should NOT make any ListNode objects yet!
    // For now head and tail both stay == null
    // WATCH OUT FOR THIS!
  }

  // METHODS (auto-generated)
  @Override
  public void add(E item) { // TODO fix for tail reference
    // Add at the END of the list
    // add(size,item);
    // Special case: if head==null
    if (null == head) {
      head = new ListNode<E>(item, null);
      size++;
      return;
    }
    // Start at head and do getNext until we get to null
    ListNode<E> curr = head;
    while (curr.getNext() != null)
      curr = curr.getNext();
    // Claim: Need curr to not be null so that we can append a new item as its next field
    curr.setNext(new ListNode<E>(item, null));
    size++;
  }

  @Override
  public void add(int position, E item) { // TODO fix for tail reference
    if (position < 0 || position > size)
      throw new IndexOutOfBoundsException();

    // Special case: if head==null
    if (null == head) {
      head = new ListNode<E>(item, null);
      size++;
      return;
    }
    // Start at head and do getNext until we get to null
    ListNode<E> curr = head;
    int index = 0; // counter to track how far curr has gone
    while (curr.getNext() != null && index < position - 1) {
      curr = curr.getNext();
      index++;
    }
    // Claim: Need curr to not be null so that we can append a new item as its next field
    curr.setNext(new ListNode<E>(item, curr.getNext()));
    size++;
  }

  @Override
  public E get(int position) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public E remove(int position) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean contains(E item) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public int size() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public boolean isEmpty() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public int indexOf(E item) {
    // TODO Auto-generated method stub
    return 0;
  }
  
  public Iterator<E> iterator() {
    return new LinkedListIterator<E>(head);
  }
}
