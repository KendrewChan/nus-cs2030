public class Point {
  private final double x;
  private final double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj instanceof Point) {
      Point point = (Point) obj;
      return this.x == point.x && this.y == point.y;
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return "(" + this.x + ", " + this.y + ")";
  }
}