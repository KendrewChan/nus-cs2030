class A {
  int x;

  A(int x) {
    this.x = x;
  }

  public A method() {
    System.out.printf("%d\n", x);
    return new A(x);
  }
}