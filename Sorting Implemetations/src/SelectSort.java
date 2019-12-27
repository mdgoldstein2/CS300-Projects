import java.util.Random;
public class SelectSort {
  public static void sort (int[] arrayToSort) {
    for(int i=0; i<arrayToSort.length; i++) {
      int minIndex=i;
      for(int index=i+1; index<arrayToSort.length; index++) {
        if(arrayToSort[index]<arrayToSort[minIndex]) {
          minIndex=index;
        }
      }
      int temp=arrayToSort[minIndex];
      arrayToSort[minIndex]=arrayToSort[i];
      arrayToSort[i]=temp;
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
