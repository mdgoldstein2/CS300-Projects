
// Title: SequenceGeneratorTests
// Files: GeometricSequenceGenerator, FibonacciSequenceGenerator, DigitProductSequenceGenerator,
// Iterator
// Course: CS300 Fall 2018
//
// Author: Michael Goldstein
// Email: mdgoldstein2@wisc.edu
// Lecturer's Name: Alexi Brooks

import java.util.Iterator;

/**
 * Test class for student created SequenceGenetor classes
 * 
 * @author Michael Goldstein
 *
 */
public class SequenceGeneratorTests {
  /**
   * Tests the constructor, hasNext(), and next() methods of GeometricSequenceGenerator
   * 
   * @return true if the constructor, hasNext, and next() pass all test cases, false if they do not
   * @see GeometricSequenceGenerator#GeometricSequenceGenerator(int, int, int)
   * @see GeometricSequenceGenerator#hasNext()
   * @see GeometricSequenceGenerator#next()
   */
  public static Boolean geometricSequenceGeneratorTest() {
    // good test values used as parameters when creating tester instance of
    // GeometricSequenceGenerator
    final int TEST_START = 1;
    final int TEST_RATIO = 5;
    final int TEST_SIZE = 10;

    // known bad test values used to test constructor
    final int BAD_TEST_START = -2;
    final int BAD_TEST_RATIO = 0;
    final int BAD_TEST_SIZE = 0;

    // tests to make sure invalid parameters are prevented by the constructor
    // tests if negative size throws error
    try {
      // creates instance of GeometricSequenceGenerator with bad start parameter
      GeometricSequenceGenerator tester =
          new GeometricSequenceGenerator(BAD_TEST_START, TEST_RATIO, TEST_SIZE);
      return false; // test fails if exception is nor thrown for negative start value
    } catch (IllegalArgumentException A) {
      // nothing happens. Breaks before false is returned if exception is thrown, which it should be
    }

    // tests if 0 (or negative) ratio throws error
    try {
      // creates instance of GeometricSequenceGenerator with bad ratio parameter
      GeometricSequenceGenerator tester =
          new GeometricSequenceGenerator(TEST_START, BAD_TEST_RATIO, TEST_SIZE);
      return false; // test fails if exception is nor thrown for 0 ratio value
    } catch (IllegalArgumentException A) {
      // nothing happens. Breaks before false is returned if exception is thrown, which it should be
    }

    // tests if 0 (or negative) sequence size throws error
    try {
      // creates instance of GeometricSequenceGenerator with bad size parameter
      GeometricSequenceGenerator tester =
          new GeometricSequenceGenerator(TEST_START, TEST_RATIO, BAD_TEST_SIZE);
      return false; // test fails if exception is nor thrown for 0 ratio value
    } catch (IllegalArgumentException A) {
      // nothing happens. Breaks before false is returned if exception is thrown, which it should be
    }

    // good tester instance of GeometricSequenceGenerator
    GeometricSequenceGenerator tester =
        new GeometricSequenceGenerator(TEST_START, TEST_RATIO, TEST_SIZE);

    // goes through sequence and makes sure next() works correctly by comparing its output to a
    // reference value
    int referenceValue = TEST_START;
    for (int index = 0; index < TEST_SIZE; index++) {
      if (tester.next() != referenceValue) {
        return false; // test fails if returned value of next() does not meet the reference value
      } else {
        // multiplies referenceValue by given ratio to give accurate referenceValue for next
        // number in sequence
        referenceValue *= TEST_RATIO;
      }
    }

    // tests hasNext() by calling it after a sequence with the given size has already been traversed
    // through
    if (tester.hasNext()) {
      return false; // hasNext() should return false in thi situation, so if it returns true,
    } // the test fails
    return true; // returns true if no test case fails
  }

  /**
   * Tests the constructor, hasNext(), and next() methods of FibbonaciSequenceGenerator
   * 
   * @return true if the constructor, hasNext, and next() pass all test cases, false if they do not
   * @see FibonacciSequenceGenerator#GeometricSequenceGenerator(int, int, int)
   * @see FibonacciSequenceGenerator#hasNext()
   * @see FibonacciSequenceGenerator#next()
   */
  public static Boolean fibonacciSequenceGeneratorTest() {
    final int BAD_TEST_SIZE = 0; // known bad size parameter value (0 or negative)
    final int TEST_SIZE = 6; // good size parameter value to be used when testing next() and
                             // hasNext()

    // tests if constructor catches bad size parameter
    try {
      // creates instance of GeometricSequenceGenerator with bad size parameter
      FibonacciSequenceGenerator tester = new FibonacciSequenceGenerator(BAD_TEST_SIZE);
      return false; // test fails if exception is nor thrown for bad (<=0) size value
    } catch (IllegalArgumentException A) {
      // nothing happens. Breaks before false is returned if exception is thrown, which it should be
    }

    // tester instance of FibbonacciSeauenceGenerator which operations will be performed on
    FibonacciSequenceGenerator tester = new FibonacciSequenceGenerator(TEST_SIZE);

    // goes through sequence and makes sure next() works correctly by comparing its output to a set
    // of reference values

    // reference values (in order) for fibonacci sequence
    int referenceValue[] = new int[TEST_SIZE];

    // sets reference values
    // first two values are 0 and 1, set directly
    referenceValue[0] = 0;
    referenceValue[1] = 1;
    for (int index = 2; index < TEST_SIZE; index++) {
      // values are sum of previous two values
      referenceValue[index] = referenceValue[index - 1] + referenceValue[index - 2];
    }

    // compares output of next() to reference value
    for (int index = 0; index < TEST_SIZE; index++) {
      if (tester.next() != referenceValue[index]) {
        return false; // test fails if returned value of next() does not meet the reference value
      }
    }

    return true; // returns true if all test cases passed
  }

  /**
   * Tests the constructor, generateSequnce() and getIterator method of
   * digitProductSequenceGenerator
   * 
   * @return true if the methods pass all test cases, false if they do not
   * @see DigitProductSequenceGenerator#DigitProductSequenceGenerator(int, int)
   * @see DigitProductSequenceGenerator#generateSequence()
   * @see DigitProductSequenceGenerator#getIterator()
   */
  public static Boolean digitProductSequenceGeneratorTest() {
    // bad values for testing constructor
    final int BAD_TEST_INIT = 0; // known bad size parameter value (0 or negative)
    final int BAD_TEST_SIZE = 0; // known bad size parameter value (0 or negative)

    // good values for testing generateSequence() and getIterator()
    final int TEST_INIT = 6; // good initial value parameter value
    final int TEST_SIZE = 6; // good size parameter value

    // tests if constructor catches bad init parameter
    try {
      // creates instance of GeometricSequenceGenerator with bad init parameter
      DigitProductSequenceGenerator tester =
          new DigitProductSequenceGenerator(BAD_TEST_INIT, TEST_SIZE);
      return false; // test fails if exception is nor thrown for bad (<=0) init value
    } catch (IllegalArgumentException A) {
      // nothing happens. Breaks before false is returned if exception is thrown, which it should be
    }

    // tests if constructor catches bad size parameter
    try {
      // creates instance of GeometricSequenceGenerator with bad init parameter
      DigitProductSequenceGenerator tester =
          new DigitProductSequenceGenerator(TEST_INIT, BAD_TEST_SIZE);
      return false; // test fails if exception is nor thrown for bad (<=0) size value
    } catch (IllegalArgumentException A) {
      // nothing happens. Breaks before false is returned if exception is thrown, which it should be
    }

    // good DigitProductSequenceGenerator for testing generateSequence() and getIterator()
    DigitProductSequenceGenerator tester = new DigitProductSequenceGenerator(TEST_INIT, TEST_SIZE);

    // array of reference values for DigitProductSequence
    int referenceValue[] = new int[TEST_SIZE];

    // calculates reference values
    referenceValue[0] = TEST_INIT;
    for (int index = 1; index < referenceValue.length; index++) {
      // converts the previous term to an array of Strings for easier removal of 0s
      String tempString = ((Integer) referenceValue[index - 1]).toString();
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
      referenceValue[index] = product + referenceValue[index - 1];
    }

    // gets Iterator
    Iterator<Integer> testIterator = tester.getIterator();

    // compares the values an iterator returns to the reference values
    for (int index = 0; index < TEST_SIZE; index++) {
      int tempHolder = testIterator.next();
      if (referenceValue[index] != tempHolder) {
        return false; // test fails if returned value does not match expected value
      }
    }

    // tests that the iterator works correctly by making sure hasNext() returns null after the
    // entire sequence should have been indexed through by the iterator
    if (testIterator.hasNext()) {
      return false; // test fails if the iterator has a next element when there should not be one
    }

    return true; // returns true if all test cases passed
  }

  /**
   * Main and driver method. Calls test function for each sequence generator type
   * 
   * @param args String that is keyboard input. Not used
   */
  public static void main(String[] args) {
    // calls tester methods
    int fails = 0; // tracks number of failures
    if (!geometricSequenceGeneratorTest()) {
      System.out
          .println("geometricSequenceGeneratorTest() [constructor, hasNext(), next()] failed");
      fails++;
    }
    if (!fibonacciSequenceGeneratorTest()) {
      System.out
          .println("fibonacciSequenceGeneratorTest() [constructor, hasNext(), next()] failed");
      fails++;
    }
    if (!digitProductSequenceGeneratorTest()) {
      System.out.println(
          "digitProductSequenceGeneratorTest() [constructor, getSequence(), getIterator()] failed");
      fails++;
    }
    if (fails == 0) {
      System.out.println("no tests failed!");
    }
  }

}
