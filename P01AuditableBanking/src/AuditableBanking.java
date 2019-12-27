/**
 * A class that stores, audits, and processes bank transactions based on user input
 * 
 * @author Michael Goldstein
 * @version 1.0
 */

import java.util.Scanner;

public class AuditableBanking {
  /**
   * Adds a transaction group to an array of transaction groups. If the allTransactions array is
   * already full then this method will do nothing other than return allTransactionCount.
   * 
   * @param newTransactions      is the new transaction group being added (perfect size).
   * @param allTransactions      is the collection that newTransactions is being added to
   *                             (oversize).
   * @param allTransactionsCount is the number of transaction groups within allTransactions (before
   *                             newTransactions is added).
   * @return the number of transaction groups within allTransactions after newTransactions is added.
   */
  public static int submitTransactions(int[] newTransactions, int[][] allTransactions,
      int allTransactionsCount) {
    final int ARBITRARY_NONBINARY_VALUE = 2;

    if (allTransactions.length > allTransactionsCount) {
      // loads new transactions into allTransaction
      for (int index = 0; index < newTransactions.length; index++) {
        allTransactions[allTransactionsCount][index] = newTransactions[index];
      }
      /*
       * if the new transactions are binary encoded and shorter than the number of elements in one
       * row of allTransactions, the remaining "unloaded" elements are filled with a non-binary
       * value so calculateCurrentBalance() and calculateNumberofOverdrafts do not treat the
       * unloaded elements as 0's
       */
      if (newTransactions.length < allTransactions[allTransactionsCount].length
          && newTransactions[0] == 0) {
        for (int index =
            newTransactions.length; index < allTransactions[allTransactionsCount].length; index++) {
          allTransactions[allTransactionsCount][index] = ARBITRARY_NONBINARY_VALUE;
        }
      }
      allTransactionsCount++;
    }
    return allTransactionsCount;
  }

  /**
   * Processes a String into an array of integers, checks if the integers match one of three valid
   * encodings, and adds those integers to an array with all other transactions using
   * submitTransactions(int []. int [][], int)
   * 
   * @param command              is the encoded input
   * @param allTransactions      is the collection that the input will be added to if valid
   *                             (oversize)
   * @param allTransactionsCount is the number of transaction groups within allTransactions (before
   *                             the input is added).
   * @return the number of transaction groups within allTransactions after transactions in command
   *         are added.
   * @see submitTransactions(int []. int [][], int)
   */
  public static int processCommand(String command, int[][] allTransactions,
      int allTransactionsCount) {

    String trimmedCommand = command.trim();
    trimmedCommand = trimmedCommand.toUpperCase();

    // checks if first character in command is a special case or a transaction
    if (trimmedCommand.charAt(0) == 'B') {
      System.out.println(
          "Current Balance: " + calculateCurrentBalance(allTransactions, allTransactionsCount));
    } else if (trimmedCommand.charAt(0) == 'O') {
      System.out.println("Number of Overdrafts: "
          + calculateNumberOfOverdrafts(allTransactions, allTransactionsCount));
    } else {
      // converts transaction from string into integer array
      String[] newTransactionsString = trimmedCommand.split(" ");
      int[] newTransactions = new int[newTransactionsString.length];

      for (int index = 0; index < newTransactions.length; index++) {
        newTransactions[index] = Integer.parseInt(newTransactionsString[index]);
      }

      // loads the transaction if the first element indicates a valid encoding method
      if (newTransactions[0] >= 0 && newTransactions[0] <= 2) {
        allTransactionsCount =
            submitTransactions(newTransactions, allTransactions, allTransactionsCount);
      }
    }
    return allTransactionsCount;
  }

  /**
   * Executes a given set of transactions and outputs the current balance after those transactions
   * 
   * @param allTransactions      is the collection of transactions that need to be executed
   * @param allTransactionsCount is the number of transaction groups that need to be executed
   * @return current balance based on the transactions in allTransactions
   */
  public static int calculateCurrentBalance(int[][] allTransactions, int allTransactionsCount) {
    int currentBalance = 0;

    // values for each element of a quick withdraw transaction
    final int QUICK_WITHDRAW_VALUE_1 = 20;
    final int QUICK_WITHDRAW_VALUE_2 = 40;
    final int QUICK_WITHDRAW_VALUE_3 = 80;
    final int QUICK_WITHDRAW_VALUE_4 = 100;

    for (int index = 0; index < allTransactionsCount; index++) {
      // executes binary transactions
      if (allTransactions[index][0] == 0) {
        for (int index2 = 1; index2 < allTransactions[index].length; index2++) {
          if (allTransactions[index][index2] == 0) {
            currentBalance--;
          } else if (allTransactions[index][index2] == 1) {
            currentBalance++;
          }
        }
      }
      // executes integer transactions
      else if (allTransactions[index][0] == 1) {
        for (int index2 = 1; index2 < allTransactions[index].length; index2++) {
          currentBalance = currentBalance + allTransactions[index][index2];
        }
      }
      // executes quick withdraw transactions
      else {
        currentBalance = currentBalance - QUICK_WITHDRAW_VALUE_1 * allTransactions[index][1];
        currentBalance = currentBalance - QUICK_WITHDRAW_VALUE_2 * allTransactions[index][2];
        currentBalance = currentBalance - QUICK_WITHDRAW_VALUE_3 * allTransactions[index][3];
        currentBalance = currentBalance - QUICK_WITHDRAW_VALUE_4 * allTransactions[index][4];
      }
    }
    return currentBalance;
  }

  /**
   * Executes a given set of transactions and outputs the number of overdrafts after calculating the
   * current balance after each transaction
   * 
   * @param allTransactions      is the collection of transactions that need to be executed and
   *                             checked for overdrafts
   * @param allTransactionsCount is the number of transaction groups that need to be executed and
   *                             checked for overdrafts
   * @return current balance based on the transactions in allTransactions
   */
  public static int calculateNumberOfOverdrafts(int[][] allTransactions, int allTransactionsCount) {
    int currentBalance = 0;
    int numberOfOverdrafts = 0;

    // values for each element of a quick withdraw transaction
    final int QUICK_WITHDRAW_VALUE_1 = 20;
    final int QUICK_WITHDRAW_VALUE_2 = 40;
    final int QUICK_WITHDRAW_VALUE_3 = 80;
    final int QUICK_WITHDRAW_VALUE_4 = 100;

    for (int index = 0; index < allTransactionsCount; index++) {
      // executes binary transactions and counts number of overdrafts
      if (allTransactions[index][0] == 0) {
        for (int index2 = 1; index2 < allTransactions[index].length; index2++) {
          if (allTransactions[index][index2] == 0) {
            currentBalance--;
            if (currentBalance < 0) {
              numberOfOverdrafts++;
            }
          } else if (allTransactions[index][index2] == 1) {
            currentBalance++;
          }
        }
      }
      // executes integer transactions and counts number of overdrafts
      else if (allTransactions[index][0] == 1) {
        for (int index2 = 1; index2 < allTransactions[index].length; index2++) {
          currentBalance = currentBalance + allTransactions[index][index2];
          if (currentBalance < 0 && allTransactions[index][index2] < 0) {
            numberOfOverdrafts++;
          }
        }
      }
      // executes quick withdraw transactions and counts number of overdrafts
      else {
        currentBalance = currentBalance - QUICK_WITHDRAW_VALUE_1 * allTransactions[index][1];
        if (currentBalance < 0 && allTransactions[index][1] > 0) {
          numberOfOverdrafts++;
        }
        currentBalance = currentBalance - QUICK_WITHDRAW_VALUE_2 * allTransactions[index][2];
        if (currentBalance < 0 && allTransactions[index][2] > 0) {
          numberOfOverdrafts++;
        }
        currentBalance = currentBalance - QUICK_WITHDRAW_VALUE_3 * allTransactions[index][3];
        if (currentBalance < 0 && allTransactions[index][3] > 0) {
          numberOfOverdrafts++;
        }
        currentBalance = currentBalance - QUICK_WITHDRAW_VALUE_4 * allTransactions[index][4];
        if (currentBalance < 0 && allTransactions[index][4] > 0) {
          numberOfOverdrafts++;
        }
      }
    }
    return numberOfOverdrafts;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in).useDelimiter("\n");

    boolean stayRunning = true;
    int[][] allTransactions = new int[200][20];
    int allTransactionsCount = 0;
    String userInput;

    // creates and runs text-based UI
    System.out.println("========== Welcome to the Auditable Banking App ==========");

    while (stayRunning) {
      System.out.println("COMMAND MENU:");
      System.out.println("  Submit Transaction (enter sequence of integers separated by spaces)");
      System.out.println("  Show Current [B]alance)");
      System.out.println("  Show Number of [O]verdrafts");
      System.out.println("  [Q]uit Program");
      System.out.print("ENTER COMMAND: ");

      userInput = in.next();

      if (userInput.charAt(0) == 'Q' || userInput.charAt(0) == 'q') {
        stayRunning = false;
      } else {
        allTransactionsCount = processCommand(userInput, allTransactions, allTransactionsCount);
        System.out.println();
      }
    }
    System.out.println("============ Thank you for using this App!!!! ============");
    in.close();
  }
}
