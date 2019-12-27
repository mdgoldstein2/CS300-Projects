// Title: AsciiTest
// Files: DrawingStack, DrawingStackIterator
// Course: CS300 Fall 2018
//
// Author: Michael Goldstein
// Email: mdgoldstein2@wisc.edu
// Lecturer's Name: Alexi Brooks

/**
 * Tests the functions of DrawingStack and DrawingStackIterator
 * 
 * @author Michael Goldstein
 *
 */
public class AsciiTest {
  /**
   * Tests that the push and peak functions of DrawingStack work correctly by pushing an element
   * onto a stack and peeking, then comparing the output
   * 
   * @return true if the result of peek matches the object that was pushed on the stack, false if it
   *         does not
   * @see DrawingStack#push()
   * @see DrawingStack#peek()
   */
  public static boolean testStackPushPeek() {
    // test stack a node is pushed on and peeked from
    DrawingStack testStack = new DrawingStack();
    // test element pushed onto stack, parameters are arbitrary values that do not throw exceptions
    DrawingChange testChange = new DrawingChange(6, 6, 'a', 'a');

    // pushes peeks and compares
    testStack.push(testChange);
    if (testStack.peek() == testChange) {
      return true; // returns true if what peek returns matches the testNode that was pushed on
    } else {
      return false; // returns false if what peak returns does not match the testNode
    }
  }

  /**
   * Tests that the push and pop functions of DrawingStack work correctly by pushing an element onto
   * a stack and popping, then comparing the output. Also checks that isEmpty() works correctly by
   * checking if it returns true after popping an object since the stack should be empty at that
   * point
   * 
   * @return true if the result of peek matches the object that was pushed on the stack, false if it
   *         does not
   * @see DrawingStack#push(Object)
   * @see DrawingStack#pop()
   * @see DrawingStack#isEmpty()
   */
  public static boolean testStackPushPop() {
    // test stack a node is pushed on and peeked from
    DrawingStack testStack = new DrawingStack();
    // test element pushed onto stack, parameters are arbitrary values that do not throw exceptions
    DrawingChange testChange = new DrawingChange(6, 6, 'a', 'a');

    // pushes pop and compares, also makes sure isEmpty functions correctly as it should return true
    // since after popping the stack should be empty
    testStack.push(testChange);
    if (testStack.pop() == testChange && testStack.isEmpty()) {
      return true; // returns true if what peek returns matches the testNode that was pushed on
    } else {
      return false; // returns false if what peak returns does not match the testNode
    }
  }

  /**
   * Tests that size() and Iterator() methods work correctly by making an iterator count the number
   * of elements in a stack, comparing that to what size() returns and the actual number of elements
   * 
   * @return true if size() and the iterated loop count the same correct number of elements, false
   *         if they do not
   */
  public static boolean testStackSizeAndIterator() {
    // number of DrawingChange elements that will be added to the stack
    final int NUMBER_OF_ELEMENTS_ADDED = 10;
    // test stack a node is pushed on and peeked from
    DrawingStack testStack = new DrawingStack();

    // loading testStack with DrawingChange elements
    for (int index = 0; index < NUMBER_OF_ELEMENTS_ADDED; index++) {
      testStack.push(new DrawingChange(6, 6, 'a', 'a'));
    }

    // tests iterator by iterating over whole stack and counting each node to compare against
    // size()
    int size = 0; // size as counted by iterator
    DrawingStackIterator testIterator = (DrawingStackIterator) testStack.iterator(); // the iterator

    // iterates over while stack and counts each node
    while (testIterator.hasNext()) {
      size++;
      testIterator.next();
    }

    if (size == testStack.size() && size == NUMBER_OF_ELEMENTS_ADDED) {
      return true; // test passes if size() and iterator count the correct number of elements
    } else {
      return false; // test fails if either does not count the correct number
    }
  }

  /**
   * Driver method that calls other test methods to check functionality of DrawingStack
   * 
   * @return true if all tests passed, false if at least one fails
   * @see testStackPushPeek()
   * @see testStackPushPop()
   * @see testStackSizeAndIterator()
   */
  public static boolean runStackTestSuite() {
    int fails = 0; // counts number of failed tests

    // calls tester methods and prints out which methods (if any) failed
    if (!testStackPushPeek()) {
      System.out.println("testStackPushPeek() [push(), peek()] failed!");
      fails++;
    }
    if (!testStackPushPop()) {
      System.out.println("testStackPushPop() [push(), pop(), isEmpty()] failed!");
      fails++;
    }
    if (!testStackSizeAndIterator()) {
      System.out.println("testStackSizeAndIterator() [size(), iterator()] failed!");
      fails++;
    }
    if (fails == 0) {
      System.out.println("All tests passed!");
      return true;
    } else {
      return false;
    }
  }

  /**
   * Main method. Sole purpose is to call runStackTestSuite()
   * 
   * @param args String input from user through keyboard. Not used in this case.
   * @see runStackTestSuite()
   */
  public static void main(String[] args) {
    runStackTestSuite();
  }
}
