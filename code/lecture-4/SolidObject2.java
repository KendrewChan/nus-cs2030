abstract class SoildObject {
  Material material;

  SolidObject(Material material) {
    this.material = material;
  }

  public double getDensity() {
    return this.material.getDensity();
  }

  public double getMass() {
    return getDensity() * getVolume();
  }
}