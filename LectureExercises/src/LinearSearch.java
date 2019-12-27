
public class LinearSearch<T extends Comparable<T>> {
  public static int searchFor(ListADT<Comparable> list, Comparable item) {
    for(int index=0; index<list.size(); index++) {
      if(item.compareTo(list.get(index))==0) {
        return index;
      }
    }
    return -1;
  }
}
