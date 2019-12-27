// Title: P9Tests
// Files: Badger, Sett, NoSuchElementException, List
// Course: CS300 Fall 2018
//
// Author: Michael Goldstein
// Email: mdgoldstein2@wisc.edu
// Lecturer's Name: Alexi Brooks

import java.util.NoSuchElementException;
import java.util.List;

/**
 * Class that tests that the methods of Badger and Sett work correctly
 * 
 * @author Michael Goldstein
 * @see Badger
 * @see Sett
 */
public class P9Tests {
  /**
   * Tests the constructor and getters of Badger class
   * 
   * @return true if constructor and getters do not fail any of the test cases, false if they do
   * @see Badger#Badger(int)
   * @see Badger#getSize()
   * @see Badger#getLeftLowerNeighbor()
   * @see Badger#getRightLowerNeighbor()
   */
  public static boolean checkBadgerConstructor() {
    final int TEST_SIZE = 2; // test size for badger constructor
    Badger testBadger = new Badger(TEST_SIZE); // test Badger initialized with given test size

    // tests constructor and getters
    if (testBadger.getSize() != TEST_SIZE) {
      // test fails if constructor did not initialize size correctly to the test value or getter
      // failed to properly return it
      return false;
    }
    if (testBadger.getLeftLowerNeighbor() != null) {
      // test fails if constructor did not initialize leftLowerNeighbor to null or getter failed to
      // properly return it
      return false;
    }
    if (testBadger.getRightLowerNeighbor() != null) {
      // test fails if constructor did not initialize rightLowerNeighbor to null or getter failed to
      // properly return it
      return false;
    }
    return true; // returns true if none of the above test points failed
  }

  /**
   * Tests the setter methods of Badger class
   * 
   * @return true if setters do not fail any of the test points, else false
   * @see Badger#setLeftLowerNeighbor(Badger)
   * @see Badger#setRightLowerNeighbor(Badger)
   */
  public static boolean checkBadgerSetters() {
    final int TEST_SIZE = 2; // test size for badger constructor
    Badger testBadger = new Badger(TEST_SIZE); // test Badger initialized with given test size

    // test Badgers to be added as child nodes (lower neighbors)
    Badger leftTestBadger = new Badger(TEST_SIZE);
    Badger rightTestBadger = new Badger(TEST_SIZE);

    // setting testBadger's left and right lower neighbors
    testBadger.setLeftLowerNeighbor(leftTestBadger);
    testBadger.setRightLowerNeighbor(rightTestBadger);

    // testing that the setters work correctly
    if (testBadger.getLeftLowerNeighbor() != leftTestBadger) {
      // test fails if the testBadger's left lower neighbor does not match the test badger that was
      // added
      return false;
    }
    if (testBadger.getRightLowerNeighbor() != rightTestBadger) {
      // test fails if the testBadger's right lower neighbor does not match the test badger that was
      // added
      return false;
    }
    return true; // return true if none of the above test points failed
  }

  /**
   * Method that calls all test methods for the Badger class
   * 
   * @return true if all tests pass, false if not
   * @see P9Tests#checkBadgerConstructor()
   * @see P9Tests#checkBadgerSetters()
   */
  public static boolean runAllBadgerTests() {
    // runs all Badger tests, checks if they fail, prints out if they fail
    int fails = 0; // counts number of fails
    if (!checkBadgerConstructor()) {
      fails++;
      System.out.print("checkBadgerConstructor() [constructor, getters] failed!");
    }
    if (!checkBadgerSetters()) {
      fails++;
      System.out.print("checkBadgerSetters() [setters] failed!");
    }

    // checks whether any tests have failed, returns false if at least one failure happened, else
    // true
    if (fails > 0) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * Checks that constructor and getTopBadger of Sett class work correctly
   * 
   * @return true if methods pass all test cases, false if not
   * @see Sett#Sett()
   * @see Sett#getTopBadger()
   */
  public static boolean checkSettConstructor() {
    // creates a test Sett object
    Sett testSett = new Sett();

    // checks that topBadger is correctly set to null and correctly returned
    if (testSett.getTopBadger() != null) {
      return false; // test fails if topBadger is not set to null or returned correctly
    }
    return true; // test passes if topBadger is set to null and returned correctly
  }

  /**
   * Checks that Sett's settleBadger() method works correctly by adding 7 badgers and checking that
   * they are in the correct position based on their sizes
   * 
   * @return
   */
  public static boolean checkSettSettleBadger() {
    // creates a test Sett object
    Sett testSett = new Sett();

    // settles test Badgers in way that should create complete, perfect Sett
    // Sett should be set up like this:
    //    3
    //  1   5
    // 0 2 4 6
    testSett.settleBadger(3);
    testSett.settleBadger(1);
    testSett.settleBadger(5);
    testSett.settleBadger(0);
    testSett.settleBadger(2);
    testSett.settleBadger(4);
    testSett.settleBadger(6);

    // checks that badgers set in correct positions by comparing badgers' sizes to what they should
    // be based on the commented diagram.
    // if a badger at any point does not have the correct size, test fails
    // Also checks if NullPointerException is thrown since one of the checked nodes is null. If a 
    //NullPointerException is thrown, the Sett has improperly settled the Badgers.
    try {
      if (testSett.getTopBadger().getSize() != 3) {
        return false;
      }
      if (testSett.getTopBadger().getLeftLowerNeighbor().getSize() != 1) {
        return false;
      }
      if (testSett.getTopBadger().getRightLowerNeighbor().getSize() != 5) {
        return false;
      }
      if (testSett.getTopBadger().getLeftLowerNeighbor().getLeftLowerNeighbor().getSize() != 0) {
        return false;
      }
      if (testSett.getTopBadger().getLeftLowerNeighbor().getRightLowerNeighbor().getSize() != 2) {
        return false;
      }
      if (testSett.getTopBadger().getRightLowerNeighbor().getLeftLowerNeighbor().getSize() != 4) {
        return false;
      }
      if (testSett.getTopBadger().getRightLowerNeighbor().getRightLowerNeighbor().getSize() != 6) {
        return false;
      }
    } catch (NullPointerException a) {
      return false;
    }

    // adds a badger with the same size as a badger already added. If an IllegalArgumentException is
    // not thrown test fails
    try {
      testSett.settleBadger(4);
      return false;
    } catch (IllegalArgumentException a) {
      // nothing happens here
    } catch (Exception a) {
      return false; //test fails if an incorrect exception is thrown
    }
    // returns true if all badgers settled in correct location and exception is throw when a badger
    // with the same size as one already settled is settled
    return true;
  }

  /**
   * Tests that findBadger() works correctly by adding 7 badgers and making sure they are all found
   * correctly
   * 
   * @return true if all badgers are found correctly, false if they are not
   * @see Sett#findBadger(int)
   */
  public static boolean checkSettFindBadger() {
    // creates a test Sett object
    Sett testSett = new Sett();

    // settles test Badgers in way that should create complete, perfect Sett
    // Sett should be set up like this:
    //    3
    //  1   5
    // 0 2 4 6
    testSett.settleBadger(3);
    testSett.settleBadger(1);
    testSett.settleBadger(5);
    testSett.settleBadger(0);
    testSett.settleBadger(2);
    testSett.settleBadger(4);
    testSett.settleBadger(6);

    // checks that findBadger() works by using it to search for badgers known to exist in the Sett
    // if findBadger fails to return (throws a NoSuchElementException) or returns an incorrect
    // Badger the test fails. The test also fails if any other exception is thrown as that indicates
    // that the Sett class either throws an incorrect exception
    try {
      for (int index = 0; index < 7; index++) {
        if (testSett.findBadger(index).getSize() != index) {
          return false;
        }
      }
    } catch (Exception a) {
      return false;
    }
    return true; // returns true if all badgers found and returned correctly
  }

  /**
   * Checks countBadger(), getHeight() and getLargestBadger() by setting 7 badgers and making sure
   * the returned count, height and largest badger are the equal to the correct values for the given
   * 7 badgers
   * 
   * @return true if countBadger(), getHeight(), and getLargestBadger() return the correct values,
   *         false if they do not
   * @see Sett#countBadger()
   * @see Sett#getHeight()
   * @see Sett#getLargestBadger()
   */
  public static boolean checkSettStats() {
    // creates a test Sett object
    Sett testSett = new Sett();

    // settles test Badgers in way that should create complete, perfect Sett
    // Sett should be set up like this:
    //    3
    //  1   5
    // 0 2 4 6
    testSett.settleBadger(3);
    testSett.settleBadger(1);
    testSett.settleBadger(5);
    testSett.settleBadger(0);
    testSett.settleBadger(2);
    testSett.settleBadger(4);
    testSett.settleBadger(6);

    // tests countBadger(), getHeight(), and getLargestValue() by seeing if they return the correct
    // vales
    if (testSett.countBadger() != 7) {
      return false;
    }
    if (testSett.getHeight() != 3) {
      return false;
    }
    if (testSett.getLargestBadger().getSize() != 6) {
      return false;
    }
    return true; // returns true if all three methods return correct values
  }

  /**
   * Checks that getAllBadgers() returns a list in the correct order by setting 7 badgers and making
   * sure the list returned by getAllBadgers() has those badgers in the correct order
   * 
   * @return true if the badgers are in the correct order in the list returns by getAllBadgers(),
   *         false if not
   * @see Sett#getAllBadgers()
   */
  public static boolean checkSettGetAllBadgers() {
    // creates a test Sett object
    Sett testSett = new Sett();

    // settles test Badgers in way that should create complete, perfect Sett
    // Sett should be set up like this:
    //    3
    //  1   5
    // 0 2 4 6
    testSett.settleBadger(3);
    testSett.settleBadger(1);
    testSett.settleBadger(5);
    testSett.settleBadger(0);
    testSett.settleBadger(2);
    testSett.settleBadger(4);
    testSett.settleBadger(6);

    // Sett should be transformed into a list as 0 1 2 3 4 5 6
    // checks that list is in correct order, fails if any the size of any of the badgers at any
    // index of list does not match the size which should be at that index
    List<Badger> testList = testSett.getAllBadgers(); // List to hold return value of
                                                      // getAllBadgers()
    for (int index = 0; index < testList.size(); index++) {
      if (testList.get(index).getSize() != index) {
        return false;
      }
    }
    return true; // returns true if all badgers in return list in correct order
  }

  /**
   * Checks if isEmpty() and clear() methods work correctly by setting 7 badgers, checking if
   * isEmpty() returns false, calling clear(), and checking if isEmpty() now returns true
   * 
   * @return true if isEmpty() returns false after the badgers are settled and true after clear() is
   *         called, false if isEmpty() does not
   */
  public static Boolean checkSettIsEmptyClear() {
    // creates a test Sett object
    Sett testSett = new Sett();

    // settles test Badgers in way that should create complete, perfect Sett
    // Sett should be set up like this:
    //    3
    //  1   5
    // 0 2 4 6
    testSett.settleBadger(3);
    testSett.settleBadger(1);
    testSett.settleBadger(5);
    testSett.settleBadger(0);
    testSett.settleBadger(2);
    testSett.settleBadger(4);
    testSett.settleBadger(6);

    // tests the funcitonality of isEmpty(). isEmpty should be false as there are settled Badgers in
    // the test Sett
    if (testSett.isEmpty()) {
      return false;
    }

    // clears Sett, then checks if the Sett is empty again. This time it should be empty
    testSett.clear();
    if (!testSett.isEmpty()) {
      return false;
    }
    // returns true if isEmpty() returned false when 7 badgers were settled and true
    // after clear() was called
    return true;
  }

  public static boolean runAllSettTests() {
    int fails = 0;
    if (!checkSettConstructor()) {
      fails++;
      System.out.println("checkSettConstructor() [constructor, getTopBadger] failed");
    }
    if (!checkSettSettleBadger()) {
      fails++;
      System.out.println("checkSettSettleBadger() [constructor, getTopBadger] failed");
    }
    if (!checkSettFindBadger()) {
      fails++;
      System.out.println("checkSettFindBadger() [findBadger()] failed");
    }
    if (!checkSettStats()) {
      fails++;
      System.out
          .println("checkSettStats() [countBadger(), getHeight(), getLargestBadger()] failed");
    }
    if (!checkSettGetAllBadgers()) {
      fails++;
      System.out.println("checkSettGetAllBadgers() [getAllBadgers()] failed");
    }
    if (!checkSettIsEmptyClear()) {
      fails++;
      System.out.println("checkSettIsEmptyClear() [isEmpty(), clear()] failed");
    }

    // checks whether any tests have failed, returns false if at least one failure happened, else
    // true
    if (fails > 0) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * Driver method. Calls runAllBadgerTests and runAllSettTests to test that Badger and Sett work
   * correctly
   * 
   * @param args String input from user through keyboard. Not used in this case.
   */
  public static void main(String[] args) {
    if (runAllBadgerTests() && runAllSettTests()) {
      System.out.println("All tests passed!");
    }
  }
}
