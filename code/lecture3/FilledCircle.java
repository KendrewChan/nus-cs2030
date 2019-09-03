import java.awt.Color;

public class FilledCircle extends Circle {
  // final means entity can only be assined once
  private final Color color;

  public FilledCircle(double radius, Color color) {
    super(radius);
    this.color = color;
  }

  public Color getColor() {
    return this.color;
  }

  @Override
  public String toString() {
    return super.toString() + ", color: " + getColor();
  }
}
