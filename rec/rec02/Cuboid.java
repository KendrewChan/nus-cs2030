class Cuboid implements SolidObject2 {
  private final double length;
  private final Material material;

  Cuboid(double height, double width, double length, Material material) {
    super(material);
    this.height = height;
    this.width = width;
    this.length = length;
  }
  public double getVolume() {
    return this.height * this.width * this.length;
  }

  @Override
  public String toString() {
    return String.format("Density: %.3f, Volume: %.3f, Area: %.3f", super.getDensity(), getVolume(), super.getArea());
  }
}