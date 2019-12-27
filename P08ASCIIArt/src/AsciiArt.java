// Title: AsciiArt
// Files: Canvas, Scanner, DrawingStackIterator
// Course: CS300 Fall 2018
//
// Author: Michael Goldstein
// Email: mdgoldstein2@wisc.edu
// Lecturer's Name: Alexi Brooks

import java.util.Scanner;

/**
 * Driver class that allows ascii art to be created by the user
 * 
 * @author Michael Goldstein
 * @see Canvas
 * @see DrawingStackIterator
 */
public class AsciiArt {
  /**
   * Main driver method which calls other methods and executes its own instructions to run an ascii
   * art application
   * 
   * @param args String input by user through keyboard
   * @see printMenu()
   * @see processInput()
   * @see makeCanvas()
   * @see printHistory()
   */
  public static void main(String[] args) {
    Canvas myCanvas = null; // the canvas instance that will be used for ascii art
    Scanner in = new Scanner(System.in); // scanner that reads user input
    boolean stayRunning = true;

    // loop that runs application
    while (stayRunning) {
      printMenu();

      // the user's inital menu selection
      int userInput = processInt(in.next());

      // switch case that handles input
      switch (userInput) {
        case 1: // create new canvas case
          myCanvas = makeCanvas(in);
          break;
        case 2: // draw a character case
          // checks if myCanvas is initialized
          if (myCanvas == null) {
            System.out.println("Create a valid canvas first!");
          } else {
            // processes user input for row, column, and character and checks for exceptions
            System.out.print("Row:");
            int row = processInt(in.next());
            System.out.println("Column:");
            int column = processInt(in.next());
            System.out.println("Character");
            char character = in.next().charAt(0);
            try {
              myCanvas.draw(row, column, character);
            } catch (IllegalArgumentException a) {
              System.out.println("The row and column coordinates must integers "
                  + "within the dimensions of the canvas!");
            }
          }
          break;
        case 3: // Undo drawing case
          // checks if myCanvas is initialized
          if (myCanvas == null) {
            System.out.println("Create a valid canvas first!");
          } else if (!myCanvas.undo()) { // checks if an undo was possible
            System.out.println("Undo operation failed! No operation to undo!");
          }
          break;
        case 4: // Redo drawing case
          // checks that myCanvas is initialized
          if (myCanvas == null) {
            System.out.println("Create a valid canvas first!");
          } else if (!myCanvas.redo()) { // checks if a redo was possible
            System.out.println("Redo operation failed! No operation to Redo!");
          }
          break;
        case 5: // Draw canvas case
          // checks that myCanvas is initialized
          if (myCanvas == null) {
            System.out.println("Create a valid canvas first!");
          } else {
            System.out.println(myCanvas); // uses toString of myCanvas to print
          }
          break;
        case 6: // Show drawing history case
          // checks that myCanvas is initialized
          if (myCanvas == null) {
            System.out.println("Create a valid canvas first!");
          } else {
            printHistory(myCanvas);
          }
          break;
        case 7: // quit case
          // sets flag for if application should keep running to false
          stayRunning = false;
          System.out.println("Bye!");
          break;
        default:
          // prints out what user failed to do correctly in order to execute a command in the
          // application
          System.out.println("Menu commands must be integers from 1 to 7!");
          break;
      }
    }
  }

  /**
   * Method that prints out all menu options
   */
  public static void printMenu() {
    // prints menu commands
    System.out.println("======== MENU ========");
    System.out.println("[1] Create a new canvas");
    System.out.println("[2] Draw a character");
    System.out.println("[3] Undo drawing");
    System.out.println("[4] Redo drawing");
    System.out.println("[5] Show current canvas");
    System.out.println("[6] Show drawing history");
    System.out.println("[7] Exit");
    System.out.println("Command:");
  }

  /**
   * Method that sets a canvas's dimensions by creating and returning a Canvas object with
   * dimensions given by user. Also performs exception handling
   * 
   * @param in the Scanner that is used to get user input
   * @return a Canvas object with parameters entered by user or null if width and height parameters
   *         are not positive integers
   * @see processInt(input)
   */
  public static Canvas makeCanvas(Scanner in) {
    // sets new canvas's dimensions
    try {
      // processes user input for width and height, checks for exceptions
      System.out.print("Width:");
      int width = processInt(in.next());
      System.out.print("Height");
      int height = processInt(in.next());
      return new Canvas(width, height);
    } catch (IllegalArgumentException a) {
      System.out.println("Width and Height must be integers greater than 0!");
      return null;
    }
  }

  /**
   * Checks if the input parameter is an integer. Returns the integer value if it is, returns -1 if
   * it is not. Simplifies exception handling in main method and makeCanvas()
   * 
   * @param input the String which is checked if it can be converted to an integer
   * @return the value of Integer.parseInt(input) if input can be parsed to an integer, -1 if it
   *         cannot.
   */
  public static int processInt(String input) {
    // checks if string is parseable for integer
    try {
      int userInput = Integer.parseInt(input);
      return userInput;
    } catch (NumberFormatException a) {
      return -1;
    }
  }

  /**
   * Prints contents of a canvas's undoStack using an iterator over it to display history
   * 
   * @param myCanvas
   */
  public static void printHistory(Canvas myCanvas) {
    // creates iterator
    DrawingStackIterator myIterator = (DrawingStackIterator) myCanvas.getUndoStack().iterator();

    // iterates through myCanvas's undoStack
    while (myIterator.hasNext()) {
      DrawingChange temp = myIterator.next();
      System.out.println("draw " + temp.newChar + " on (" + temp.x + "," + temp.y + ")");
    }
  }
}
