// Title: DigitProductSequenceGenerator
// Files: ArrayList, Iterator
// Course: CS300 Fall 2018
//
// Author: Michael Goldstein
// Email: mdgoldstein2@wisc.edu
// Lecturer's Name: Alexi Brooks

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class represents a generator for a digit product sequence.
 * 
 * @author Michael Goldstein
 *
 */

public class DigitProductSequenceGenerator {
  private final int INIT; // initial number
  private final int SIZE; // size of sequence
  private ArrayList<Integer> sequence; // ArrayList object storing the sequence

  /**
   * Constructor. Creates a digit product sequence progression given the parameters
   * 
   * @param init the initial value of the sequence
   * @param size the size of the progression
   * @throws illegalArgumentException if init or size <=0
   */
  public DigitProductSequenceGenerator(int init, int size) {
    // checks that parameters are valid
    if (init <= 0) {
      throw new IllegalArgumentException("WARNING: The starting element for digit product sequence "
          + "cannot be less than or equal to zero.");
    }
    if (size <= 0) {
      throw new IllegalArgumentException("WARNING: CANNOT create a sequence with size <= zero.");
    }

    // initializes instance fields
    INIT = init;
    SIZE = size;
    sequence = new ArrayList<Integer>();

    // generates sequence
    generateSequence();
  }

  /**
   * Generates a digit product sequence with the given starting value and size using loops
   */
  public void generateSequence() {
    sequence.add(INIT); // adds first value

    // loop that creates and adds values to sequence ArrayList. Starts at 1 since first element is
    // auto-added
    for (int index = 1; index < SIZE; index++) {
      // converts the previous term to an array of Strings for easier removal of 0s
      String tempString = sequence.get(index - 1).toString();
      String splitString[] = tempString.split("");

      // finds the number of non zero digits in the previous term (to avoid using ArrayList in loop)
      int nonZeroDigits = 0;
      for (int index2 = 0; index2 < splitString.length; index2++) {
        if (!splitString[index2].equals("0")) {
          nonZeroDigits++;
        }
      }

      // converts to an integer array all non zero digits of the previous term in the sequence
      int digitsToMultiply[] = new int[nonZeroDigits];
      for (int index2 = 0; index2 < splitString.length; index2++) {
        if (!splitString[index2].equals("0")) {
          digitsToMultiply[index2] = Integer.parseInt(splitString[index2]);
        }
      }

      int product = 1; // temporary storage for product of non zero digits of previous term
      // finds product of non zero digits of previous term
      for (int index2 = 0; index2 < digitsToMultiply.length; index2++) {
        product *= digitsToMultiply[index2];
      }
      sequence.add(product + sequence.get(index - 1));
    }
  }

  /**
   * Returns a listIterator that traverses the sequence ArrayList
   * 
   * @return a listIterator that traverses the sequence ArrayList
   */
  public Iterator<Integer> getIterator() {
    return sequence.listIterator();
  }
}
