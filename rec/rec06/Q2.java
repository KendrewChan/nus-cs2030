import java.math.BigInteger;
import java.util.stream.Stream;

class Pair {
  private int left;
  private int right;

  public Pair(int left, int right) {
    this.left = left;
    this.right = right;
  }

  public int getLeft() {
    return this.left;
  }

  public int getRight() {
    return this.right;
  }
}

class Q2 {
  public static int getFib(int n) {
    Pair p = new Pair(0, 1);

    for (int i = 2; i <= n; i++) {
      p = new Pair(p.getRight(), p.getLeft() + p.getRight());
    }

    return p.getRight();
  }

  public static Stream<Integer> getNFib(int n) {
    return Stream.iterate(1, x -> x + 1).map(x -> Q2.getFib(x)).limit(n);
  }
}

/* --------------------------------- ANSWER --------------------------------- */

class Fib {
  /**
   * Since there are no internal implementation, don't need to set as S and T a
   * private
   * 
   * @param <S>
   * @param <T>
   */
  static class Pair<S, T> {
    S first;
    T second;

    Pair(S first, T second) {
      this.first = first;
      this.second = second;
    }
  }

  static Stream<BigInteger> fib(int n) {
    return Stream.iterate(new Pair<>(BigInteger.ONE, BigInteger.ONE), pair -> new Pair<>(pair.second, pair.first.add(pair.second))).map(pair -> pair.first).limit(n);
  }
}