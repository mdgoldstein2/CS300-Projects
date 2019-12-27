//
// Title: Deer
// Files: PApplet.java, ParkGUI.java,
// Course: CS300 Fall2018
//
// Author: Michael Goldstein
// Email: mdgoldstein2@wisc.edu
// Lecturer's Name: Alexi Brooks
//

/**
 * This class represents a deer in JunglePark. Extends Animal
 * 
 * @author Michael Goldstein
 * @version 1.0
 * @see Animal
 *
 */
public class Deer extends Animal {
  private static final int SCAN_RANGE = 175; // scan range area to check for a threat in the
                                             // neighborhood
  private static final String IMAGE_FILE_NAME = "images/deer.png";
  private static int nextID = 1; // class variable that represents the identifier of the next deer
                                 // to be created

  private static final String TYPE = "DR"; // A String that represents the deer type
  private final int id; // Deer's id: positive number that represents the order of the deer


  /**
   * Constructor that creates a new Deer object positioned at a random position of the display
   * window using constructor from Animal superclass
   * 
   * @param processing the JunglePark the Deer should be drawn in
   * @see Animal#Animal(JunglePark, String)
   */
  public Deer(JunglePark processing) {
    super(processing, IMAGE_FILE_NAME);
    id = nextID;
    this.label = TYPE + id;
    nextID++;
  }

  // Checks if there is a threat (a Tiger for instance) at the neighborhood
  // scanRange
  /**
   * Checks if there is a threat (a Tiger object) within a certain range of this instance of Deer
   * using .isClose() method from superclass Animal
   * 
   * @param scanRange is an integer that represents the range of the area to be scanned around the
   *                  animal to be checked for threats (Tiger objects)
   * @return true if at least one tiger object is within the scanRange, false if no tiger object is
   *         within scan range
   * @see Animal#isClose(Animal, int)
   */
  public boolean scanForThreat(int scanRange) {
    //indexes through ArrayList of all graphical objects in the JunglePark this instance of Deer 
    //exists in
    for(int index=0; index<super.processing.listGUI.size(); index++) {
      //checks if object at index of listGUI is a tiger
      if(super.processing.listGUI.get(index).getClass()==Tiger.class) {
        //if the object at index is a tiger, checks if tiger is within scanRange of this instance
        //of Deer
        if(super.isClose((Animal) super.processing.listGUI.get(index), scanRange)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Defines the behavior of Deer instance in JunglePark. Tells Deer instance to check for threats
   * and respond if threat found
   * @see Deer#scanForThreat(int)
   */
  @Override
  public void action() {
    //checks if there are threats by calling scanForThreat
    if(scanForThreat(SCAN_RANGE)) {
      //Deer instance writes that there is threat if threat found
      this.processing.fill(0); // specify font color: black
      this.processing.text("THREAT!", this.getPositionX(), this.getPositionY() 
          - this.image.height / 2 - 4);
    }
  }
}
