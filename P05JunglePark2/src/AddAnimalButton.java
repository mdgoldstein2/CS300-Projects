//
// Title: AddAnimalButton
// Files: Button
// Course: CS300 Fall 2019
//
// Author: Michael Goldstein
// Email: mdgoldstein2@wisc.edu
// Lecturer's Name: Alexi Brooks
//

/**
 * This is a subclass of Button that creates a button which when clicked creates an animal in the
 * the same instance of Jungle Park it exists in
 * 
 * @author Michael Goldstein
 * @version 1.0
 * @see Button
 */
public class AddAnimalButton extends Button {
  private String type; // type of the animal to add

  /**
   * Constructor
   * 
   * @param type indicates which kind of animal the button will create when clicked and what will be
   *             written on it
   * @param x    the x coordinate of the button
   * @param y    the y coordinate of the button
   * @param park the JunglePark the button will be drawn in
   */
  public AddAnimalButton(String type, float x, float y, JunglePark park) {
    super(x, y, park);
    this.type = type.toLowerCase();
    this.label = "Add " + type;
  }
  
  /**
   * When the button is clicked, adds a new Tiger or Deer object to the instance of JunglePark
   * the button exists in 
   */
  @Override
  public void mousePressed() {
    if (super.isMouseOver()) {
      switch (type) {
        case "tiger":
          super.processing.listGUI.add(new Tiger(super.processing));
          break;
        case "deer":
          super.processing.listGUI.add(new Deer(super.processing));
          break;
      }
    }
  }

}
