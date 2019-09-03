class Test {
  public static void main(String args[]) {
    Circle c1 = new Circle(new Point(10, 10), 10);
    Circle c2 = new Circle(new Point(10, 10), 10);
    Object o1 = c1;
    Object o2 = c2;

    // Object::equals(Object) -> nothing gets printed
    // Circle::equals(Object) -> "equals(Object) called"
    // Circle::equals(Circle)
    
    o1.equals(o2); // Circle::equals(Object)
    o1.equals((Circle) o2); // Circle::equals(Object)
    o1.equals(c2); // Circle::equals(Object)
    o1.equals(c1); // Circle::equals(Object)

    c1.equals(o2); // Circle::equals(Object)
    c1.equals((Circle) o2); // Circle::equals(Circle)
    c1.equals(c2); // Circle::equals(Circle)
    c1.equals(o1); // Circle::equals(Object)
  }
}