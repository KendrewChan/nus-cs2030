class Maybe<T> {
  private final T t;

  public Maybe() {
      t = null;
  }

  public Maybe(T t) {
      this.t = t;
  }

  public void ifPresent(Actionable<T> action) {
    if (t != null) {
      action.doit(t);
    }
  }

  public <R> Maybe<R> map(Mappable<T, R> mapper) {
    if (t != null) {
      return new Maybe<R>(mapper.apply(t));
    }

    return new Maybe<R>();
  }

  @Override
  public String toString() {
      return String.format("Maybe[%s]", (t == null) ? "empty" : t);
  }
}

