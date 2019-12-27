public class ListNode <E> {
  //FIELDS
  E data;
  ListNode<E> next;
  
  //CONSTRUCTORS
  //If we want a stand-alone node, just make next == null
  public ListNode(E item, ListNode<E> next) {
    this.data = item;
    this.next = next;
  }
  //METHODS
  public E getData() {
    return data;
  }
 
  public void setData(E data) {
    this.data = data;
  }
 
  public ListNode<E> getNext() {
    return next;
  }
 
  public void setNext(ListNode<E> next) {
    this.next = next;
  }
  
}