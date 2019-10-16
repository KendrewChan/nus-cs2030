import java.util.Optional;

class Circle {
  private final Optional<Point> centre;
  private final double radius;

  private Circle(Point centre, double radius) {
    this.centre = Optional.of(centre);
    this.radius = radius;
  }

  public Optional<Point> getCentre() {
    return this.centre;
  }

  public static Optional<Circle> getCircle(Point centre, double radius) {
    if (radius > 0) {
      return Optional.of(new Circle(centre, radius));
    } else {
      return Optional.empty();
    }
  };

  public boolean contains(Point point) {
    return this.centre.get().distance(point) < radius + 1E-15;
  }

  @Override
  public String toString() {
    return "Circle centered at " + this.centre + " with radius " + this.radius;
  }
}