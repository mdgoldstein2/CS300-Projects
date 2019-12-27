// Title: CustomProcessQueue
// Files: WaitingQueueADT, CustomProcess
// Course: CS300 Fall 2018
//
// Author: Michael Goldstein
// Email: mdgoldstein2@wisc.edu
// Lecturer's Name: Alexi Brooks
/**
 * Class that implements a priority queue (as a heap) as a way to store CustomProcess objects.
 * 
 * @author Michael Goldstein
 * @see WaitingQueueADT
 * @see CustomProcess
 */
public class CustomProcessQueue implements WaitingQueueADT<CustomProcess> {
  private static final int INITIAL_CAPACITY = 20; // the initial capacity of the heap
  private CustomProcess[] heap; // array-based min heap storing the data. This is an oversize array
  private int size; // number of CustomProcesses present in this CustomProcessQueue

  /**
   * Constructor for CustomProcessQueue. Initializes instance fields
   */
  public CustomProcessQueue() {
    heap = new CustomProcess[INITIAL_CAPACITY];
    size = 0;
  }

  /**
   * Inserts a new CustomProcess object into the heap, expanding the heap's maximum size if
   * necessary
   * 
   * @param newProcess a new CustomProcess object to be added to the heap
   * @see CustomProcessQueue#minHeapPercolateUp(int)
   * @see CustomProcessQueue#expandHeap()
   */
  public void enqueue(CustomProcess newProcess) {
    // checks if a new object will fit in the heap
    if (size + 1 > heap.length - 1) {
      expandHeap(); // if not, calls expandHeap() to allow a new object to fit
    }

    size++; // increments size by 1 to account for new node
    // adds newProcesss at leftmost available "node" at max depth of heep to maintain shape
    // constraint
    heap[size] = newProcess;
    minHeapPercolateUp(size); // calls recursive helper on new "node"
  }

  /**
   * Since the class does not implement a shadow array, this method copies the heap into a new heap
   * with double the capacity if a new object will not fit.
   */
  private void expandHeap() {
    // temporary heap to be copied to
    CustomProcess[] tempHeap = new CustomProcess[heap.length * 2];

    // copies contents of old heap into temporary
    for (int index = 1; index < heap.length; index++) {
      tempHeap[index] = heap[index];
    }
    heap = tempHeap; // has heap point to the now loaded tempHeap
  }

  /**
   * Recursive helper method for enqueue. Compares a CustomProcess object with its parent node to
   * check if it has a higher priority, swaps the nodes and calls itself on the new parent node if
   * the parent node has a lower priority
   * 
   * @param index the index of the node to be compared with its parent and possibly bubbled up.
   * @see CustomProcessQueue#minHeapPercolateUp(int)
   */
  private void minHeapPercolateUp(int index) {
    // checks that index refers to an initialized "node" in the heap and is not the root "node", as
    // the root "node" has no parent to be compared to (and this algorithm would compare it to
    // heap[0], which is null)
    if (index > 1 && index <= size) {
      if (heap[index].compareTo(heap[index / 2]) < 0) {
        // if the child node has a higher priority, it is swapped with its parent
        CustomProcess temp = heap[index / 2]; // temp node that stores the value of the parent node
        heap[index / 2] = heap[index];
        heap[index] = temp;

        // calls minHeapPercolateUp on the newly swapped node
        minHeapPercolateUp(index / 2);
      }
    }
  }

  /**
   * Removes and returns the object with the highest priority from the heap
   * 
   * @return the object with the highest priority in the heap, or null if the heap is empty (size<0)
   * @see CustomProcessQueue#minHeapPercolateUp(int)
   */
  public CustomProcess dequeue() {
    CustomProcess temp = heap[1]; // stores root node to return as it will be removed from the heap

    // checks if the heap has more than one node
    if (size > 1) {
      // if there is more than one node, the rightmost node at the maximum depth of the heap is
      // moved to the root position to maintain shape constraint and remove the current root
      heap[1] = heap[size];
      heap[size] = null;
      size--; // decrements size as a node has been removed from the stack
      minHeapPercolateDown(1); // calls recursive helper on root
    } else if (size > 0) {
      // if there is only a root node, the root node is removed by setting it to null
      heap[1] = null;
      size--; // decrements size as a node has been removed from the stack
    }
    return temp; // returns the former root
  }

  /**
   * Recursive helper for dequeue. Compares a node with its children, if its priority is lower than
   * either or both of its children, it is swapped with the child with the highest priority, then
   * the helper is called on the swapped node
   * 
   * @param index the index of the node being compared to its children
   * @see CustomProcessQueue#dequeue()
   */
  private void minHeapPercolateDown(int index) {
    // checks which children the node at index has
    boolean hasLeftChild;
    boolean hasRightChild;

    if (index * 2 <= size) {
      hasLeftChild = true;
    } else {
      hasLeftChild = false;
    }
    if ((index * 2) + 1 <= size) {
      hasRightChild = true;
    } else {
      hasRightChild = false;
    }

    // compares node with its children (if they exist)
    if (hasLeftChild && hasRightChild) {
      // if the node has two children with higher priority than it
      if (heap[index].compareTo(heap[index * 2]) > 0
          && heap[index].compareTo(heap[(index * 2) + 1]) > 0) {
        // if left child has a higher priority, swaps parent with left child
        if (heap[index * 2].compareTo(heap[(index * 2) + 1]) < 0) {
          CustomProcess temp = heap[index * 2]; // temp node for child node's value
          heap[index * 2] = heap[index];
          heap[index] = temp;
          minHeapPercolateDown(index * 2); // calls method again on swapped node's new index
        } else {
          // if right child has higher priority, swaps parent with right child
          CustomProcess temp = heap[(index * 2) + 1]; // temp node for child node's value
          heap[(index * 2) + 1] = heap[index];
          heap[index] = temp;
          minHeapPercolateDown((index * 2) + 1); // calls method again on swapped node's new index
        }
      } else if (heap[index].compareTo(heap[index * 2]) > 0) {
        // if only left child has a higher priority, swaps parent with left child
        CustomProcess temp = heap[index * 2]; // temp node for child node's value
        heap[index * 2] = heap[index];
        heap[index] = temp;
        minHeapPercolateDown(index * 2); // calls method again on swapped node's new index
      } else if (heap[index].compareTo(heap[(index * 2) + 1]) > 0) {
        // if only right child has a higher priority, swaps parent with left child
        CustomProcess temp = heap[(index * 2) + 1]; // temp node for child node's value
        heap[(index * 2) + 1] = heap[index];
        heap[index] = temp;
        minHeapPercolateDown(index * 2); // calls method again on swapped node's new index
      }
    } else if (hasLeftChild) {
      // if the node only has one child (which can only be the left child because of shape
      // constraint)
      // if left child has a higher priority, swaps parent with left child
      if (heap[index].compareTo(heap[index * 2]) > 0) {
        CustomProcess temp = heap[index * 2]; // temp node for child node's value
        heap[index * 2] = heap[index];
        heap[index] = temp;
        minHeapPercolateDown(index * 2); // calls method again on swapped node's new index
      }
    }
  }

  /**
   * Returns (without removing) the object with the highest priority in the heap
   * 
   * @return the object with the highest priority in the heap
   */
  public CustomProcess peek() {
    return heap[1];
  }

  /**
   * Returns the size of the heap
   * 
   * @return the size of the heap
   */
  public int size() {
    return size;
  }

  /**
   * Checks if the heap is empty
   * 
   * @return true if the heap is empty, false if it is not
   */
  public boolean isEmpty() {
    // if size is 0, list should be empty. Size should never be negative, so if not 0, size is
    // positive and should not be empty
    if (size == 0) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Returns the heap for testing purposes
   * 
   * @return the heap
   */
  public CustomProcess[] getHeap() {
    return heap;
  }
}
