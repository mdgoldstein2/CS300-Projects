import java.util.Random;

public class QuickSort {
  public static void sort(int[] arrayToSort, int start, int end) {
    if(start==end || start==end-1) {
      return;
    }
    
    int middleIndex = (start+end)/2;
    int leftPointer=start;
    int rightPointer=end;
    int pivot = arrayToSort[middleIndex];
    boolean done=false;
    
    while(!done) {
      while(arrayToSort[leftPointer]<pivot) {
        leftPointer++;
      }
      
      while(arrayToSort[rightPointer]>pivot) {
        rightPointer--;
      }
      
      if(leftPointer>=rightPointer) {
        done=true;
      } else {
        int temp=arrayToSort[leftPointer];
        arrayToSort[leftPointer]=arrayToSort[rightPointer];
        arrayToSort[rightPointer]=temp;
        
        leftPointer++;
        rightPointer--;
      }
    }
    
    sort(arrayToSort, start, rightPointer);
    sort(arrayToSort, rightPointer+1, end);
  }

  public static void main(String[] args) {
    Random r = new Random();
    int randomArray[] = new int[10];

    for (int x = 0; x < randomArray.length; x++) {
      randomArray[x] = r.nextInt(20);
      System.out.print(randomArray[x] + " ");
    }
    System.out.print("\n");

    sort(randomArray, 0, randomArray.length-1);

    for (int x = 0; x < randomArray.length; x++) {
      System.out.print(randomArray[x] + " ");
    }
  }
}
