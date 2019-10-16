class Main {
  public static void main(String[] args) {
    F f = new F();
    G g = new G();

    // (a)
    System.out.println(f.apply(10));
    System.out.println(g.apply(10));

    // (b)
    System.out.println(f.compose(g).apply(10));

    // (c)


  }
}