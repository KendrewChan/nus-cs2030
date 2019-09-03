class Circle {
  private final Point centre;
  private final double radius;
  
  Circle(Point centre, double radius) {
    this.centre = centre;
    this.radius = radius;
  }

  public boolean contains(Point point) {
    return this.centre.distance(point) < radius + 1E-15;
  }

  @Override
  public String toString() {
    return "Circle centered at " + this.centre + " with radius " + radius;
  }
}