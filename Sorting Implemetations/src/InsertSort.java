import java.util.Random;

public class InsertSort {
  public static void sort(int[] arrayToSort) {
    for (int index = 1; index < arrayToSort.length; index++) {
      int indexOfValueToSwap = index;
      int temp=arrayToSort[index];
      
      while (indexOfValueToSwap > 0
          && temp < arrayToSort[indexOfValueToSwap-1]) {  
        arrayToSort[indexOfValueToSwap]=arrayToSort[indexOfValueToSwap-1];
        indexOfValueToSwap--;
      }
      arrayToSort[indexOfValueToSwap]=temp;
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

    sort(randomArray);

    for (int x = 0; x < randomArray.length; x++) {
      System.out.print(randomArray[x] + " ");
    }
  }
}
