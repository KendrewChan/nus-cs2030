public class Circle implements Shape, Scalable, Printable {
  private final double radius;

  public Circle(double radius) {
    this.radius = radius;
  }

  @Override
  public double getArea() {
    return Math.PI * radius * radius;
  }

  @Override
  public double getPerimeter() {
    return 2 * Math.PI * radius;
  }

  @Override
  public Circle scale(double factor) {
    return new Circle(this.radius * factor);
  }

  @Override
  public String print() {
    return String.format("circle: area %.2f, perimeter %.2f", getArea(), getPerimeter());
  }
}