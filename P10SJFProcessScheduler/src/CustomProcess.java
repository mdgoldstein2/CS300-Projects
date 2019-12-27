// Title: CustomProcess
// Files: Comparable
// Course: CS300 Fall 2018
//
// Author: Michael Goldstein
// Email: mdgoldstein2@wisc.edu
// Lecturer's Name: Alexi Brooks

/**
 * Class that represents a process to be stored in a waiting queue and executed by the CPU
 * 
 * @author Michael Goldstein
 *
 */
public class CustomProcess implements Comparable<CustomProcess> {
  private static int nextProcessId = 1; // stores the id to be assigned to the next process
  // to be created
  private final int PROCESS_ID; // unique identifier for this process
  private int burstTime; // time required by this process for CPU execution

  /**
   * Constructor for CustomProcess. Creates a new CustomProcess instance and initializes instance
   * fields
   * 
   * @param burstTime the value the burstTime field of the new instance will be initialized to
   */
  public CustomProcess(int burstTime) {
    this.burstTime = burstTime; // initializes fields
    PROCESS_ID = nextProcessId;
    nextProcessId++; // increments the nextProcess for when the next instance will be created
  }

  /**
   * Returns this instance's PROCESS_ID field
   * 
   * @return this instance's PROCESS_ID
   */
  public int getProcessId() {
    return PROCESS_ID;
  }

  /**
   * Returns this instance's burst time field
   * 
   * @return this instance's burst time field
   */
  public int getBurstTime() {
    return burstTime;
  }

  /**
   * Compares the priorities of two CustomProcesses (the priority of this instance and another
   * instance)
   * 
   * @param other the other CustomProcess whose priority will be compared to this instance's
   *              priority
   * @return -1 if this CustomProcess instance has a lower burst time (or process id in case of tie)
   *         1 if the other instance has a lower burst time (or process id in case of tie)
   */
  @Override
  public int compareTo(CustomProcess other) {
    // compares burst times of the CustomProcesses
    if (this.getBurstTime() < other.getBurstTime()) {
      return -1; // if this instance's burst time is lower, return -1
    } else if (this.getBurstTime() > other.getBurstTime()) {
      return 1; // if the other instance's burst time is lower, return 1
    } else {
      // if burst times equal, compare process ids
      if (this.getProcessId() < other.getProcessId()) {
        return -1; // if this instance's process id is lower, return -1
      } else {
        return 1; // if the other instance's process id is lower, return 1
      }
    }
  }
}
