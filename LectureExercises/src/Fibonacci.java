
import java.util.Scanner;

public class Fibonacci {
  public static int generateFibonacci(int fibTerm) {
    if(fibTerm<0) {
      throw new IllegalArgumentException();  
    }
    
    if(fibTerm==0) {
      return 1; 
    } else if(fibTerm==1) {
      return 1;
    } else {
      return generateFibonacci(fibTerm-1)+generateFibonacci(fibTerm-2);
    }
  }
  
  public static void main(String[] args) {
    System.out.println();
  }
}
