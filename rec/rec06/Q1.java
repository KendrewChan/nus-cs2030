import java.util.stream.IntStream;

class Q1 {
  
  public static boolean isPrime(int n) {
    return IntStream.range(2, n).noneMatch(x -> n % x == 0);
  }

/* --------------------------------- MODEL ANS -------------------------------- */

  public static IntStream primeFactorsOf(int n) {
    return IntStream.range(1, n + 1).filter(i -> n % i == 0 && isPrime(i));
  }

  public static int[] omega2(int n) {
    return IntStream.range(1, n + 1).map(i -> (int) primeFactorsOf(i).count());
  }

/* ------------------------------- OWN ATTEMPT ------------------------------ */

  /**
   * @return number of distinct prime factors for the number n
   */
  public static int nPrime(int n) {
    return (int) IntStream.rangeClosed(2, n).filter(x -> isPrime(x) && n % x == 0).count();
  }

  public static int[] omega(int n) {
    return IntStream.iterate(1, x -> x + 1).map(x -> Q1.nPrime(x)).limit(n).toArray();
  }
}