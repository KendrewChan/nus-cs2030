# Recitation 05

Closure

- abstraction barrier
  - implementer 
    - hides details
  - client

## 2

```java
Function<String, Integer> findFirstSpace = new Function<>() {
  public Integer apply(String str) {
    return str.indexOf(' ');
  }
}

findFirstSpace.apply("string ");
```

## 3

(a) true
(b) true
(c) false

- b/c `a.bar(f).bar(g)
  - will call .bar twice no matter if A isPostive or not
- whereas `b.bar(x -> f.apply(x).bar(g))`
  - will only call bar once if this.x !isPositive

## 4

- Answers in `Optional.java`
