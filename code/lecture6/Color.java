enum Color {
  BLACK(0, 0, 0), WHITE(1, 1, 1);

  private final double r;
  private final double g;
  private final double b;

  Color(double r, double g, double b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  public String toString() {
    return String.format("(%d, %d, %d)", this.r, this.g, this.b);
  }
}