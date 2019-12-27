// Title: ProcessScheduleTests
// Files: CustomProcessQueue, CustomProcess, Scanner
// Course: CS300 Fall 2018
//
// Author: Michael Goldstein
// Email: mdgoldstein2@wisc.edu
// Lecturer's Name: Alexi Brooks

import java.util.Scanner;

/**
 * Class that represents a SJF process scheduler for a CPU and how it will run
 * 
 * @author Michael Goldstein
 * @see CustomProcessQueue
 * @see CustomProcess
 */
public class ProcessScheduler {
  private int currentTime; // stores the current time after the last run
  private int numProcessesRun; // stores the number of processes run so far
  private CustomProcessQueue queue; // this processing unit's custom process queue

  /**
   * Constructor for ProcessScheduler, initializes instance variables
   */
  public ProcessScheduler() {
    currentTime = 0;
    numProcessesRun = 0;
    queue = new CustomProcessQueue();
  }

  /**
   * Schedules a process to be executed by the CPU by adding it to the queue
   * 
   * @param process the CustomProcess to be scheduled
   */
  public void scheduleProcess(CustomProcess process) {
    queue.enqueue(process); // adds process to queue
  }

  /**
   * Runs all processes in the queue and creates and returns a log of all processes run
   * 
   * @return the log of all processes run as a String
   */
  public String run() {
    String runStorage = ""; // stores run log to be return when all processes in queue executed
    runStorage += "Starting " + queue.size();

    // changes start message based on size
    if (queue.size() != 1) {
      runStorage += " processes\n\n";
    } else {
      runStorage += " process\n\n";
    }

    // loop that executes all processes in queue
    while (!queue.isEmpty()) {
      CustomProcess tempProcess = queue.dequeue(); // dequeues and temporarily stores a process
      runStorage +=
          "Time " + currentTime + " : Process ID " + tempProcess.getProcessId() + " Starting.\n";
      // increments time by the burst time of the dequeued process
      currentTime += tempProcess.getBurstTime();
      runStorage +=
          "Time " + currentTime + " : Process ID " + tempProcess.getProcessId() + " Completed.\n";
      numProcessesRun++; // increases the number of run processes
    }

    // finishing entry in log when all processes completed
    runStorage += "\nTime " + currentTime + " : All scheduled processes completed.\n";
    return runStorage; // returns the log
  }

  /**
   * Driver method that provides UI and usability for ProcessScheduler
   */
  private static void driver() {
    Scanner in = new Scanner(System.in).useDelimiter("\n"); // uses \n to handle scheduling input
    ProcessScheduler scheduler = new ProcessScheduler(); // ProcessScheduler instance to be used
    boolean run = true; // flag to keep the application running

    //Application UI header
    System.out.println("==========   Welcome to the SJF Process Scheduler App   ========\n\n");

    // loop that runs application
    while (run) {
      printMenu();
      run = handleInput(in, scheduler);
    }

    // end message
    System.out.print(scheduler.numProcessesRun + " processes run in " + scheduler.currentTime
        + " units of time!\n");
    System.out.print("Thank you for using our scheduler!\n");
    System.out.print("Goodbye!\n");
  }

  /**
   * Prints out the menu options for the application
   */
  private static void printMenu() {
    System.out.print("Enter command:\n");
    System.out.print("[schedule <burstTime>] or [s <burstTime>]\n");
    System.out.print("[run] or [r]\n");
    System.out.print("[quit] or [q]\n");
  }

  /**
   * Deals with user input from keyboard by executing user's command (if valid) and checks if the
   * application loop should continue to run
   * 
   * @param in        the Scanner the input is read from
   * @param scheduler the ProcessScheduler to add a process to or run
   * @return true if the application should continue to run, false if not
   */
  private static boolean handleInput(Scanner in, ProcessScheduler scheduler) {
    // trims and splits input after making it all lowercase
    String input = in.next();
    String lowercaseInput = input.toLowerCase();
    String trimmedInput = lowercaseInput.trim();
    String[] inputs = trimmedInput.split(" ");

    // checks what kind of command is entered
    if (inputs.length == 1) {
      // should be run or quit command if length 1
      if (inputs[0].equals("run") || inputs[0].equals("r")) {
        // runs all processes in queue
        System.out.print(scheduler.run());
      } else if (inputs[0].equals("quit") || inputs[0].equals("q")) {
        // quits application
        return false;
      } else {
        System.out.print("WARNING: Please enter a valid command!\n");
      }
    } else if (inputs.length == 2) {
      // only scheduling commands are length 2
      if (inputs[0].equals("schedule") || inputs[0].equals("s")) {
        // scheduling has been formatted correctly, so schedules a function
        // try catch makes sure that burstTime is an integer
        try {
          int burstTime = Integer.parseInt(inputs[1]);
          // makes sure that burstTime is positive
          if (burstTime > 0) {
            CustomProcess tempProcess = new CustomProcess(burstTime);
            scheduler.scheduleProcess(tempProcess);
            System.out.print("Process ID " + tempProcess.getProcessId()
                + " scheduled. Burst Time = " + tempProcess.getBurstTime() + "\n");
          } else {
            System.out.print("WARNING: burst time MUST be greater than 0!\n");
          }
        } catch (NumberFormatException a) {
          System.out.print("WARNING: burst time MUST be an integer!\n");
        }
      } else {
        // scheduling has not been formatted correctly
        System.out.print("WARNING: Please enter a valid command!\n");
      }
    } else {
      // no valid input has more than two separated Strings
      System.out.print("WARNING: Please enter a valid command!\n");
    }
    return true; // application should stay running if loop has made it this far
  }
  
  /**
   * Main method, which calls application driver
   * @param args user input from keyboard, use for adding processes
   */
  public static void main(String[] args) {
    driver();
  }
}
