// Title: DrawingChange
// Files: None
// Course: CS300 Fall 2018
//
// Author: Michael Goldstein
// Email: mdgoldstein2@wisc.edu
// Lecturer's Name: Alexi Brooks

/**
 * A class which details how a change will be executed on a Canvas
 * 
 * @author Michael Goldstein
 *
 */
public class DrawingChange {
  public final int x; // x coordinate for a change
  public final int y; // y coordinate for a change
  public final char prevChar; // previous character in the (x,y)
  public final char newChar; // new character in the (x,y)

  /**
   * Constructor that initializes all instance fields
   * 
   * @param x        x coordinate for change
   * @param y        y coordinate for change
   * @param prevChar previous character in the (x, y)
   * @param newChar  new character in the (X, y)
   */
  public DrawingChange(int x, int y, char prevChar, char newChar) {
    this.x = x;
    this.y = y;
    this.prevChar = prevChar;
    this.newChar = newChar;
  }
}
