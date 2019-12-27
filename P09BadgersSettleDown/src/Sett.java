// Title: Sett
// Files: Badger, NoSuchElementException, List
// Course: CS300 Fall 2018
//
// Author: Michael Goldstein
// Email: mdgoldstein2@wisc.edu
// Lecturer's Name: Alexi Brooks

import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Sett, where a group of Badgers live together. Each Sett is organized as a
 * BST of Badger nodes.
 * 
 * @author Michael Goldstein
 * @see Badger
 */
public class Sett {
  private Badger topBadger; // the root node of the BST (the badger at the top)

  /**
   * Constructor. Constructs an empty Sett. Initializes topBadger field to null.
   */
  public Sett() {
    topBadger = null;
  }

  /**
   * Retrieve the top Badger within this Sett (the one that was settled first).
   * 
   * @return The Badger living on the top of the current Sett.
   */
  public Badger getTopBadger() {
    return topBadger;
  }

  /**
   * Checks whether this Sett is empty.
   * 
   * @return true if this Sett is empty, false otherwise.
   */
  public boolean isEmpty() {
    if (topBadger == null) { // checks if the Sett is empty by checking if there is a top badger
      return true; // if no top badger, the Sett is empty so return true
    } else {
      return false; // if there is top badger, the set is not empty so return false
    }
  }

  /**
   * Creates a new Badger object with the specified size, and inserts them into this Sett (BST).
   * 
   * @param size The size of the new Badger that will be settled.
   * @throws IllegalArgumentException When a Badger with the specified size already exists within
   *                                  this Sett. Originally thrown in helper method.
   * @see Sett#settleHelper(Badger, Badger)
   */
  public void settleBadger(int size) throws IllegalArgumentException {
    if (topBadger == null) { // checks if topBadger (BST root) is null
      topBadger = new Badger(size); // adds badger as topBadger (the root node) if topBadger is null
    } else {
      settleHelper(topBadger, new Badger(size)); // calls helper method if top badger is not null
    }
  }

  /**
   * This recursive helper method is used to help settle a new Badger within this Sett.
   * 
   * @param current   The current Badger (previously settled within this Sett) that we are
   *                  considering settling the newBadger beneath (either to its left or right).
   * @param newBadger The new Badger that needs to be settled within this Sett.
   * @throws IllegalArgumentException When a Badger with the specified size already exists within
   *                                  this Sett.
   */
  private void settleHelper(Badger current, Badger newBadger) throws IllegalArgumentException {
    // checks if a badger with the same size exists in the Sett
    if (current.getSize() == newBadger.getSize()) {
      // throws exception if badger with the same size exists in the Sett
      throw new IllegalArgumentException("WARNING: failed to settle the badger with size "
          + newBadger.getSize() + ", as there is already a badger with the same size in this sett");
    }

    // checks whether the new badger's size is greater than or less than the current badger's size
    if (current.getSize() > newBadger.getSize()) {
      // if the new badger's size is less, checks whether the current badger's left node is null
      if (current.getLeftLowerNeighbor() == null) {
        // if the current badger's left node is null, adds the new badger as that left node
        current.setLeftLowerNeighbor(newBadger);
      } else {
        // if the current badger's left node is not null, calls settleHelper with the left node as
        // the new current badger
        settleHelper(current.getLeftLowerNeighbor(), newBadger);
      }
    } else {
      // if the new badger's size is larger, checks whether the current badger's right node is null
      if (current.getRightLowerNeighbor() == null) {
        // if the current badger's right node is null, adds the new badger as that right node
        current.setRightLowerNeighbor(newBadger);
      } else {
        // if the current badger's right node is not null, calls settleHelper with the right node as
        // the new current badger
        settleHelper(current.getRightLowerNeighbor(), newBadger);
      }
    }
  }

  /**
   * Finds a Badger of a specified size in this Sett.
   * 
   * @param size The size of the Badger object to search for and return.
   * @return The Badger found with the specified size.
   * @throws NoSuchElementException When there is no Badger in this Sett with the specified size.
   * @see Sett#findHelper(Badger, int)
   */
  public Badger findBadger(int size) throws NoSuchElementException {
    return findHelper(topBadger, size); // calls recursive helper class
  }

  /**
   * This recursive helper method is used to help find a Badger within this Sett.
   * 
   * @param current The current Badger that is the root of a (sub) tree that we are searching for a
   *                Badger with the specified size within.
   * @param size    The size of the Badger object to search for and return.
   * @return The Badger found with the specified size.
   * @throws NoSuchElementException When there is no Badger in this Sett with the specified size.
   */
  private Badger findHelper(Badger current, int size) throws NoSuchElementException {
    // checks whether the current badger's size is equal to, greater than or less than the searched
    // for size
    if (current.getSize() == size) {
      return current; // if the current badger is the size searched for, it is returned
    } else if (current.getSize() > size) {
      // if the searched for size is less than the current badger's, checks whether the current
      // badger's left node is null
      if (current.getLeftLowerNeighbor() == null) {
        // if the current badger's left node is null, a badger with the searched for size does not
        // exist so an exception is thrown
        throw new NoSuchElementException(
            "WARNING: failed to find a badger with size " + size + " in the sett");
      } else {
        // if the current badger's left node is not null, calls findHelper with the left node as
        // the new current badger
        return findHelper(current.getLeftLowerNeighbor(), size);
      }
    } else {
      // if the searched for size is larger than the current badger's, checks whether the current
      // badger's right node is null
      if (current.getRightLowerNeighbor() == null) {
        // if the current badger's right node is null, a badger with the searched for size does not
        // exist so an exception is thrown
        throw new NoSuchElementException(
            "WARNING: failed to find a badger with size " + size + " in the sett");
      } else {
        // if the current badger's right node is not null, calls findHelper with the right node as
        // the new current badger
        return findHelper(current.getRightLowerNeighbor(), size);
      }
    }
  }

  /**
   * Counts how many Badgers live in this Sett.
   * 
   * @return The number of Badgers living in this Sett.
   * @see Sett#countHelper(Badger)
   */
  public int countBadger() {
    return countHelper(topBadger); // calls recursive helper
  }

  /**
   * This recursive helper method is used to help count the number of Badgers in this Sett.
   * 
   * @param current The current Badger that is the root of a (sub) tree that we are counting the
   *                number of Badgers within.
   * @return the number of Badgers living in the Sett rooted at the current Badger.
   */
  private int countHelper(Badger current) {
    if (current == null) {
      return 0; // does not increase count if node is null
    } else {
      // increases badger count by one then recursively calls countHelper on left and right children
      // of current badger
      return 1 + countHelper(current.getLeftLowerNeighbor())
          + countHelper(current.getRightLowerNeighbor());
    }
  }

  /**
   * Gets all Badgers living in the Sett as a list in ascending order of their size: smallest one in
   * the front (at index zero), through the largest one at the end (at index size-1).
   * 
   * @return A list of all Badgers living in the Sett in ascending order by size.
   */
  public List<Badger> getAllBadgers() {
    List<Badger> allBadgers = new ArrayList<Badger>(); // creates a list to store badgers in
    getAllHelper(topBadger, allBadgers); // calls recursive helper
    return allBadgers; // returns the list which now has all badgers smallest to largest in it
  }

  /**
   * This recursive helper method is used to help collect the Badgers within this Sett into a List.
   * 
   * @param current    The list of all Badgers living in the Sett that is rooted at the current
   *                   Badger node. The contents of this list should be in ascending order by Badger
   *                   size.
   * @param allBadgers The current Badger that is the root of a (sub) tree that we are collecting
   *                   all contained Badgers within, into the allBadgers List.
   */
  private void getAllHelper(Badger current, List<Badger> allBadgers) {
    if (current != null) { // checks that the current node is not null
      // calls getAllHelper on left child node
      getAllHelper(current.getLeftLowerNeighbor(), allBadgers);
      // adds the current badger to the list
      allBadgers.add(current);
      // calls getAllHelper on right child node
      getAllHelper(current.getRightLowerNeighbor(), allBadgers);
    }
  }

  /**
   * Computes the height of the Sett, as the number of nodes from root to the deepest leaf Badger
   * node.
   * 
   * @return The depth of this Sett.
   */
  public int getHeight() {
    return getHeightHelper(topBadger); // calls recursive helper
  }

  /**
   * This recursive helper method is used to help compute the height of this Sett.
   * 
   * @param current The current Badger that is the root of a (sub) tree that we are calculating the
   *                height of.
   * @return The height of the (sub) tree that we are calculating.
   */
  private int getHeightHelper(Badger current) {
    if (current == null) { // checks if current node is null
      return 0; // if current node is null, height is not increased
    } else {
      // finds the height of a subtree with the left child node as the root node
      int leftHeight = getHeightHelper(current.getLeftLowerNeighbor());
      // finds the height of a subtree with the right child node as the root node
      int rightHeight = getHeightHelper(current.getRightLowerNeighbor());

      // checks which subtree is taller
      if (leftHeight > rightHeight) {
        // if left subtree taller, adds height of left subtree + 1 for the current node
        return 1 + leftHeight;
      } else {
        // if right subtree taller or equal, adds height of right subtree + 1 for the current node
        return 1 + rightHeight;
      }
    }
  }

  /**
   * Retrieves the largest Badger living in this Sett.
   * 
   * @return The largest Badger living in this Sett.
   */
  public Badger getLargestBadger() {
    Badger current = topBadger; // sets current badger reference to the top badger

    // loop runs while the current badger has a larger badger (A right child node)
    while (current.getRightLowerNeighbor() != null) {
      current = current.getRightLowerNeighbor();
    }
    return current; // returns the largest (bottom right) node (badger) of the BST (Sett)
  }

  /**
   * Empties this Sett, to no longer contain any Badgers.
   */
  public void clear() {
    topBadger = null; // Sets the top badger (root of BST) to null, effectively removing all badgers
  }

}
