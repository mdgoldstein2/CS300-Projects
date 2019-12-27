
public class Rectangle implements Comparable <Rectangle>{ // implicitly extends Object
  private int length;
  private int height;

  public Rectangle(int length, int height) {
    this.length = length;
    this.height = height;
  }

  public Rectangle() {
    length = 4;
    height = 2;
  }

  public int getLength() {
    return length;
  }

  public int getHeight() {
    return height;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getArea() {
    return (length * height);
  }
  
  public String toString() {
    return ("Area: " + getArea());
  }

  @Override
  public int compareTo(Rectangle other) {  
    if (this.getArea() > other.getArea()) {
      return 1;
    } else if (this.getArea() == other.getArea()) {
      return 0;
    } else {
      return -1;
    }
  }
}
