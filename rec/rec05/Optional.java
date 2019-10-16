import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

class Optional<T> {
  private final T value;

  public Optional() {
    this.value = null;
  }

  public Optional(T value) {
    this.value = value;
  }

  public static <T> Optional<T> of(T v) throws NullPointerException {
    if (v.equals(null)) {
      throw new NullPointerException();
    }

    return new Optional<T>(v);
  }

  public static <T> Optional<T> ofNullable(T v) {
    if (v.equals(null)) {
      return new Optional<T>();
    }

    return new Optional<T>(v);
  }

  public static <T> Optional<T> empty() {
    return new Optional<T>();
  }

  public void ifPresent(Consumer<? super T> consumer) {
    if (!this.value.equals(null)) {
      consumer.accept(this.value);
    }
  }

  public Optional<T> filter(Predicate<? super T> predicate) {
    if (predicate.test(this.value)) {
      return Optional.of(this.value);
    }

    return Optional.empty();
  }

  public <U> Optional<U> map(Function<? super T, ? extends U> mapper) {
    if (this.value != null) {
      return Optional.ofNullable(mapper.apply(this.value));
    }

    return Optional.empty();
  }

  public <U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper) {
    if (this.value != null) {
      return mapper.apply(this.value);
    }

    return Optional.empty();
  }

  public T orElseGet(Supplier<? extends T> other) throws NoSuchElementException {
    if (this.value != null) {
      return this.value;
    }

    throw new NoSuchElementException();
  }
}