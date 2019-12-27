//
// Title: GameApplication
// Files: GameList, GameNode, GameOperator, Random, Scanner
// Course: CS300 Fall 2018
//
// Author: Michael Goldstein
// Email: mdgoldstein2@wisc.edu
// Lecturer's Name: Alexi Brooks
//

/**
 * Class that runs a math game using a linked list (GameList) of GameNodes
 * 
 * @author Michael Goldstein
 * @version 1.0
 * @see GameNode, GameList
 */
import java.util.Random;
import java.util.Scanner;

public class GameApplication {
  /**
   * Main method that is driver for the game
   * 
   * @param args String that is user input by keyboard
   */
  public static void main(String[] args) {
    Random r = new Random();
    Scanner in = new Scanner(System.in);

    Boolean gameWon = false; // flag for game status
    final int GAME_GOAL = r.nextInt(90) + 10; // result player wants to get
    int movesTaken = 0; /// number of turns the player has taken
    GameList gameNumbers = new GameList(); // the GameList the numbers are stored in

    String userInput; // string that stores user's commands
    int numberToModify; // number field of node that will be searched for and modified

    // loads gameNumbers with 7 starting elements
    for (int index = 0; index < 7; index++) {
      gameNumbers.addNode(new GameNode(r));
    }

    // loop that runs game
    while (!gameWon) {
      // ui elements
      System.out.println("Goal: " + GAME_GOAL + " Moves Taken: " + movesTaken);
      System.out.println("Puzzle: " + gameNumbers);
      System.out.print("Number and Operation "+ GameOperator.ALL_OPERATORS + " to Apply:");
      userInput = in.next();
      System.out.print("\n");

      // checks for quit command
      String upperCaseUserInput = userInput.toUpperCase();
      if (upperCaseUserInput.equals("QUIT")) {
        break;
      }

      // checks for errors in input and executes command
      try {
        if (userInput.length() > 2) {
          throw new IllegalStateException();
        }
        numberToModify = Integer.parseInt(Character.toString(userInput.charAt(0)));
        gameNumbers.applyOperatorToNumber(numberToModify,
            GameOperator.getFromChar(userInput.charAt(1)));
        gameNumbers.addNode(new GameNode(r));
      } catch (IllegalStateException a) {
        movesTaken--;
        System.out.println(
            "WARNING: you have entered an invalid number and/or operation. Please try again");
      } catch (NullPointerException a) {
        movesTaken--;
        System.out.println(
            "WARNING: you have entered an invalid number and/or operation. Please try again");
      }

      // checks if player has won
      if (gameNumbers.contains(GAME_GOAL)) {
        gameWon = true;
      }
      movesTaken++;
    }

    // prints out winner sequence if won
    if (gameWon) {
      System.out.println("Congratulations, you won in " + movesTaken + " moves");
      System.out.print("Solution: " + gameNumbers);
    }
  }
}
