//
// Title: Tiger
// Files: Animal
// Course: CS300 Fall2018
//
// Author: Michael Goldstein
// Email: mdgoldstein2@wisc.edu
// Lecturer's Name: Alexi Brooks
//


/**
 * This class represents a Tiger in the JunglePark application and extends Animal
 * 
 * @author Mouna Kacem and Michael Goldstein
 * @version 1.0
 * @see Animal
 */
public class Tiger extends Animal {
  private static final int SCAN_RANGE = 100; // range dimension for scanning the neighborhood for
                                             // food
  private static final String IMAGE_FILE_NAME = "images/tiger.png";
  private static int nextID = 1; // class variable that represents the identifier of the next tiger
                                 // to be created
  // Tiger's identification fields
  private static final String TYPE = "TGR"; // A String that represents the tiger type
  private final int id; // Tiger's id: positive number that represents the order of the tiger
  private int deerEatenCount; // number of deer this instance has eaten


  /**
   * Creates a new Tiger object positioned at a random position of the display window
   * 
   * @param processing PApplet object that represents the display window
   */
  public Tiger(JunglePark processing) {
    // Set Tiger drawing parameters
    super(processing, IMAGE_FILE_NAME);

    // Set Tiger identification fields
    id = nextID;
    this.label = TYPE + id; // String that identifies the current tiger
    nextID++;
    deerEatenCount = 0;
  }

  /**
   * Returns the number of deer this instance of Tiger has eaten
   * 
   * @return the number of deer this instance of Tiger has eaten
   */
  public int getDeerEatenCount() {
    return deerEatenCount;
  }

  public void hop(Deer food) {
    // releases mouse and sets position to that of the deer to be eaten
    super.mouseReleased();
    super.setPositionX(food.getPositionX());
    super.setPositionY(food.getPositionY());

    // removes the eaten deer from the ArrayList of all graphical objects in the instance of
    // JunglePark this instance of Tiger exists in and incremend deerEatenCount
    super.processing.listGUI.remove(food);
    deerEatenCount++;
  }

  /**
   * Tiger's behavior in the Jungle Park Scans for food at the neighborhood of the current tiger. If
   * the Tiger founds any deer at its proximity, it hops on it, and eats it
   */
  @Override
  public void action() {
    // indexes through all graphical objects that exist in JunglePark that this instance of Tiger
    // exists in
    for (int index = 0; index < super.processing.listGUI.size(); index++) {
      // checks if object at index is a deer
      if (super.processing.listGUI.get(index).getClass() == Deer.class) {
        // checks if deer is within range
        if (super.isClose((Animal) super.processing.listGUI.get(index), SCAN_RANGE)) {
          this.hop((Deer) super.processing.listGUI.get(index));
        }
      }
    }
    //prints out number of deer eaten if that number is greater than 0
    if(deerEatenCount>0) {
      displayDeerEatenCount();
    }
  }
  
  /**
   * Displays the number of eaten deers if any on the top of the tiger image
   */
  private void displayDeerEatenCount() {
    this.processing.fill(0); // specify font color: black
    // display deerEatenCount on the top of the Tiger's image
    this.processing.text(deerEatenCount, this.getPositionX(), this.getPositionY() - this.image.height / 2 - 4);  
  }
}
