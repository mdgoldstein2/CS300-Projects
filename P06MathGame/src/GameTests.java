//
// Title: GameTests
// Files: GameList, GameNode, Random
// Course: CS300 Fall 2018
//
// Author: Michael Goldstein
// Email: mdgoldstein2@wisc.edu
// Lecturer's Name: Alexi Brooks
//

/**
 * Class that checks that GameNode and GameList's methods work correctly
 * 
 * @author Michael Goldstein
 * @version 1.0
 * @see GameNode, GameList
 */
import java.util.Random;

public class GameTests {
  /**
   * Tests whether the GameNode constructor, getters and setter work correctly
   * 
   * @return true if the constructor, getters, and setter do not fail any test case (work correctly)
   *         false if they do fail at least one case
   * @see GameNode#getNext()
   * @see GameNode#getNumber()
   * @see GameNode#setNext(GameNode)
   */
  public static boolean gameNodeTest1() {
    Random r = new Random();
    GameNode testNode = new GameNode(r); // test node operations are done on

    // tests that fields are initialized correctly by constructor
    if (testNode.getNext() != null) {
      return false; // test fails if next node field is not initialized to null
    } else if (testNode.getNumber() < 1 || testNode.getNumber() > 9) {
      return false; // test fails if number field is outside 1 to 9 range
    } // not a complete test as number field is randomly generated
      // also tests getters for both fields

    // tests setter for next node field
    GameNode nextNode = new GameNode(r); // test node that next node field of testNode will be set
                                         // to
    testNode.setNext(nextNode); // sets next node to nextNode
    if (testNode.getNext() != nextNode) {
      return false; // test fails if setNext() does not properly set node or getNext() does not
    } // properly return it
    return true; // returns true if none of the above test situations failed;
  }

  /**
   * Tests if GameNode's applyOperator method works correctly by comparing its result with an
   * expected value
   * 
   * @return true if GameNode's applyOperator change's an instance of GameNode's number field to a
   *         value that matches the expected outcome, false if it does not or fails to remove the
   *         node after it
   */
  public static boolean gameNodeTest2() {
    Random r = new Random();
    GameNode testNode = new GameNode(r); // test node all operations are done on

    // array of nodes which will be used for operations, have one for each possible operation
    GameNode[] opsNodes = new GameNode[GameOperator.ALL_OPERATORS.size()];
    for (int index = 0; index < opsNodes.length; index++) {
      opsNodes[index] = new GameNode(r);
    }

    // array of expected values, one for each operation
    // each expected value is calculated by running a game operator on the previous value in the
    // array and the current opsNode (or testNumber's value when index is 0) as that is
    // applyOperator
    // will work when testNode and all opsNodes are linked together
    final int[] EXPECTED_VALUES = new int[GameOperator.ALL_OPERATORS.size()];
    for (int index = 0; index < EXPECTED_VALUES.length; index++) {
      if (index == 0) {
        EXPECTED_VALUES[index] = GameOperator.ALL_OPERATORS.get(index).apply(testNode.getNumber(),
            opsNodes[index].getNumber());
      } else {
        EXPECTED_VALUES[index] = GameOperator.ALL_OPERATORS.get(index)
            .apply(EXPECTED_VALUES[index - 1], opsNodes[index].getNumber());
      }
    }

    // links all nodes together
    testNode.setNext(opsNodes[0]);
    for (int index = 0; index < opsNodes.length - 1; index++) {
      opsNodes[index].setNext(opsNodes[index + 1]);
    }

    // performs operation, checks that result is correct, and that testNode's next field is correct
    for (int index = 0; index < opsNodes.length; index++) {
      //runs operation
      testNode.applyOperator(GameOperator.ALL_OPERATORS.get(index)); 
      //checks result
      if (testNode.getNumber() != EXPECTED_VALUES[index]) {
        return false; // fails if applyOperator does not change testNode's number field to the
        // expected value
      }
      //checks that testNode's next field is set correctly by applyOperator
      if(index<opsNodes.length-1) {
        if(testNode.getNext()!=opsNodes[index+1]) {
          return false; //fails if next field is not set to the node after the one removed
        }
      } else {
        if(testNode.getNext()!=null) {
          return false; //fails if next field is not set to null after all opsNodes removed
        }
      }
    }
    return true; // returns true if none of the test cases failed
  }

  /**
   * Tests GameLists constructor, addNode(), toString() and contains() methods
   * 
   * @return true if addNode successfully adds nods to the linked list toString outputs correct
   *         String, and contains() returns true when a node with a given number is found (otherwise
   *         false), and false when any of the above methods does not fulfill the above requirements
   * @see GameList#addNode(GameNode)
   * @see GameList#toString()
   * @see GameList#contains(int)
   */
  public static boolean gameListTest1() {
    GameList testList = new GameList();
    Random r = new Random();

    // test nodes to be added to list
    GameNode testNode1 = new GameNode(r);
    GameNode testNode2 = new GameNode(r);

    // node value guaranteed not to be in list
    final int NOT_CONTAINED_VALUE = 15;

    // tests adding nodes
    testList.addNode(testNode1);
    testList.addNode(testNode2);
    if (testNode1.getNext() != testNode2) {
      return false; // fails test if testNode2 is not testNode1's next node
    }

    // tests toString
    if (!testList.toString()
        .equals(testNode1.getNumber() + " - > " + testNode2.getNumber() + " - > END")) {
      System.out.println("b");
      return false; // test fails if toString is not correctly formatted
    }

    // tests contains(
    if (!testList.contains(testNode2.getNumber())) {
      return false; // if statement will not be true if contains() functions correctly as testNode2
    } // is in list

    if (testList.contains(NOT_CONTAINED_VALUE)) {
      return false; // if statement will not be true if contains() functions correctly as no node
    } // with that value exists in list
    return true; // returns true if none of above test cases failed
  }

  /**
   * Tests that GameList's applyOperator() method works correctly by comparing its result to an
   * expected value for each operation
   * 
   * @return True if the result of applyOperator() matches the expected value for all operations,
   *         false if it does not
   */
  public static boolean gameListTest2() {
    GameList testList = new GameList();
    Random r = new Random();

    GameNode testNode = new GameNode(r); // test node all operations are done on

    // array of nodes which will be used for operations, have one for each possible operation
    GameNode[] opsNodes = new GameNode[GameOperator.ALL_OPERATORS.size()];
    for (int index = 0; index < opsNodes.length; index++) {
      opsNodes[index] = new GameNode(r);
    }

    // array of expected values, one for each operation
    // each expected value is calculated by running a game operator on the previous value in the
    // array and the current opsNode (or testNumber's value when index is 0) as that is
    // applyOperator
    // will work when testNode and all opsNodes are linked together
    final int[] EXPECTED_VALUES = new int[GameOperator.ALL_OPERATORS.size()];
    for (int index = 0; index < EXPECTED_VALUES.length; index++) {
      if (index == 0) {
        EXPECTED_VALUES[index] = GameOperator.ALL_OPERATORS.get(index).apply(testNode.getNumber(),
            opsNodes[index].getNumber());
      } else {
        EXPECTED_VALUES[index] = GameOperator.ALL_OPERATORS.get(index)
            .apply(EXPECTED_VALUES[index - 1], opsNodes[index].getNumber());
      }
    }

    // links all nodes together
    testList.addNode(testNode);
    for (int index = 0; index < opsNodes.length; index++) {
      testList.addNode(opsNodes[index]);
    }

    // performs operation, checks that result is correct, and that testNode's next field is correct
    for (int index = 0; index < opsNodes.length; index++) {
      //runs operation
      testList.applyOperatorToNumber(testNode.getNumber(), GameOperator.ALL_OPERATORS.get(index)); 
      //checks result
      if (testNode.getNumber() != EXPECTED_VALUES[index]) {
        return false; // fails if applyOperator does not change testNode's number field to the
        // expected value
      }
    }
    return true; //returns true if no test cases fail
  }

  /**
   * Main driver method that calls tester methods and prints out results
   * 
   * @param args String input from user through keyboard, not used in program
   */
  public static void main(String[] args) {
    // calls tester methods
    int fails = 0;
    if (!gameNodeTest1()) {
      System.out.println("gameNodeTest1() [constructor, getters, setter] failed");
      fails++;
    }
    if (!gameNodeTest2()) {
      System.out.println("gameNodeTest2() [applyOperator()] failed");
      fails++;
    }
    if (!gameListTest1()) {
      System.out.println("gameListTest1() [constructor, addNode(), toString(), contains()] failed");
      fails++;
    }
    if (!gameListTest2()) {
      System.out.println("gameListTest2() [applyOperatorToNumber] failed");
      fails++;
    }
    if (fails == 0) {
      System.out.println("no tests failed!");
    }
  }
}
