public interface Actionable<T> {
  // Contract to do something on t
  public void doit(T t);
}