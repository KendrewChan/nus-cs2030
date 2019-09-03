class Rectangle {
  private final double width;
  private final double height;

  Rectangle(double width, double height) {
    this.width = width;
    this.height = height;
  }

  public double getArea() {
    return this.width * this.height;
  }

  public double getPerimeter() {
    return 2 * this.width + 2 * this.height;
  }

  public Square setWidth(double newWidth) {
    return new Rectangle(newWidth, this.height);
  }

  public Square setHeight(double newHeight) {
    return new Rectangle(this.width, newHeight);
  }

  @Override
  public String toString() {
    return String.format("area %.2f and perimeter %.2f", this.getArea(), this.getPerimeter());
  }
}

class Square extends Rectangle {
  public Square(double width) {
    super(width, width);
  }

  
}