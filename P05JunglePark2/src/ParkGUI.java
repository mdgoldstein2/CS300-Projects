//
// Title: ParkGUI
// Files: none (is an interface)
// Course: CS300 Fall2018
//
// Author: Michael Goldstein
// Email: mdgoldstein2@wisc.edu
// Lecturer's Name: Alexi Brooks
//

/**
 * An interface which makes all classes who implement it have a universal set of methods for being
 * drawn and operating in the JunglePark window
 * 
 * @author Mouna Kacem
 * @version 1.0
 */

public interface ParkGUI {
  /**
   * Method that draws a ParkGUI object (either an animal or a button) to the display window
   */
  public void draw();

  /**
   * called each time the mouse is pressed
   */
  public void mousePressed();

  /**
   * called each time the mouse is pressed
   */
  public void mouseReleased();

  /**
   * checks whether the mouse is over a ParkGUI object
   * 
   * @return true if the mouse is over the object, false if it is not
   */
  public boolean isMouseOver();
}
