class B extends A {
  B(int x) {
    super(x);
  }

  @Override
  public B method() {
    return new B(x);
  }
}