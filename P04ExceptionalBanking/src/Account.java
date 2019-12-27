import java.io.File;
import java.util.zip.DataFormatException;
import java.util.regex.PatternSyntaxException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

/**
 * A class that creates a bank account object which loads a series of transactions from a source
 * file or other input
 * 
 * @author UW-Madison CS300 Professors
 * @version 1.0
 * @see TransactionGroup
 */
public class Account {

  private static final int MAX_GROUPS = 10000;
  private static int nextUniqueId = 1000;

  private String name;
  private final int UNIQUE_ID;
  private TransactionGroup[] transactionGroups;
  private int transactionGroupsCount;

  /**
   * A constructor that creates an Account object with name given by a parameter and initializes
   * UNIQUE_ID, transactionGroups[], and transactionGroupsCount
   * 
   * @param name String passed in which will be the name of the new Account object
   * @throws DataFormatException if DataFormatException is thrown by addTransactionGroup()
   * @see addTransactionGroup()
   */
  public Account(String name) {
    this.name = name;
    this.UNIQUE_ID = Account.nextUniqueId;
    Account.nextUniqueId++;
    this.transactionGroups = new TransactionGroup[MAX_GROUPS];
    this.transactionGroupsCount = 0;
  }

  /**
   * A constructor that creates an Account object with the name given by the file and transaction
   * groups imported from the file. Also initializes UNIQUE_ID, transactionGroups[], and
   * transactionGroupsCount
   * 
   * @param file The file from which the name and transactionGroups of the Account are read from
   * @throws DataFormatException if DataFormatException is thrown by addTransactionGroup()
   * @see addTransactionGroup()
   */
  public Account(File file) throws FileNotFoundException {
    FileInputStream input = new FileInputStream(file);
    Scanner in = new Scanner(input);
    // ... THIS WILL BE UPDATED TO READ FROM A FILE INSTEAD OF SYSTEM.IN.

    this.name = in.nextLine();
    this.UNIQUE_ID = Integer.parseInt(in.nextLine());
    Account.nextUniqueId = this.UNIQUE_ID + 1;
    this.transactionGroups = new TransactionGroup[MAX_GROUPS];
    this.transactionGroupsCount = 0;
    String nextLine = "";

    // checks if each line is a valid transaction group
    while (in.hasNextLine()) {
      try {
        this.addTransactionGroup(in.nextLine());
      } catch (DataFormatException newException) {
        System.out.println("A transaction group was improperly formatted in the file!");
      }
    }
    in.close();
  }

  /**
   * Returns the accounts uniqueID
   * 
   * @return uniqueID integer which is different for each account
   */
  public int getId() {
    return this.UNIQUE_ID;
  }

  /**
   * Creates a transactionGroup from a String parameter and adds that transaction group to
   * transactionGroups[]
   * 
   * @param command the String from which the transactionGroup will be created
   * @see TransactionGroup(int[]) constructor
   * @throws DataFormatException if DataFormatException is thrown by TransactionGroup(int[])
   *                             constructor or command does not contain space separated integer
   *                             values
   * @throws OutOfMemoryError    when more transaction groups are about to be added than there is
   *                             space in transactionGroups[]
   */
  public void addTransactionGroup(String command) throws DataFormatException, OutOfMemoryError {
    String[] parts;

    // checks if command has only space-separated integer values
    try {
      parts = command.split(" ");
    } catch (PatternSyntaxException newException) {
      throw new DataFormatException("addTransactionGroup requires string commands that contain only"
          + " space separated integer values");
    }
    int[] newTransactions = new int[parts.length];
    try {
      for (int i = 0; i < parts.length; i++)
        newTransactions[i] = Integer.parseInt(parts[i]);
    } catch (NumberFormatException anotherException) {
      throw new DataFormatException("addTransactionGroup requires string commands that contain only"
          + " space separated integer values");
    }

    if (this.transactionGroupsCount + 1 >= this.transactionGroups.length) {
      throw new OutOfMemoryError(
          "Number of Transaction Groups: " + (this.transactionGroupsCount + 1)
              + "Maximum Number of Transaction Groups: " + this.transactionGroups.length);
    }

    TransactionGroup t = new TransactionGroup(newTransactions);
    this.transactionGroups[this.transactionGroupsCount] = t;
    this.transactionGroupsCount++;
  }

  /**
   * Counts the total number of transactions the Account object has
   * 
   * @return the number of transactions
   */
  public int getTransactionCount() {
    int transactionCount = 0;
    for (int i = 0; i < this.transactionGroupsCount; i++)
      transactionCount += this.transactionGroups[i].getTransactionCount();
    return transactionCount;
  }

  /**
   * Returns the amount of a particular transaction given by an index
   * 
   * @param index the number of the transaction whose amount will be returned
   * @return the amount of the transaction with the given index
   * @throws IndexOutOfBoundsException when transactionIndex is outside the allowable range of
   *                                   indexes
   */
  public int getTransactionAmount(int index) {
    // checks if the index is within all transactions
    if (index > this.getTransactionCount() - 1) {
      throw new IndexOutOfBoundsException(
          "Index: " + index + "Total transactions: " + this.getTransactionCount());
    }

    int transactionCount = 0;
    for (int i = 0; i < this.transactionGroupsCount; i++) {
      int prevTransactionCount = transactionCount;
      transactionCount += this.transactionGroups[i].getTransactionCount();
      if (transactionCount > index) {
        index -= prevTransactionCount;
        return this.transactionGroups[i].getTransactionAmount(index);
      }
    }
    return -1;
  }

  /**
   * Returns the value of all transactions this Account object has
   * 
   * @return the value of all transactions in this Account's transactionGroups[]
   * @see getTransactionCount()
   */
  public int getCurrentBalance() {
    int balance = 0;
    int size = this.getTransactionCount();
    for (int i = 0; i < size; i++)
      balance += this.getTransactionAmount(i);
    return balance;
  }

  /**
   * Returns the number of overdrafts that occur when all transactions the Account object has are
   * executed
   * 
   * @return the number of overdrafts that occur when all transactions the Account object has are
   *         executed
   */
  public int getNumberOfOverdrafts() {
    int balance = 0;
    int overdraftCount = 0;
    int size = this.getTransactionCount();
    for (int i = 0; i < size; i++) {
      int amount = this.getTransactionAmount(i);
      balance += amount;
      if (balance < 0 && amount < 0)
        overdraftCount++;
    }
    return overdraftCount;
  }
}
