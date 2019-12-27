/**
 * A class that
 * 
 * @author Michael Goldstein
 *
 */

import java.util.Random;

public class JunglePark {
  private static PApplet processing; // PApplet object that represents the graphic
  // interface of the JunglePark application
  private static PImage backgroundImage; // PImage object that represents the
  // background image
  private static Tiger[] tigers; // array storing the current tigers present
  // in the Jungle Park
  private static Random randGen; // Generator of random numbers

  public static void main(String[] args) {
    Utility.startApplication();
  }

  /**
   * Defines the initial environment properties of the application
   * 
   * @param processingObj represents a reference to the graphical interface of the application
   */
  public static void setup(PApplet processingObj) {
    processing = processingObj; // initialize the processing field to the one passed into
    // the input argument parameter

    // Sets the color used for the background of the Processing window
    processing.background(245, 255, 250); // Mint cream color

    // initializes and loads the image of the background
    backgroundImage = processing.loadImage("images/background.png");

    // Draws the background image at the center of the screen
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);
    // width [resp. height]: System variable of the processing library that stores the width
    // [resp. height] of the display window.

    randGen = new Random();
    
    //creates array of Tiger objects and fills index 0 of that array
    //with a randomly placed Tiger object
    tigers = new Tiger[8];
    tigers[0] = new Tiger(processing, (float) randGen.nextInt(processing.width),
        (float) randGen.nextInt(processing.height));
  }
  
  /**
   * Is a callback method run by utility to draw and move tigers across the window
   */
  public static void update() {
    // Sets the color used for the background of the Processing window
    processing.background(245, 255, 250); // Mint cream color

    // Draws the background image at the center of the screen
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);
    // width [resp. height]: System variable of the processing library that stores the width
    // [resp. height] of the display window.
    
    //loops through array of tigers
    for(int index=0; index<tigers.length; index++) {
      //draws tigers in array
      if(tigers[index]!=null) {
        tigers[index].draw();
      }
    }
  }
  
  /**
   * Checks if the user's mouse is over a particular tiger object's image
   * 
   * @param tiger Tiger object which is being checked if the user's mouse is over it
   * @return true if the user's mouse is over tiger, false if it is not
   */
  public static boolean isMouseOver(Tiger tiger) {
    //checks if x and y values of the mouse are within the coordinates of the four corners of the
    //tiger image
    if((processing.mouseX>=(tiger.getPositionX()-tiger.getImage().width/2.0)) &&
        (processing.mouseX<=(tiger.getPositionX()+tiger.getImage().width/2.0)) &&
            (processing.mouseY>=(tiger.getPositionY()-tiger.getImage().height/2.0)) &&
                (processing.mouseY<=(tiger.getPositionY()+tiger.getImage().height/2.0))) {
      return true;        
    } else {
      return false;
    }
  }
  
  /**
   * Checks if a Tiger object in array has a mouse over it and if it does sets it to dragging
   * using isDragging() from the Tiger class and isMouseOver() from JunglePark
   * @see JunglePark#isMouseOver(Tiger)
   * @see Tiger#isDragging()
   */
  public static void mouseDown() {
    for(int index=0; index<tigers.length; index++) {
      if(tigers[index]!=null) {
        if(isMouseOver(tigers[index])) {
          tigers[index].setDragging(true);
          break;
        }
      }
    }
  }
  
  /**
   *Sets all Tiger objects in array to not dragging using the isDragging() method from Tiger class
   *@see Tiger#isDragging()
   */
  public static void mouseUp() {
    for(int index=0; index<tigers.length; index++) {
      if(tigers[index]!=null) {
        tigers[index].setDragging(false);
      }
    }
  }
  
  /**
   *Removes a tiger if R is pressed and the user's mouse is over it using the isMouseOver() method
   *Adds a tiger at a random point if T is pressed
   *@see JunglePark#isMouseOver(Tiger)
   */
  public static void keyPressed() {
    //removes a tiger if R key is pressed
    if(processing.key=='r' || processing.key=='R') {
      for(int index=0; index<tigers.length; index++) {
        if(tigers[index]!=null) {
          if(isMouseOver(tigers[index])) { 
            tigers[index]=null;
            break;
          }
        }
      }
    } else if(processing.key=='t' || processing.key=='T') { //adds a tiger if T is pressed
      for(int index=0; index<tigers.length; index++) {
        if(tigers[index]==null) {
          //draws a tiger at a random point
          randGen = new Random();
          tigers[index] = new Tiger(processing, (float) randGen.nextInt(processing.width),
              (float) randGen.nextInt(processing.height));
          break;
        }
      }
    }
  }
}// end of class JunglePark
