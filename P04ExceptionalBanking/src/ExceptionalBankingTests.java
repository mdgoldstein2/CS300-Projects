/**
 * A class that tests whether the methods of Account and TransactionGroup function correctly
 * 
 * @author Michael Goldstein
 * @version 1.0
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class ExceptionalBankingTests {
  /**
   * Driver method that runs tester methods and reports whether or not the tests failed
   * 
   * @param args
   */
  public static void main(String[] args) {
    int fails = 0;
    if (!testAccountBalance()) {
      fails++;
      System.out.println("testAccountBalance() [checking if correct balance is computed] failed");
    }
    if (!testOverdraftCount()) {
      fails++;
      System.out.println("testOverdraftCount() [checking if correct number of overdrafts is "
          + "computed] failed");
    }
    if (!testTransactionGroupEmpty()) {
      fails++;
      System.out.println("testTransactionGroupEmpty() [checking if exception is thrown when an"
          + "empty int array is passed through] failed");
    }
    if (!testTransactionGroupInvalidEncoding()) {
      fails++;
      System.out.println("testTransactionGroupInvalidEncoding() [checking if exception is thrown"
          + " when an invalidly encoded int array is passed through] failed");
    }
    if (!testAccountAddNegativeQuickWithdraw()) {
      fails++;
      System.out.println("testAccountAddNegativeQuickWithdraw() [checking if exception is thrown"
          + " when a quick withdraw encoided int array with negative values is passed through]"
          + " failed");
    }
    if (!testAccountBadTransactionGroup()) {
      fails++;
      System.out.println("testAccountAddNegativeQuickWithdraw() [checking if exception is thrown"
          + " when a String with nonintegers separated by spaces is passed through] failed");
    }
    if (!testAccountIndexOutOfBounds()) {
      fails++;
      System.out.println("testAccountIndexOutOfBounds() [checking if exception is thrown"
          + " when an invalid transaction index is passed through] failed");
    }
    if (!testAccountMissingFile()) {
      fails++;
      System.out.println("testAccountMissingFile() [checking if exception is thrown"
          + " when an invalid file is passed through] failed");
    }
    if (fails == 0) {
      System.out.println("No tests failed!");
    }
  }

  /**
   * Tests if getCurrentBalance() method works correctly
   * 
   * @return true if getCurrentBalance() returns the expected balance, false if it does not
   */
  public static boolean testAccountBalance() {
    // creates an Account instance to test as well as test commands and expected result
    Account testAccount1 = new Account("testAccount1");
    final String TEST_TRANSACTION_COMMAND1 = "0 1 1 0 1"; // net balance of 2
    final String TEST_TRANSACTION_COMMAND2 = "1 50"; // net balance of 50
    final String TEST_TRANSACTION_COMMAND3 = "2 1 0 0 0"; // net balance of -20
    final int EXPECTED_BALANCE = 32;

    // adds the test commands and checks if the test Account computes the correct current balance
    try {
      testAccount1.addTransactionGroup(TEST_TRANSACTION_COMMAND1);
      testAccount1.addTransactionGroup(TEST_TRANSACTION_COMMAND2);
      testAccount1.addTransactionGroup(TEST_TRANSACTION_COMMAND3);
    } catch (DataFormatException newException) {
      System.out.println(newException.getMessage());
      return false;
    }


    if (testAccount1.getCurrentBalance() == EXPECTED_BALANCE) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Tests if getNumberOfOverdrafts() method works correctly
   * 
   * @return true if getNumberOfOverdrafts() returns the expected number, false if it does not
   */
  public static boolean testOverdraftCount() {
    // creates an Account instance to test as well as test command and expected result
    Account testAccount2 = new Account("testAccount1");
    final String TEST_TRANSACTION_COMMAND1 = "0 1 1 1 0 0 1 0 0 1 0 0 0 0 0"; // should have 1 OD
    final String TEST_TRANSACTION_COMMAND2 = "0 1 1 0 0"; // should have 1 OD as well
    final int EXPECTED_NUMBER_OF_OVERDRAFTS = 2;

    // adds the test commands and checks if the test Account computes the correct number
    // of overdrafts
    try {
      testAccount2.addTransactionGroup(TEST_TRANSACTION_COMMAND1);
      testAccount2.addTransactionGroup(TEST_TRANSACTION_COMMAND2);
    } catch (DataFormatException newException) {
      System.out.println(newException.getMessage());
      return false;
    }

    if (testAccount2.getNumberOfOverdrafts() == EXPECTED_NUMBER_OF_OVERDRAFTS) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * This method checks whether the TransactionGroup constructor throws an exception with an
   * appropriate message, when it is passed an empty int[].
   * 
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testTransactionGroupEmpty() {
    int emptyArray[] = new int[0];
    try {
      TransactionGroup testGroup = new TransactionGroup(emptyArray);
    } catch (DataFormatException newException) {
      return true;
    }
    return false;
  }

  /**
   * This method checks whether the TransactionGroup constructor throws an exception with an
   * appropriate message, when then first int in the provided array is not 0, 1, or 2.
   * 
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testTransactionGroupInvalidEncoding() {
    int badArray[] = new int[] {3, 0, 1, 1, 0, 1};
    try {
      TransactionGroup testGroup = new TransactionGroup(badArray);
    } catch (DataFormatException newException) {
      return true;
    }
    return false;
  }

  /**
   * This method checks whether the Account.addTransactionGroup method throws an exception with an
   * appropriate message, when it is passed a quick withdraw encoded group that contains negative
   * numbers of withdraws.
   * 
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testAccountAddNegativeQuickWithdraw() {
    String negativeQuickWithdraw = "2 -1 1 -1 0";
    Account testAccount = new Account("tester");
    try {
      testAccount.addTransactionGroup(negativeQuickWithdraw);;
    } catch (DataFormatException newException) {
      return true;
    }
    return false;
  }

  /**
   * This method checks whether the Account.addTransactionGroup method throws an exception with an
   * appropriate message, when it is passed a string with space separated English words (non-int).
   * 
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testAccountBadTransactionGroup() {
    String badCommand = "Clearly Not Integers Separated By Spaces";
    Account testAccount = new Account("tester");
    try {
      testAccount.addTransactionGroup(badCommand);
    } catch (DataFormatException newException) {
      return true;
    }
    return false;
  }

  /**
   * This method checks whether the Account.getTransactionAmount method throws an exception with an
   * appropriate message, when it is passed an index that is out of bounds.
   * 
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testAccountIndexOutOfBounds() {
    String goodCommand = "0 1 1 0 0";
    Account testAccount = new Account("tester");
    try {
      testAccount.addTransactionGroup(goodCommand);
      testAccount.getTransactionAmount(2);
    } catch (IndexOutOfBoundsException newException) {
      return true;
    } catch (DataFormatException newException) {
      System.out.println(newException.getMessage());
    }
    return false;
  }

  /**
   * This method checks whether the Account constructor that takes a File parameter throws an
   * exception with an appropriate message, when it is passed a File object that does not correspond
   * to an actual file within the file system.
   * 
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testAccountMissingFile() {
    File fakeFile = new File("fakeFileName");
    try {
      Account TestAccount = new Account(fakeFile);
    } catch (FileNotFoundException newException) {
      return true;
    }
    return false;
  }
}
