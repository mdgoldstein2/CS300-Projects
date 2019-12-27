
// Title: FibonacciSequenceGenerator
// Files: Iterator
// Course: CS300 Fall 2018
//
// Author: Michael Goldstein
// Email: mdgoldstein2@wisc.edu
// Lecturer's Name: Alexi Brooks

import java.util.Iterator;

/**
 * This class represents a generator for a Fibonacci progression This class implements the
 * Iterator<Integer> interface
 * 
 * @author Michael Goldstein
 */
public class FibonacciSequenceGenerator implements Iterator<Integer> {
  private final int SIZE; // number of elements in this sequence
  private int prev; // previous item in the sequence with respect to the current iteration
  private int next; // next item in the sequence with respect to the current iteration
  private int generatedCount; // number of items generated so far


  /**
   * Constructor. Creates a fibonacci progession from the given parameters
   * 
   * @param size the number of terms in the progression
   * @throws IllegalArgumentException if size<=0
   */
  public FibonacciSequenceGenerator(int size) {
    if (size <= 0) {
      throw new IllegalArgumentException(
          "WARNING: " + "CANNOT create a sequence with size <= zero.");
    }

    // sets instance fields
    prev = 0;
    next = 1;
    generatedCount = 0;
    SIZE = size;
  }

  /**
   * Checks if the iteration has a next element in this sequence
   * 
   * @return true if the current element in the iteration has a next element in this sequence, false
   *         otherwise
   */
  @Override
  public boolean hasNext() {
    // time complexity: O(1)
    if (generatedCount < SIZE) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Returns the next element in this fibonacci sequence iteration with respect to the numbers
   * generated so far
   * 
   * @return the next element in this iteration
   */
  @Override
  public Integer next() {
    // time complexity: O(1)
    if (!hasNext()) {// check if the current element has a next element in this sequence
      return null;
    }
    int current = prev; // sets the "current" element (one to be returned)
    generatedCount++; // increment the number of generated elements so far
    prev = next; //sets the new previous element
    next += current; // sets the new next element (sum of current and previous next)
    return current; // return the current number as the generated one
  }
}
