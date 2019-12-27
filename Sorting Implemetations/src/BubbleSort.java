import java.util.Random;
public class BubbleSort {
  public static void sort (int[] arrayToSort) {
    boolean wasSwapped=true;
    
    while(wasSwapped) {
      wasSwapped=false;
      for(int index=0; index<arrayToSort.length-1; index++) {
        if(arrayToSort[index]>arrayToSort[index+1]) {
          wasSwapped=true;
          int temp=arrayToSort[index];
          arrayToSort[index]=arrayToSort[index+1];
          arrayToSort[index+1]=temp;
        }
      }
    }
  }
  
  public static void main(String[] args) {
    Random r = new Random();
    int randomArray[] = new int [10];
    
    for (int x=0; x<randomArray.length; x++) {
      randomArray[x]=r.nextInt(20);
      System.out.print(randomArray[x] + " ");
    }
    System.out.print("\n");
    
    sort(randomArray);
    
    for (int x=0; x<randomArray.length; x++) {
      System.out.print(randomArray[x] + " ");
    }
  }
}
