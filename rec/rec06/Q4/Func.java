import java.util.function.Function;

public abstract class Func<T, R> {
  public abstract R apply(T arg);

  public Func<T, R> compose(Func<T, T> func) {
    return new Func<>() {
      public R apply(T arg) {
        return Func.this.apply(func.apply(arg));
      }
    };
  }
}