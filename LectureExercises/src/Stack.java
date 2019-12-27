
public class Stack<E> {
  private ListNode<E> top; //node at top of stack
  
  public Stack(){
   
  }
  
  public void push(E item) {
    if (item==null) {
      throw new IllegalArgumentException();
    } else if(top==null) {
      top = new ListNode<E>(item, null);
    } else {
      top = new ListNode<E>(item, top);
    }
  }
  
  public E peek() {
    return top.getData();
  }
  
  public E pop() {
    E tempData = top.getData();
    top=top.getNext();
    return tempData;
  }
  
  public static void main(String[] args) {
    
  }
}
