# Recitation 03

## 1

(a) Valid

- You can assign a subtype to a supertype
- List<?>
  - `?` means wildcard
  - `List<String>` is a subtype of `List<?>`
    - `ArrayList<String>` is subtype of `List<String>`

(b) Invalid

- List is an abstract class, it cannot be instantiated

- `List<? super Integer>`
  - can be Object, Number, Integer
    ⇑
  - `List<Object>`
    ⇑
    - `ArrayList<Object>`

(c) Valid

(d) Invalid

- Because you used int which is a primitive type

(e) Valid

- But warning: `Unchecked conversion`
  - No compile error for backward compatability

## 2

`double`: 2049 ms
`Double`: 8546 ms

- It takes a longer timer to perform operation on a `Double` class over a `double` primitive

## 3

- `true`
- `false`

- Small values of integers are cached
- `Integer.valueOf(int) return the same Integer object for -128 < input < 127`
- Hence for small values, it's compare by value
- For larger values, it's compare by reference, hence false

## 4

(a) Name clash

- Java removes generic type before compilation. (Type erasue)
- Hence `List<Integer>` & `List<String>` are seen as the same type.
- Thus error.

(b) non-static type cannot be referenced from a static context

- Because static variables is associated with a class
- Using static will result in no type for the class

(c)

```c
2
2
```

- Each time class C is contructed, b property adds by 1
- Hence two 2s printed

## 5

```t
Comparable<FastFood> <-     FastFood          FastFood[]
        |
        v                      ↑
Comparable<? super Burger>   Burger
(Contravariance)
```

(a)

```java
public static <T> Comparable<T> max3(Comparable<T>[] arr)
```

(b)

```java
public static <T> T max3(Comparable<T>[] arr)
```

(c)

```java
public static Comparable max3(Comparable[] arr)
```

**What if parameter is `List<T>` instead?**

```java
public static <T> Comparable<T> max3(List<T> arr)
```

```java
class FastFood implements Comparable<FastFood> {}

class Burger extends FastFood {}

List<FastFood> lst = Arrays.asList(new FastFood(), new FastFood(), new FastFood());
max3(list); // FastFood is a subtype of Comparable<FastFood>
```

```java
List<Burger> burgers = Arrays.asList(new Burger(), new Burger(), new Burger());
max3(list); // Fail: Burger is not a subtype of Comaparable<Burger>

// List<Burger> is a subtype of List<? extends FastFood>
```

```java
public static <T extends Comparable<? super T>> T max3(List<? extends T> arr) {}
```

- Is Burger a subtype of Comparable<? super Burger>?

- PECS: producer extends, consumer super

## 6

(a) Will compile

```java
1
2
3
```

(b) Will compile

```java
1
2
3
```

(c) Will not compile (Type wrong)

(d) Will compile

```java
1.0
2.0
3.0
```

(e) Will compile

```java
5
4
3
2
1
```

## Notes:

- Type erasure in java
- Java removes generic type before compilation
