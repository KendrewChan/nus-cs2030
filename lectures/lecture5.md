# 13 Sep

## Lecture 5

- Generics and Variance of Types

### Abstraction Principle

- Using a specfic type for a class results in code duplication

- Problem with using Object as a replacement of specific type
  - Items passed become typecasted to an Object
  - runtime error ClassCastException occurs
  - To take out the Object and use the methods then have to add check what instance it is

### Generic Type

- parametric polymorphism
- type or method to operate on objects of various types while providing compile-time type safety
- For backward compatibility, Java implements generic typing via type erasure
  - Type argument is erased during compile time
  - Compiler will serve a warning if type is unsafe

```java
Queue<Circle> circleQueue = circleQueue = new Queue<Circle>();
```

```java
class Queue<T> {
  public Queue(int size) {

  }

  public void add(T elem) {

  }

  public T remove() {
    @SuppressWarnings("unchecked") // Use this to supress
    return (T) element;
  }
}

Queue<Circle> queue = new Queue<Circle>(3);

queue.add(new Circle(1));
queue.add(new Point(1)); // Error

Circle  = queue.remove(c);
```

### Auto-boxing and Unboxing

- Cannot use primitives as reference types

```java
Queue<int> queueIntWrong = new Queue<int>(3);
Queue<Integer> queueIntCorrect = new Queue<Integer>(3);

int y = 1;
y instanceof Integer // false

new Integer(1) instanceof Integer // true

Queue<Integer> q = new Queue<Integer>(3)
// Auto-boxing (don't need to specify type here)
q.add(1);
// Unboxing (don't need to use Integer)
int a = q.remove() // 1
```

- Integer is a wrapper class for int
- For primitives don't need to auto specify the type

### Variance of Types

- LSP: If S is a subtype of T, then object of type T can be replaced with that of type S without changing the desirable property of the program
- Notation: `S <: T`

| Type      | Notation                                  | Example                                               |
| --------- | ----------------------------------------- | ----------------------------------------------------- |
| Covariant | S[] <: T[]                                | Burger[] = new CheeseBurger[10]                       |
| Covariant | `S<E> <: T<E>`                            | `List<Burger> = new ArrayList<Burger>()`              |
| Invariant | Neither `C<S> <: C<T>` nor `C<T> <: C<S>` | `ArrayList<Burger> b = new ArrayList<CheeseBurger>()` |

- ArrayList is a subtype of List
- Burger is a subtype of CheeseBurger
- Note: `ArrayList<FastFood> b = new ArrayList<>()`
  - Don't need specify type for the second part as `new ArrayList<FastFood>()` is the only possible for statement to work

### Wildcards

- `?`
- e.g. `ArrayList<?> anyList = new ArrayList<Circle>();`

#### Bounded Wilcards

**Upper-Bounded:**

```java
static void getBurger(List<? extends Burger> burgers) {
  Burger b = burgers.get(0);
}

List<Burger> cheeseBurgers = new ArrayList<CheeseBurger>();

cheeseBurgers.add(new Burger()));

getBurger(cheeseBurgers)
```

- `? extends Burger`
  - accepts Burger and anything that is a subclass of Burger
- if `S<: T`, then `C<S> <: C<? extends T>`

**Lower-Bounded:**

```java
static void putBurger(List<? super Burger> consumer) {
  Burger b = burgers.add(new Burger());
}

List<Burger> cheeseBurgers = new ArrayList<CheeseBurger>();
putBurger(cheeseBurgers) // Cannot 🔴

List<Burger> fastFood = new ArrayList<Burger>();
putBurger(fastFood) // Can ✅
```

- Lower bound
- can accept consumer that is Burger or parent class
- ? super is contravariant
  - if `S <: T` then `C<T> :> C<? super S>`

**Question:**

- What about `getAndPutBurger(List<Burger> burgers)`
  - invariant
  - only can accept Burger

### Get-Put Principle

- Covariant: use extends to get items from a producer
- Contravariant: use super to put items into a consumer
- PECS: Producer Extends Consumer Super

### Generic Types in methods

```java
public <T extends Comparable<T>> T max3(T[] nums) {
  T max = nums[0];

  if (nums[1].compareTo(max) > 0) {
    max = nums[1];
  }

  if (nums[2].compareTo(max) > 0) {
    max = nums[2];
  }

  return max;
}
```

- T is anything that implements comparable (with method compareTo)

### Comparator

- for sorting

```java
public class NComparator implements Comparator<Integer> {
  @Override
  public int compare(Integer s1, Integer s2) {
    return s1 - s2;
  }
}
```

- Anonymous inner class

```java
nums.sort(new Comparator<Integer>()) {
  @Override
  public int compare(Integer s1, Integer s2) {
    return s1 - s2;
  }
}
```

- 
