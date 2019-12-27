/**
 * A class that creates and reads a group of bank transactions
 * 
 * @author UW-Madison CS300 Professors
 * @version 1.0
 * @see Account
 */

import java.util.zip.DataFormatException;

public class TransactionGroup {

  private enum EncodingType {
    BINARY_AMOUNT, INTEGER_AMOUNT, QUICK_WITHDRAW
  };

  private EncodingType type;
  private int[] values;

  /**
   * Constructor that creates new TransactionGroup object from the values in an integer array
   * 
   * @param groupEncoding the integer array the new TransactionGroup is created from
   * @throws DataFormatException when groupEncoding does not match one of the three transaction
   *                             encoding types
   */
  public TransactionGroup(int[] groupEncoding) throws DataFormatException {
    // checks and throws DataFormatException if groupEncoding[] does not match one of the three
    // transaction encoding types
    if (groupEncoding == null || groupEncoding.length == 0) {
      throw new DataFormatException("transaction group encoding cannot be null or empty");
    }
    if (!(groupEncoding[0] == 0 || groupEncoding[0] == 1 || groupEncoding[0] == 2)) {
      throw new DataFormatException(
          "the first element within a transaction group must be 0, 1, or 2");
    }
    if (groupEncoding[0] == 0) {
      for (int index = 1; index < groupEncoding.length; index++) {
        if (!(groupEncoding[index] == 0 || groupEncoding[index] == 1)) {
          throw new DataFormatException(
              "binary amount transaction groups may only contain 0s and 1s");
        }
      }
    }
    if (groupEncoding[0] == 1) {
      for (int index = 1; index < groupEncoding.length; index++) {
        if (groupEncoding[index] == 0) {
          throw new DataFormatException("integer amount transaction groups may not contain 0s");
        }
      }
    }
    if (groupEncoding[0] == 2) {
      if (groupEncoding.length != 5) {
        throw new DataFormatException("quick withdraw transaction groups must contain 5 elements");
      }
      for (int index = 1; index < groupEncoding.length; index++) {
        if (groupEncoding[index] < 0) {
          throw new DataFormatException(
              "quick withdraw transaction groups may not contain negative numbers");
        }
      }
    }

    this.type = EncodingType.values()[groupEncoding[0]];
    this.values = new int[groupEncoding.length - 1];
    for (int i = 0; i < values.length; i++)
      this.values[i] = groupEncoding[i + 1];
  }

  /**
   * Returns the number of transactions in TransactionGroup
   * 
   * @return number of transactions in Transaction group
   */
  public int getTransactionCount() {
    int transactionCount = 0;
    switch (this.type) {
      case BINARY_AMOUNT:
        int lastAmount = -1;
        for (int i = 0; i < this.values.length; i++) {
          if (this.values[i] != lastAmount) {
            transactionCount++;
            lastAmount = this.values[i];
          }
        }
        break;
      case INTEGER_AMOUNT:
        transactionCount = values.length;
        break;
      case QUICK_WITHDRAW:
        for (int i = 0; i < this.values.length; i++)
          transactionCount += this.values[i];
    }
    return transactionCount;
  }

  /**
   * Returns the value of one transaction in TransactionGRoup
   * 
   * @param transactionIndex the index of the transaction whose value should be returned
   * @return the value of the transaction whose index is equal to the parameter
   * @throws IndexOutOfBoundsException when transactionIndex is outside the allowable range of
   *                                   indexes
   */
  public int getTransactionAmount(int transactionIndex) throws IndexOutOfBoundsException {
    // throws IndexOutOfBoundsException when transactionIndex is outside the allowable range of
    // indexes
    if (transactionIndex > this.getTransactionCount() - 1) {
      throw new IndexOutOfBoundsException(
          "Index: " + transactionIndex + "Total transactions: " + this.getTransactionCount());
    }

    int transactionCount = 0;
    switch (this.type) {
      case BINARY_AMOUNT:
        int lastAmount = -1;
        int amountCount = 0;
        for (int i = 0; i <= this.values.length; i++) {
          if (i == this.values.length || this.values[i] != lastAmount) {
            if (transactionCount - 1 == transactionIndex) {
              if (lastAmount == 0)
                return -1 * amountCount;
              else
                return +1 * amountCount;
            }
            transactionCount++;
            lastAmount = this.values[i];
            amountCount = 1;
          } else
            amountCount++;
          lastAmount = this.values[i];
        }
        break;
      case INTEGER_AMOUNT:
        return this.values[transactionIndex];
      case QUICK_WITHDRAW:
        final int[] QW_AMOUNTS = new int[] {-20, -40, -80, -100};
        for (int i = 0; i < this.values.length; i++)
          for (int j = 0; j < this.values[i]; j++)
            if (transactionCount == transactionIndex)
              return QW_AMOUNTS[i];
            else
              transactionCount++;
    }
    return -1;
  }
}
