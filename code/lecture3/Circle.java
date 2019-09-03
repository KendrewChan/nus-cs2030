public class Circle {
  private final double radius;

  public Circle(double radius) {
    this.radius = radius;
  }

  public double getArea() {
    return Math.PI * radius * radius;
  }

  public double getPerimeter() {
    return 2 * Math.PI * radius;
  }

  @Override
  public String toString() {
    return String.format("circle: area %.2f, perimeter %.2f", getArea(), getPerimeter());
  }
}