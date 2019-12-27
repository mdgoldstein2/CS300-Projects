// Title: WaitingQueueADT
// Files: Comparable
// Course: CS300 Fall 2018
//
// Author: Michael Goldstein
// Email: mdgoldstein2@wisc.edu
// Lecturer's Name: Alexi Brooks

/**
 * This interface provides a set of methods that a process waiting list must implement to work
 * correctly (as a priority queue)
 * 
 * @author Michael Goldstein
 *
 * @param <T> the type of object to be stored in the waiting queue 
 */
public interface WaitingQueueADT<T extends Comparable<T>> {
  /**
   * Inserts a newObject into the waiting queue
   * @param newObject an object of type T to be added to the waiting queue
   */
  public void enqueue(T newObject);

  /**
   * Removes and returns the object with the highest priority from the waiting queue
   * @return the object with the highest waiting
   */
  public T dequeue();

  /**
   * Returns (without removing) the object with the highest priority in the waiting queue
   * @return the object with the highest priority
   */
  public T peek();

  /**
   * Returns the size of the waiting queue
   * @return the size of the waiting queue
   */
  public int size();

  /**
   * Checks if the waiting queue is empty
   * @return true if the waiting queue is empty, false if it is not
   */
  public boolean isEmpty();
}
