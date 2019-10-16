# Recitation 06

## 1

- takes in int n
- returns a IntStream containing first n omega numbers

- ith omega number is the number of distinct prime factors for the number i

Stream

- Stream is lazily evaluated

## 4

- (c) Use functional interface
  - since interface can just use arrow expression

## 5

```java
g(x, y) = h(x)(y);

interface F3<T> extends Function<T, Function<T, Function<T, T>>> {}

BiFunction<Integer, Integer, Integer> g = (x, y) -> x + y;

F3<Integer> h = x -> y -> g.apply(x, y);

g.apply(10, 10);
h.apply(10).apply(10);
```
