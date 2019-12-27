
// Title: DrawingStack
// Files: StackADT, DrawingStackIterator
// Course: CS300 Fall 2018
//
// Author: Michael Goldstein
// Email: mdgoldstein2@wisc.edu
// Lecturer's Name: Alexi Brooks

import java.util.Iterator;

/**
 * This class creates and runs stack of nodes of DrawingChange objects
 * 
 * @author Michael Goldstein
 *
 * @param <DrawingChange> the type parameter of this class and the Nodes the stack is made from
 * @see StackADT<T> the interface this class implemets
 * @see Node<T> the class that the nodes in the stack are of
 */
public class DrawingStack implements StackADT<DrawingChange> {
  private Node<DrawingChange> head; // node at top of stack
  private int size; // size of stack

  /**
   * Constructor for DrawingStack. Initializes head node (top of stack) and size
   */
  public DrawingStack() {
    head = null;
    size = 0;
  }

  /**
   * Add an element to this stack
   * 
   * @param element an element to be added
   * @throws IllegalArgumentException if the input element is null
   */
  public void push(DrawingChange element) throws IllegalArgumentException {
    // checks if input element is null, throws exception if it is, otherwise adds node based on if
    // head is null or not
    if (element == null) {
      throw new IllegalArgumentException();
    } else if (head == null) {
      // if head is null (no nodes in stack) head is set to a new node constructed with element as a
      // parameter
      head = new Node<DrawingChange>(element, null);
    } else {
      // if head is not null, head is set to a new node constructed with element and the head node
      // prior to the new node being added as parameters
      head = new Node<DrawingChange>(element, head);
    }
    // increments size
    size++;
  }

  /**
   * Remove the element on the stack top and return it
   * 
   * @return the element removed from the stack top
   */
  public DrawingChange pop() {
    // creates and loads temporarily data holder with data from head
    DrawingChange tempData = head.getData();
    head = head.getNext(); // removes current head by setting head to the node after the current
                           // head
    size--; // decreases size
    return tempData;
  }

  /**
   * Get the element on the stack top
   * 
   * @return the element on the stack top
   */
  public DrawingChange peek() {
    // returns the data from the head node (which is the element on top)
    return head.getData();
  }

  /**
   * Returns true if this stack contains no elements.
   * 
   * @return true if this stack contains no elements, otherwise false
   */
  public boolean isEmpty() {
    // if head is null, the stack is empty
    if (head == null) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Get the number of elements in the stack
   * 
   * @return the size of the stack
   */
  public int size() {
    return size;
  }

  /**
   * Returns an iterator over the stack
   * 
   * @return iterator of the stack
   */
  public Iterator<DrawingChange> iterator() {
    return new DrawingStackIterator(head);
  }
}
