// Title: DrawingStackIterator
// Files: DrawingStack
// Course: CS300 Fall 2018
//
// Author: Michael Goldstein
// Email: mdgoldstein2@wisc.edu
// Lecturer's Name: Alexi Brooks

import java.util.Iterator;

/**
 * This class creates and runs an iterator over a DrawingStack
 * 
 * @author Michael Goldstein
 *
 * @param <DrawingChange> the type of object the iterator returns
 * @see DrawingStack where the constructor of this class is called
 */
public class DrawingStackIterator implements Iterator<DrawingChange> {
  private Node<DrawingChange> next; // the node the iterator is "behind"

  /**
   * Constructor. Initialized the next Node to the top of the stack
   * 
   * @param topOfStack the top node of the stack to be iterated through
   */
  public DrawingStackIterator(Node<DrawingChange> topOfStack) {
    next = topOfStack;
  }

  /**
   * Returns the data in the node the iterator is behind and moves up the iterator by one node
   * 
   * @return the data in the node the iterator is behind
   */
  public DrawingChange next() {
    DrawingChange tempStore = next.getData();
    next = next.getNext();
    return tempStore;
  }

  /**
   * Checks if there is another node ahead of the iterator
   * 
   * @return true if there is a node ahead of the iterator, false if there is not
   */
  public boolean hasNext() {
    if (next == null) {
      return false;
    } else {
      return true;
    }
  }
}
