//
// Title: GameList
// Files: GameOperator, GameNode, Random
// Course: CS300 Fall 2018
//
// Author: Michael Goldstein
// Email: mdgoldstein2@wisc.edu
// Lecturer's Name: Alexi Brooks
//

/**
 * Class that implements a singly linked list of GameNode objects
 * 
 * @author Michael Goldstein
 * @version 1.0
 * @see GameNode
 */
public class GameList {
  private GameNode list; // reference to the first GameNode in this list (the head node)

  /**
   * Constructor that creates a new instance of GameList and initialized list (the head node)field
   * to null
   */
  public GameList() {
    list = null;
  }

  /**
   * Adds a new GameNode to the end of GameList linked list
   * 
   * @param newNode the new GameNode to be added to the end of the list
   */
  public void addNode(GameNode newNode) {
    if (list == null) {
      // if list is empty, sets head node to the newNode
      list = newNode;
    } else {
      // if list is not empty, adds newNode to end by traversing list and adding newNode as the
      // previous final node's next node
      GameNode traverser = list;
      while (traverser.getNext() != null) {
        traverser = traverser.getNext();
      }
      traverser.setNext(newNode);
    }
  }

  /**
   * Checks whether or not the linked list contains a node with a certain value
   * 
   * @param number the value the linked list is searched for
   * @return true if a node with the value given by the parameter is found, false if it is not
   */
  public boolean contains(int number) {
    // traverses list and check's if node's value matches parameter
    GameNode traverser = list;
    while (traverser != null) {
      if (traverser.getNumber() == number) {
        return true;
      } else {
        traverser = traverser.getNext();
      }
    }
    return false;
  }

  /**
   * Returns a String of the values of all nodes in list formatted with " ->" in between and "END"
   * after the last node value when a particular instance of GameList is called in a print statement
   * 
   * @return a String of the values of all nodes in list formatted with " ->" in between and "END"
   */
  public String toString() {
    String allNodes = ""; // "storage" string while list is traversed

    // traverses list and adds elements and formatting to storage string
    GameNode traverser = list;
    while (traverser != null) {
      allNodes += traverser.getNumber() + " - > ";
      if (traverser.getNext() == null) {
        allNodes += "END";
      }
      traverser = traverser.getNext();
    }
    return allNodes; // returns storage string
  }

  /**
   * Searches linked list for a node with the value given by parameter, and executes an operation on
   * it using a given GameOperator object and the node's next value using GameNode's applyOperator()
   * function, which also removes the next node.
   * 
   * @param number   the value the node which will have the operation executed on it will have
   * @param operator the GameOperator object that will execute the operation
   * @see GameNode#applyOperator(GameOperator)
   */
  public void applyOperatorToNumber(int number, GameOperator operator) throws NullPointerException {
    // traverses list and searches for element to execute operation on
    GameNode traverser = list;
    while (traverser != null) {
      // checks if node is node with given value, executed operation if it is and breaks loop;
      if (traverser.getNumber() == number) {
        traverser.applyOperator(operator);
        break;
      } else {
        traverser = traverser.getNext();
      }
    }
  }
}
