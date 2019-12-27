// Title: ProcessSchedulerTests
// Files: CustomProcessQueue, CustomProcess, ProcessScheduler
// Course: CS300 Fall 2018
//
// Author: Michael Goldstein
// Email: mdgoldstein2@wisc.edu
// Lecturer's Name: Alexi Brooks

/**
 * Class that tests the functionality of CustomProcessQueue and ProcessScheduler
 * 
 * @author Michael Goldstein
 * @see CustomProcessQueue
 */
public class ProcessSchedulerTests {
  /**
   * Tests constructor, peek, size, and isEmpty of CustomProcessQueue
   * 
   * @return true if all methods above work correctly, false if not
   * @see CustomProcessQueue#CustomProcessQueue()
   * @see CustomProcessQueue#peek()
   * @see CustomProcessQueue#isEmpty()
   * @see CustomProcessQueue#isEmpty()
   */
  public static boolean testMiscCustomProcessQueue() {
    CustomProcessQueue testQueue = new CustomProcessQueue(); // test ProcessQueue

    // the root node should be null after initialization
    if (testQueue.peek() != null) {
      return false;
    }
    // the list should be empty after initialization
    if (!testQueue.isEmpty()) {
      return false;
    }
    // size should be 0 after initialization
    if (testQueue.size() != 0) {
      return false;
    }
    return true; // returns true if no test case failed
  }

  /**
   * Checks the correctness of the enqueue operation implemented in the CustomProcessQueue class
   * 
   * @return true if the enqueue method passes all test cases, false if it does not
   * @see CustomProcessQueue#enqueue(CustomProcess)
   */
  public static boolean testEnqueueCustomProcessQueue() {
    CustomProcessQueue testQueue = new CustomProcessQueue(); // test ProcessQueue
    CustomProcess testCustomProcessHeap[]; // heap from testQueue to check enqueue results with

    // adds test CustomProcesses. Heap array should be 3, 4, 5, 7, 5, 11, 10
    // Special cases for processes with same burst time
    CustomProcess testSameBurstTime1 = new CustomProcess(5);
    CustomProcess testSameBurstTime2 = new CustomProcess(5);

    testQueue.enqueue(testSameBurstTime1);
    testQueue.enqueue(new CustomProcess(7));
    testQueue.enqueue(new CustomProcess(3));
    testQueue.enqueue(new CustomProcess(4));
    testQueue.enqueue(testSameBurstTime2);
    testQueue.enqueue(new CustomProcess(11));
    testQueue.enqueue(new CustomProcess(10));
    testCustomProcessHeap = testQueue.getHeap();


    if (!(testCustomProcessHeap[0] == null)) {
      return false; // the 0 index should be always null, if an element is there the test fails
    }
    if (testQueue.size() != 7) {
      return false; // 7 processes should now be enqueued
    }

    // tests that values are in correct order, returns false if values incorrect/out of order
    if (!(testCustomProcessHeap[1].getBurstTime() == 3)) {
      return false;
    }
    if (!(testCustomProcessHeap[2].getBurstTime() == 4)) {
      return false;
    }
    if (!(testCustomProcessHeap[3].getBurstTime() == 5)
        || testCustomProcessHeap[3].getProcessId() != testSameBurstTime1.getProcessId()) {
      return false;
    }
    if (!(testCustomProcessHeap[4].getBurstTime() == 7)) {
      return false;
    }
    if (!(testCustomProcessHeap[5].getBurstTime() == 5)
        || testCustomProcessHeap[5].getProcessId() != testSameBurstTime2.getProcessId()) {
      return false;
    }
    if (!(testCustomProcessHeap[6].getBurstTime() == 11)) {
      return false;
    }
    if (!(testCustomProcessHeap[7].getBurstTime() == 10)) {
      return false;
    }
    return true; // returns true if all values in correct location
  }

  /**
   * Checks the correctness of the dequeue operation implemented in the CustomProcessQueue class
   * 
   * @return true if the dequeue method passes all test cases, false if it does not
   */
  public static boolean testDequeueCustomProcessQueue() {
    CustomProcessQueue testQueue = new CustomProcessQueue(); // test ProcessQueue

    // adds test CustomProcesses. Heap array should be 3, 4, 5, 7, 5, 11, 10
    // dequeued order should be 3, 4, 5, 5, 7, 10, 11
    // Special cases for processes with same burst time
    CustomProcess testSameBurstTime1 = new CustomProcess(5);
    CustomProcess testSameBurstTime2 = new CustomProcess(5);

    testQueue.enqueue(testSameBurstTime1);
    testQueue.enqueue(new CustomProcess(7));
    testQueue.enqueue(new CustomProcess(3));
    testQueue.enqueue(new CustomProcess(4));
    testQueue.enqueue(testSameBurstTime2);
    testQueue.enqueue(new CustomProcess(11));
    testQueue.enqueue(new CustomProcess(10));

    // dequeues heap and checks order to make sure dequeue works correctly
    if (!(testQueue.dequeue().getBurstTime() == 3)) {
      return false;
    }
    if (!(testQueue.dequeue().getBurstTime() == 4)) {
      return false;
    }

    // special check procedure for elements that should have same burst time
    CustomProcess shouldHaveSameBurstTime1 = testQueue.dequeue();
    CustomProcess shouldHaveSameBurstTime2 = testQueue.dequeue();
    if (!(shouldHaveSameBurstTime1.getBurstTime() == 5)
        || shouldHaveSameBurstTime1.getProcessId() != testSameBurstTime1.getProcessId()) {
      return false;
    }
    if (!(shouldHaveSameBurstTime2.getBurstTime() == 5)
        || shouldHaveSameBurstTime2.getProcessId() != testSameBurstTime2.getProcessId()) {
      return false;
    }

    // standard tests for rest
    if (!(testQueue.dequeue().getBurstTime() == 7)) {
      return false;
    }
    if (!(testQueue.dequeue().getBurstTime() == 10)) {
      return false;
    }
    if (!(testQueue.dequeue().getBurstTime() == 11)) {
      return false;
    }

    // checks if size is not 0, which it should be if dequeue works correctly
    if (testQueue.size() != 0) {
      return false;
    }

    // checks if queue is not empty, which it should be if dequeue works correctly
    if (!testQueue.isEmpty()) {
      return false;
    }
    return true; // returns true if all processes returned in correct order and heap is empty
  }

  /**
   * Tests ProcessScheduler's scheduleProcess and run methods
   * 
   * @return true if methods pass all test cases, false if not
   * @see ProcessScheduler#scheduleProcess(CustomProcess)
   * @see ProcessScheduler#run()
   */
  public static boolean testScheduleProcessRunProcessScheduler() {
    ProcessScheduler testScheduler = new ProcessScheduler(); // test scheduler

    // test tasks
    final CustomProcess[] testTasks = new CustomProcess[4];
    testTasks[0] = new CustomProcess(2);
    testTasks[1] = new CustomProcess(1);
    testTasks[2] = new CustomProcess(3);
    testTasks[3] = new CustomProcess(8);

    // schedules test tasks
    for (int index = 0; index < testTasks.length; index++) {
      testScheduler.scheduleProcess(testTasks[index]);
    }
    String testLog = testScheduler.run();

    // creates expected log
    String expectedLog = "Starting " + 4 + " processes\n\n";
    expectedLog += "Time " + 0 + " : Process ID " + testTasks[1].getProcessId() + " Starting.\n";
    expectedLog += "Time " + 1 + " : Process ID " + testTasks[1].getProcessId() + " Completed.\n";
    expectedLog += "Time " + 1 + " : Process ID " + testTasks[0].getProcessId() + " Starting.\n";
    expectedLog += "Time " + 3 + " : Process ID " + testTasks[0].getProcessId() + " Completed.\n";
    expectedLog += "Time " + 3 + " : Process ID " + testTasks[2].getProcessId() + " Starting.\n";
    expectedLog += "Time " + 6 + " : Process ID " + testTasks[2].getProcessId() + " Completed.\n";
    expectedLog += "Time " + 6 + " : Process ID " + testTasks[3].getProcessId() + " Starting.\n";
    expectedLog += "Time " + 14 + " : Process ID " + testTasks[3].getProcessId() + " Completed.\n";
    expectedLog += "\nTime " + 14 + " : All scheduled processes completed.\n";


    // checks that correct log was created from the scheduled processes and the run method
    if (!testLog.equals(expectedLog)) {
      return false; // test fails if log does not match creation
    }
    return true; // returns true if it does
  }

  /**
   * Driver method for tester class. Calls various tester methods and prints outs the results
   * 
   * @param args String input from keyboard. Not used in this case
   * @see ProcessScheduleTests#testEnqueueCustomProcessQueue()
   * @see ProcessScheduleTests#testDequeueCustomProcessQueue()
   */
  public static void main(String[] args) {
    int fails = 0; // keeps track of number of test failures

    // calls tester methods
    if (!testMiscCustomProcessQueue()) {
      System.out
          .println("testMiscCustomProcessQueue() [constructor, peek(), size(), isEmpty()] failed");
      fails++;
    }
    if (!testEnqueueCustomProcessQueue()) {
      System.out.println("testEnqueueCustomProcessQueue() [enqueue()] failed");
      fails++;
    }
    if (!testDequeueCustomProcessQueue()) {
      System.out.println("testDequeueCustomProcessQueue() [dequeue()] failed");
      fails++;
    }
    if (!testScheduleProcessRunProcessScheduler()) {
      System.out
          .println("testScheduleProcessRunProcessScheduler() [scheduleProcess(), run()] failed");
      fails++;
    }
    if (fails == 0) {
      System.out.println("No tests failed!");
    }
  }
}
