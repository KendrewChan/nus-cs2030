# Recitation 06 
## 1

- takes in int n
- returns a IntStream containing first n omega numbers

- ith omega number is the number of distinct prime factors for the number i

## 4

(c)

- Reimplementation might need to call again?

## 5

```java
g(x, y) = h(x)(y);

BiFunction<Integer, Integer, Integer> g = (x, y) -> x + y;
Function<Integer, Function<Integer, Integer>> h = x -> y -> g.apply(x, y);

g.apply(10, 10);
h.apply(10).apply(10);
```
