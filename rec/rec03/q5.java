class Main {
  public static <T extends Comparable<T>> T max3(T[] arr) {
    T max = arr[0];

    if (arr[1].compareTo(max) > 0) {
      max = arr[1];
    }
    if (arr[2].compareTo(max) > 0) {
      max = arr[2];
    }
    return max;
  }
}
