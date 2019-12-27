import java.util.ArrayList;

public class TestComparableRectangle {
  public static void main(String[] args) {
    ArrayList<Rectangle> rectList = new ArrayList<Rectangle>();
    rectList.add(new Rectangle(4, 6));
    rectList.add(new Rectangle(5, 8));
    rectList.add(new Rectangle(4, 7));
    System.out.println(isSorted(rectList));
    sortRectangles(rectList);
    System.out.println(isSorted(rectList));
  }

  public static void sortRectangles(ArrayList<Rectangle> rectArrayList) {
    rectArrayList.sort(null);
  }
  
  public static boolean isSorted(ArrayList<Rectangle> rectArrayList) {
    for(int index=0; index<rectArrayList.size()-1; index++) {
      if(rectArrayList.get(index).compareTo(rectArrayList.get(index+1))!=-1) {
        return false;
      }
    }
    return true;
  }
}
