public class Rectangle implements Shape, Printable, Scalable {
  private final double width;
  private final double height;

  public Rectangle(double width, double height) {
    this.width = width;
    this.height = height;
  }

  @Override
  public double getArea() {
    return this.width * this.height;
  }

  @Override
  public double getPerimeter() {
    return this.width * 2 + this.height * 2;
  }

  @Override
  public Rectangle scale(double factor) {
    return new Rectangle(width * factor, height * factor);
  }

  @Override
  public String print() {
    return String.format("rectangle: area %.2f, perimeter %.2f", getArea(), getPerimeter());
  }
}