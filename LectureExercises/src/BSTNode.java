
public class BSTNode<T extends Comparable<T>> {
  T data;
  BSTNode<T> leftNode;
  BSTNode<T> rightNode;
  
  public BSTNode(T data) {
    this.data=data;
  }
}