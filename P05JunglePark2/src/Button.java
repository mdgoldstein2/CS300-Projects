//
// Title: Button
// Files: ParkGUI
// Course: CS300 Fall 2019
//
// Author: Michael Goldstein
// Email: mdgoldstein2@wisc.edu
// Lecturer's Name: Alexi Brooks
//

/**
 * This class is a superclass for other buttons that will be used in JunglePark. Extends ParkGUI
 * 
 * @author Mouna Kacem
 * @version 1.0
 * @see ParkGUI
 */

public class Button implements ParkGUI {
  private static final int WIDTH = 85; // Width of the Button
  private static final int HEIGHT = 32; // Height of the Button
  protected JunglePark processing; // PApplet object where the button will be displayed
  private float[] position; // array storing x and y positions of the Button with respect to
                            // the display window
  protected String label; // text/label that represents the button

  /**
   * Constructor for Button class. Creates a new instance of Button class in a given JunglePark
   * and at a given set of coordinates
   * 
   * @param x the x coordinate the button will be created at
   * @param y the y coordinate the button will be created at
   * @param processing the JunglePark the button will be drawn in
   * @see Jungle Park
   */
  public Button(float x, float y, JunglePark processing) {
    this.processing = processing;
    this.position = new float[2];
    this.position[0] = x;
    this.position[1] = y;
    this.label = "Button";
  }

  /**
   * Draws the button in the given JunglePark, changing color if mouse is over the button
   */
  @Override
  public void draw() {
    this.processing.stroke(0);// set line value to black
    if (isMouseOver())
      processing.fill(100); // set the fill color to dark gray if the mouse is over the button
    else
      processing.fill(200); // set the fill color to light gray otherwise

    // draw the button (rectangle with a centered text)
    processing.rect(position[0] - WIDTH / 2.0f, position[1] - HEIGHT / 2.0f,
        position[0] + WIDTH / 2.0f, position[1] + HEIGHT / 2.0f);
    processing.fill(0); // set the fill color to black
    processing.text(label, position[0], position[1]); // display the text of the current button
  }

  /**
   * Prints out that the mouse is over a button using isMouseOver()
   * @see Button#isMouseOver()
   */
  @Override
  public void mousePressed() {
    if (isMouseOver())
      System.out.println("A button was pressed.");
  }

  /**
   * Does not execute anything, but is created so the ParkGUI interface can be implemented
   */
  @Override
  public void mouseReleased() {}

  /**
   * Checks whether or not the user's mouse is over the button
   */
  @Override
  public boolean isMouseOver() {
    //if statement checks if mouse is within the coordinates enclosed by the four corners of the 
    //button
    if (this.processing.mouseX > this.position[0] - WIDTH / 2
        && this.processing.mouseX < this.position[0] + WIDTH / 2
        && this.processing.mouseY > this.position[1] - HEIGHT / 2
        && this.processing.mouseY < this.position[1] + HEIGHT / 2)
      return true;
    return false;
  }
}
