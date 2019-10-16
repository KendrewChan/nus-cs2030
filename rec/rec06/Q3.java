import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Q3 {
  public static <T, U, R> Stream<R> product(List<? extends T> list1, List<? extends U> list2,
      BiFunction<? super T, ? super U, R> func) {
    return list1.stream().flatMap(x -> list2.stream().map(y -> func.apply(x, y)));
  }
}