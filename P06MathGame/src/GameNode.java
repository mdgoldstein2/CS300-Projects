//
// Title: GameNode
// Files: GameOperator, Random
// Course: CS300 Fall 2018
//
// Author: Michael Goldstein
// Email: mdgoldstein2@wisc.edu
// Lecturer's Name: Alexi Brooks
//

/**
 * Class for a node in a linked list for a math puzzle game
 * 
 * @author Michael Goldstein
 * @version 1.0
 */
import java.util.Random;

public class GameNode {
  private int number; // the number held within this node
  private GameNode next; // the next GameNode in the list, or null for the last node

  /**
   * Constructor. Creates new GameNode instance, initialized the instance's number field to a random
   * integer value between 1 and 9, and sets the next node field to null
   * 
   * @param rng the Random object that will be used to generate the random number between 1 and 9
   */
  public GameNode(Random rng) {
    number = rng.nextInt(9) + 1;
    next = null;
  }

  /**
   * Accessor that returns the number field of the node
   * 
   * @return the number field of the node
   */
  public int getNumber() {
    return number;
  }

  /**
   * Accessor method that returns a particular instance's next node
   * 
   * @return the next GameNode assigned to a particular instance of this GameNode
   */
  public GameNode getNext() {
    return next;
  }

  /**
   * Mutator that sets the next node of a particular instance's next node
   * 
   * @param next the node the next node field of the particular instance will be set to
   */
  public void setNext(GameNode next) {
    this.next = next;
  }

  /**
   * Mutator that changes the number field of a particular instance by running an operation on the
   * instance's and the instance's next node's number field and replacing the instance's number
   * field with the result of that operation. It then removes the next node from the linked list by
   * setting this instance's next field to the original next nodes' next node
   * 
   * @param operator the GameOperator object that will run the operation
   * @see GameOperator#apply(int, int)
   * @throws NullPointerException if the instance's next field is null
   */
  public void applyOperator(GameOperator operator) throws NullPointerException {
    // throws NullPointerException if next node field is null
    if (this.next == null) {
      throw new NullPointerException();
    }
    // executes operation and replaces number field with result
    this.number = operator.apply(this.number, next.getNumber());
    // changes next node field
    this.next = this.next.getNext();
  }
}
