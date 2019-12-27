import java.util.Random;

public class MergeSort {
  public static void sort(int[] arrayToSort, int start, int end) {
    if(start==end || start==end-1) {
      return;
    }
    
    int middle = (start+end)/2;
    sort(arrayToSort, start, middle);
    sort(arrayToSort, middle, end);
    
    int leftPointer=start;
    int rightPointer=middle;
    int dataPointer=0;
    int[] tempData = new int [end-start];
    
    while(leftPointer<middle && rightPointer<end) {
      if(arrayToSort[leftPointer]<=arrayToSort[rightPointer]) {
        tempData[dataPointer]=arrayToSort[leftPointer];
        leftPointer++;
      } else {
        tempData[dataPointer]=arrayToSort[rightPointer];
        rightPointer++;
      }
      dataPointer++;
    }
    
    while(leftPointer<middle) {
      tempData[dataPointer]=arrayToSort[leftPointer];
      leftPointer++;
      dataPointer++;
    }
    
    while(rightPointer<end) {
      tempData[dataPointer]=arrayToSort[rightPointer];
      rightPointer++;
      dataPointer++;
    }
    
    for(int index=0; index<dataPointer; index++) {
      arrayToSort[index+start]=tempData[index];
    }
  }

  public static void main(String[] args) {
    Random r = new Random();
    int randomArray[] = new int[10];

    for (int x = 0; x < randomArray.length; x++) {
      randomArray[x] = r.nextInt(20);
      System.out.print(randomArray[x] + " ");
    }
    System.out.print("\n");

    sort(randomArray, 0, randomArray.length);

    for (int x = 0; x < randomArray.length; x++) {
      System.out.print(randomArray[x] + " ");
    }
  }
}
