// Title: Badger
// Files: None
// Course: CS300 Fall 2018
//
// Author: Michael Goldstein
// Email: mdgoldstein2@wisc.edu
// Lecturer's Name: Alexi Brooks

/**
 * This class represents a Badger which is designed to live in a Sett. Each Badger object represents
 * a single node within a BST (known as a Sett).
 * 
 * @author Michael Goldstein
 *
 */
public class Badger {
  private Badger leftLowerNeighbor; //the badger to the left and below this badger. Is smaller
  private Badger rightLowerNeighbor; //the badger to the right and below this badger. Is larger
  private int size; //the badger's size

  /**
   * Creates a new Badger with specified size. Initializes other fields to null.
   * 
   * @param size the size of the badger
   */
  public Badger(int size) {
    this.size = size; //sets size to given parameter
    leftLowerNeighbor = null; //initializes other fields to null
    rightLowerNeighbor = null;
  }

  /**
   * Retrieves neighboring badger that is smaller than this one.
   * 
   * @return the neighboring badger that is smaller than this one.
   */
  public Badger getLeftLowerNeighbor() {
    return leftLowerNeighbor;
  }

  /**
   * Retrieves neighboring badger that is larger than this one.
   * 
   * @return the neighboring badger that is larger than this one.
   */
  public Badger getRightLowerNeighbor() {
    return rightLowerNeighbor;
  }

  /**
   * Retrieves the size of this badger.
   * 
   * @return this badger's size
   */
  public int getSize() {
    return size;
  }

  /**
   * Changes this badger's lower left neighbor.
   * 
   * @param badger the new left lower neighbor of current badger
   */
  public void setLeftLowerNeighbor(Badger badger) {
    leftLowerNeighbor = badger;
  }

  /**
   * Changes this badger's lower right neighbor.
   * 
   * @param badger the new right lower neighbor of current badger
   */
  public void setRightLowerNeighbor(Badger badger) {
    rightLowerNeighbor = badger;
  }
}
