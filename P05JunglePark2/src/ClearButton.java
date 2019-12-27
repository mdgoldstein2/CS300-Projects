//
// Title: ClearButton
// Files: Button
// Course: CS300 Fall 2019
//
// Author: Michael Goldstein
// Email: mdgoldstein2@wisc.edu
// Lecturer's Name: Alexi Brooks
//

/**
 * This is a subclass of Button that creates a button which when clicked removes all animal objects 
 * from the JUnglePark the button is created in
 * @author Michael Goldstein
 * @version 1.0
 * @see Button
 */
public class ClearButton extends Button {
  /**
   * 
   * @param x the x coordinate the button should be created at
   * @param y the y coordinate the button should be created at
   * @param park the JunglePArk the button is drawn in and clears
   */
  public ClearButton(float x, float y, JunglePark park) {
    super(x, y, park);
    this.label = "Clear Park ";
  }
  
  /**
   * Clears the JunglePark the button was created in of all animal objects
   */
  @Override
  public void mousePressed() {
    super.processing.clear();
  }
}
