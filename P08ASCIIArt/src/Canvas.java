// Title: Canvas
// Files: DrawingStack
// Course: CS300 Fall 2018
//
// Author: Michael Goldstein
// Email: mdgoldstein2@wisc.edu
// Lecturer's Name: Alexi Brooks

/**
 * Creates a canvas that ascii art is displayed on
 * 
 * @author Michael Goldstein
 *
 */
public class Canvas {
  private final int width; // width of the canvas
  private final int height; // height of the canvas

  private char[][] drawingArray; // 2D character array to store the drawing

  private final DrawingStack undoStack; // store previous changes for undo
  private final DrawingStack redoStack; // store undone changes for redo

  /**
   * Constructor. Initializes all fields and fills drawingArray with ' ' character. Throws
   * IllegalArgumentException if width or height <=0
   * 
   * @param width  width of the canvas
   * @param height height of the canvas
   * @throws IllegalArgumentException if width or height <=0
   */
  public Canvas(int width, int height) {
    // checks if width and height<=0
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException();
    }

    // initializes fields
    this.width = width;
    this.height = height;
    drawingArray = new char[height][width];
    undoStack = new DrawingStack();
    redoStack = new DrawingStack();

    // fills drawingArray
    for (int indexY = 0; indexY < drawingArray.length; indexY++) {
      for (int indexX = 0; indexX < drawingArray[indexY].length; indexX++) {
        drawingArray[indexY][indexX] = ' ';
      }
    }
  }

  /**
   * Draws a new character at the given coordinates, creates and pushes a DrawingChange onto
   * redoStack with the given coordinates and the character that was drawn over. Checks that row and
   * height are within the canvas first and throws an IllegalArgumentExcpetion if one or both is not
   * within the canvas
   * 
   * @param row the y coordinate of the location where the new character will be written
   * @param col the x coordinate of the location where the old character will be written
   * @param c   the character to be written
   * @throws IllegalArgumentExcpetion if row or col are outside the canvas (<=0 or >=height or >=
   *                                  width)
   */
  public void draw(int row, int col, char c) {
    // checks if row and column are outside the canvas, throws IllegalArumentException if they are
    if (row >= height || col >= width) {
      throw new IllegalArgumentException();
    }
    if (row < 0 || col < 0) {
      throw new IllegalArgumentException();
    }

    // stores old character at location
    char oldCharacter = drawingArray[row][col];
    // adds new character
    drawingArray[row][col] = c;
    // pushes a DrawingChange to undoStack so the draw can be undone
    undoStack.push(new DrawingChange(col, row, oldCharacter, c));
    // blanks redoStack
    while (!redoStack.isEmpty()) {
      redoStack.pop();
    }
  }

  /**
   * Undoes the most recent drawing change, removes that change from undoStack and adds it to
   * redoStack
   * 
   * @return true if there is a drawing change that is undone, false if there is no change to undo
   */
  public boolean undo() {
    // checks if there is an operation that can be undone
    if (undoStack.isEmpty()) {
      return false;
    } else {
      // undoes the operation and adds that change to redoStack
      DrawingChange tempDrawingChange = undoStack.pop();
      draw(tempDrawingChange.y, tempDrawingChange.x, tempDrawingChange.prevChar);
      redoStack.push(tempDrawingChange);
      return true;
    }
  }

  /**
   * Redoes the most recent drawing change, removes that change from redoStack and adds it to
   * undoStack
   * 
   * @return true if there is a drawing change that is redone, false if there is no change to redo
   */
  public boolean redo() {
    // checks if there is an operation that can be redone
    if (redoStack.isEmpty()) {
      return false;
    } else {
      // redoes the operation and adds that change to undoStack
      DrawingChange tempDrawingChange = redoStack.pop();
      draw(tempDrawingChange.y, tempDrawingChange.x, tempDrawingChange.newChar);
      undoStack.push(tempDrawingChange);
      return true;
    }
  }

  /**
   * Returns a printable String form of drawingArray
   * 
   * @return a string which is a printable form of drawingArray
   */
  public String toString() {
    String printString = ""; // String that will contain the String form of drawingArray()
    // fills printString
    for (int indexY = 0; indexY < drawingArray.length; indexY++) {
      for (int indexX = 0; indexX < drawingArray[indexY].length; indexX++) {
        printString += drawingArray[indexY][indexX];
      }
      printString += System.lineSeparator();
    }
    return printString;
  }

  /**
   * Returns redoStack for the purpose of running an iterator on it
   * 
   * @return redoStack
   */
  public DrawingStack getUndoStack() {
    return undoStack;
  }
}
