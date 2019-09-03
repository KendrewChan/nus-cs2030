class Test {
  public static void main(String[] args) {
    // Since test uses point (Both Point and Test will be compiled at the same time)
    Point p = new Point(0, 0);
    Point q = new Point(1, 1);

    p.setX(123);

    System.out.println(p.distance(q));
    System.out.println(p.getX());
  }
}