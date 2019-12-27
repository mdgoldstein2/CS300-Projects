/**
 * A class that tests various methods in AuditableBanking.java
 * 
 * @author Michael Goldstein
 * @version 1.0
 */

import java.util.Arrays;

public class AuditableBankingTests {
  /**
   * Tests if the processCommand(String, int[][], int) method from AuditableBanking.java works
   * correctly by simulating an input command and checking if 2D array allTransactions contains the
   * correct values from that command
   * 
   * @return true if processCommand() functions properly, false if it does not
   * @see AuditableBanking#processCommand(String, int[][], int)
   */
  public static boolean testProcessCommand() {
    int[][] testArray = new int[10][10];
    final String TEST_COMMAND = "1 0 0 1 0 1";
    int testArrayLength = 2;

    // loads testArray with values that will not match that of the test command
    for (int index = 0; index < testArrayLength; index++) {
      for (int index2 = 0; index2 < testArray[index].length; index2++) {
        testArray[index][index2] = index * index2;
      }
    }

    testArrayLength = AuditableBanking.processCommand(TEST_COMMAND, testArray, testArrayLength);

    // checks if the test commands has been correctly added to testArray
    if (testArrayLength == 3 && testArray[2][1] == 0) {
      return true;
    } else {
      return false;
    }

  }


  /**
   * Tests calculateCurrentBalance(int[][], int) method from AuditableBanking by submitting a test
   * array with transactions and checking if the current balance matches the expected current
   * balance
   * 
   * @return true if testCalculateCurrentBalance() returns the correct balance, false if it does not
   * @see AuditableBanking#calculateCurrentBalance(int[][], int)
   */
  public static boolean testCalculateCurrentBalance() {
    int[][] testArray = new int[10][10];

    final String TEST_COMMAND = "1 6 -7 10 100";
    final String TEST_COMMAND2 = "0 1 0 1 0";
    final String TEST_COMMAND3 = "2 3 2 1 1";

    int testArrayLength = 0;
    final int EXPECTED_CURENT_BALANCE = -211;

    // adds all three types on transactions to testArray
    testArrayLength = AuditableBanking.processCommand(TEST_COMMAND, testArray, testArrayLength);
    testArrayLength = AuditableBanking.processCommand(TEST_COMMAND2, testArray, testArrayLength);
    testArrayLength = AuditableBanking.processCommand(TEST_COMMAND3, testArray, testArrayLength);

    if (AuditableBanking.calculateCurrentBalance(testArray,
        testArrayLength) == EXPECTED_CURENT_BALANCE) {
      return true;
    } else {
      return false;
    }
  }


  /**
   * Tests the calculateNumberOfOverdrafts(int[][], int) method from AuditableBanking by submitting
   * a test array with transactions and checking if the number of overdrafts returned matches the
   * expected number of overdrafts
   * 
   * @return true if calculateNumberOfOverdrafts(int[][], int) returns the correct balance, false if
   *         it does not
   * @see AuditableBanking#calculateNumberOfOverdrafts(int[][], int)
   */
  public static boolean testCalculateNumberOfOverdrafts() {
    boolean foundProblem = false;
    int[][] transactions = new int[][] {{1, 10, -20, +30, -20, -20}, // +2 overdrafts (balance: -20)
        {0, 1, 1, 1, 0, 0, 1, 1, 1, 1}, // +2 overdrafts (balance: -15)
        {1, 115}, // +0 overdrafts (balance: +100)
        {2, 3, 1, 0, 1}, // +1 overdrafts (balance: -100)
    };

    // test with a single transaction of the Integer Amount encoding
    int transactionCount = 1;
    int overdrafts = AuditableBanking.calculateNumberOfOverdrafts(transactions, transactionCount);
    if (overdrafts != 2) {
      System.out.println("FAILURE: calculateNumberOfOverdrafts did not return 2 when"
          + " transactionCount = 1, and transactions contained: "
          + Arrays.deepToString(transactions));
      foundProblem = true;
    } else {
      System.out.println("PASSED TEST 1/2 of TestCalculateNumberOfOverdrafts!!!");
    }

    // test with four transactions: including one of each encoding
    transactionCount = 4;
    overdrafts = AuditableBanking.calculateNumberOfOverdrafts(transactions, transactionCount);
    if (overdrafts != 5) {
      System.out
          .println("FAILURE: calculateNumberOfOverdrafts did not return 5 when transactionCount"
              + " = 4, and transactions contained: " + Arrays.deepToString(transactions));
      foundProblem = true;
    } else {
      System.out.println("PASSED TESTS 2/2 of TestCalculateNumberOfOverdrafts!!!");
    }
    return !foundProblem;
  }

  public static void main(String[] args) {
    if (testProcessCommand()) {
      System.out.println("processCommand works!");
    } else {
      System.out.println("processCommand failed!");
    }

    if (testCalculateCurrentBalance()) {
      System.out.println("calculateCurrentBalance works!");
    } else {
      System.out.println("calculateCurrentBalance failed!");
    }

    if (testCalculateNumberOfOverdrafts()) {
      System.out.println("calculateNumberOfOverdrafts works!");
    } else {
      System.out.println("calculateNumberOfOverdrafts failed!");
    }
  }
}
