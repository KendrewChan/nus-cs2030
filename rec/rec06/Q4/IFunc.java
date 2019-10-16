public interface IFunc<R, T> {
  public R apply(T arg);

  public default <V> IFunc<V, R> compose(IFunc<? super V, T> func) {
    return new IFunc<>() {
      public R apply(T arg) {
        return IFunc.this.apply(func.apply(arg));
      }
    };
  };
}