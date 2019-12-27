
public class TreeNode<T> {
  private T data;
  private ListADT<TreeNode<T>> children;
  
  public TreeNode(T data) {
    this.data=data;
  }
}
