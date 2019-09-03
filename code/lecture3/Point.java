class Point {
  // Private property cannot be changed outside
  // Very important to private this stuff
  // Use methods to set and get them
  /**
   * Default is somewhat like public if you don't use packages?
   * 
   * Encapsulation: - x and y privatised
   * 
   * final means once code is assigned, it cannot be mutated - in order to still
   * be able to set and inc, return a new point
   */
  private final double x;
  private final double y;

  // Constructor
  // Write your code as readable as possible
  Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  Point setX(double x) {
    return new Point(x, this.y);
  }

  Point incX(double x) {
    return new Point(this.x + x, this.y);
  }

  // Getter/Accessor
  double getX() {
    return this.x;
  }

  // Getter/Accessor
  double getY() {
    return this.y;
  }

  public double distance(Point q) {
    double dx = this.x - q.x;
    double dy = this.y - q.y;
    return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
  }

  // Annotation makes the compiler check if you are really overriding
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } else if (obj instanceof Point) {
      Point p = (Point) obj;
      return Math.abs(this.x - p.getX()) < 1E-15 && Math.abs(this.y - p.getY()) < 1E-15;
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return "(" + this.x + ", " + this.y + ")";
  }
}