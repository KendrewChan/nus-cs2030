class P {
  private int x;

  public void changeSelf() {
    this.x = 1;
  }

  public void changeAnother(P p) {
    p.x = 1; // is ok
  }
}

class Q {
  public void changeAnother(P p) {
    // Not allowed
    // p.x = 1;
  }
}