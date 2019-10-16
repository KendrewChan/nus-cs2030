class Main {
  public static void main(String[] args) {
    long startTime = System.currentTimeMillis();
    double sum = 0.0;
    for (int i = 0; i < Integer.MAX_VALUE; i++) {
      sum += i;
    }
    System.out.print(System.currentTimeMillis() - startTime);
  }
}