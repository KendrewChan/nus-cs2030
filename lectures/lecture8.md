# Lecture 8

- Functional and declarative programming
- Function
  - pure function with no side effects
  - functor and monad
- Internal versus external iteration
- Stream concepts using IntStream and Stream<T>
  - Stream elements and pipelines
  - Intermediate and terminal operations
  - Stateless vs stateful operations
  - lazy and eager evaluations
  - map and flatMap
  - reducation
  - infinite streams

## Function

- a mapping from a set of inputs to a set of outputs
- every input maps to exactly one output
- multiple inputs can map to the same output
- not all values in the co-domain are mapped

## Pure Function

- One that takes in arguments and returns a deterministic value, with no other side effects
  - Program output an dinput
  - throwing exceptions
  - modifying external state
- Absence of side effects is a necessary condition for referential tranparency

**Examples of impure functions:**

```java
int q(int x, int y) {
    return x / y;
}
```

- impure b/c it may cause a ZeroDivisionException

```java
int r(int i) {
    return this.count + 1;
}
```

- dependent on value of count
  - doesn't return a deterministic value

```java
void s(List<Integer> queue, int i) {
    queue.add(i);
}
```

- mutating list is a side effect

## Higher Order Functions

- passing functions into another function

```java
Function<Integer, Integer> f = x -> x + 1;
```

- `f.apply(3) # returns 4`
- can even be used a cross-barrier state manipulator

```java
List<Integer> list = Arrays.asList(1, 2, 3);

void doSomething(Function<Integer, Integer> f) {
    for (int i = 0; i < list.size(); i ++)  {
        list.set(i, f.apply(list.get(i)));
    }
}
```

## Pure Functions?

```java
class IList<T> {
    private final List<T> list;

    IList(List<T> list) {
        this.list = new ArrayList<>();
        for (T item : list) {
            this.list.add(item);
        }
    }

    <R> IList<R> map(Function <T, R> f) {
        ArrayList<R> newList = new ArrayList<>();
        for (T item : list) {
            newLIst.add(f.apply(item));
        }

        return new IList<R>(newList);
    }

    List<T> get() {
        return list;
    }
}
```

- Side effects should be handled within a context
- Just like missing values are handled within Optional's context, IList handles immutable list mapping withins it's own context

## Functor

- map in both Optional and IList implements the following:

```java
interface Functor<T> {
    return <R> Functor<R> f(Function<T, R> func);
}
```

- A functor must als obey the 2 funtor laws
  - if func is identity function x -> x, then it should not change the functor
  - if func is a composition g o h `g(h(x))`, then the resulting factor should be the same as calling f with h and then with g

## Monad

```java
interface Monad<T> {
    public Monad<T> of (T value);
    public <R> Monad<R> f(Function<T, Monad<R>> func);
}
```

- mapping works with functors that encloses the same type
- monad laws
  - left identity: `Monad.of(x).flatMap(f) ≡ f.apply(x)`
  - right identity: `monad.flatMap(x => Monad.of(x)) ≡ monad`
  - Associative: `monad.flatMap(f).flatMap(g) ≡ mond.flatMap(x -> f.apply(x).flatMap(g))`

## External Iteration

```java
int sum = 0;
for (int x = 0; x < 10; x++) {
  sum += x;
}
```

- An imperative loop specifies how to loop and sum
- Variables i and sum mutates at each iteration
- Errors could be introduced

## Internal Iteration

- Declarative approach that specifies what to do

```java
int sum = IntStream.rangeClose(1, 10).sum();
```

- sum is assigned with the result of a stream pipeline
- a stream is a sequence of elements on which tasks are performed; stream pipeline moves the stream's elements through a sequence of tasks

## Streams and Pipelines

- stream pipeline starts with a data source
- static method IntStream.rangeClosed(1, 10) creates an IntStream containing the ordered sequence 1,2,...,9,10
- reductions are terminal operations that initiate a stream pipeline's processing so as to produce a result

## Mapping

- most stream pipelines contains intermediate operations that specify tasks to perform on a stream's elements before a terminal operation produces a result
- mapping is a common intermediate operation
- using map
- using flatMap

`int sum = IntStream.rangeClosed(1,5).flatMap(x -> IntStream.rangeClosed(1,x)).sum()`

## Intermediate and Terminal Operations

- Intermediate operations like map use lazy evaluation
- doesn't perform any operations on stream's elements until a terminal operation is called
- terminal operation use eager evaluation, i.e. perform the requested operation when they are called

## Stateles vs Stateful Operations

- Intermediate stream operations like filter and map are stateless, i.e. processing one stream element does not depend on other stream elements
- There are however stateful intermediate operations that depened on the current state

## Method references

- lambda that calls anohter methods can replaced with that method's name, e.g. forEach terminal

```java
IntStream.rangeClosed(1, 10).forEach(x -> System.out.println(x));

// Method reference
IntStream.rangeClosed(1, 10).forEach(System.out::println);
```

- Types of method references
  - reference to static method
  - reference to an instance method
  - reference to a constructor

## Boolean Terminal Operations

- useful terminal operations that return a boolean results
  - noneMatch returns true if none of the elements pass the given predicate
  - allMatch returns true if every elements passs

## Stream Elements

- Each intermediate operation results in a new stream
- Each stream is an object representing the processsing steps that have been specified up to that point in the pipelin
  - chaining last stream object contains all processing steps to perform on each stream elememnt
- Stream elements within a stream can only be consumed once
  - cannot iterate through stream multiple times

```java
IntStream
  .range(0, 10)
  .filter(x -> {
    System.out.println(x);
    return x % 2 == 0;
  })
  .map(x -> {
    System.out.println(x);
    return x;
  })
  .sum()

// returns

// 0 (filter)
// 0 (map)
// 1 (filter) doesnt get map if doesn't pass the filter
// 2 (filter)
// 2
// 3
// 4
// 4
// 5
// 6
// 6
// 7
// 8
// 8
// 9

```

**`reduce`:**

- terminal operations are specific implementations of reduce
- e.g. in place of sum

```java
IntStream.of(values).reduce(0, (x, y) -> x + y);
```

## Infinite Stream

- Lazy evaluation allows us to work with infinite streams that represent an infinite number of elements

e.g.

```java
IntStream.iterate(2, x -> x + 1).filter(x -> isPrime.apply(x)).limit(500).forEach(System.out::println);
```

## Stream<T>

```java
Stream<Circle> circles = IntStream.rangeClosed(1,3).mapToObj(Circle::new);
```

- There equivalent intermediate operations in Stream
- Functional interfaces that stream operations take in:
  - when you want to inspect a stream element
    - <? super T>
    - e.g. Predicate<T> in `filter(Predicate<? super T> predicate)`
  - when you want to produce
    - <? extends T>

## Closure

- A lambda expression not only stores the function to invoke, but also data from the enclosing environment

**Example:**

```java
class DelayedData {
  public int index;
  private int input;

  public DelayedData(int index, int input) {
    this.index = index;
    this.input = input;
  }

  public String toString() {
    return index + " :" + input;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    DelayedData[] data = new DelayedData[5];

    for (int i = 0; i < data.length; i++) {
      data[i] = new DelayedData(i, nextInt());
    }

    Stream.of(data).filter(x -> x.index % 2 == 0).forEach(System.out::println);
  }
}
```

- in this case need wait for all 5 values filled
- waiting at nextInt()

```java
class DelayedData {
  public int index;
  private Supplier<Integer> input;

  public DelayedData(int index, Supplier<Integer> input) {
    this.index = index;
    this.input = input;
  }

  public String toString() {
    return index + " :" + input.get();
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    DelayedData[] data = new DelayedData[5];

    for (int i = 0; i < data.length; i++) {
      data[i] = new DelayedData(i, () -> sc.nextInt());
    }

    Stream.of(data).filter(x -> x.index % 2 == 0).forEach(System.out::println);
  }
}
```

- By passing in a lambda to DelayedData instead of just a int, program goes directly to last line instead of waiting for the next int
  - since printing requires the values, the program is actually waiting at input.get() in the toString method
  - Hence when next int is supplied, it will print immediately

`OO makes code understandable by encapsulating moving parts. FP makes code understandable by minimizing moving .`
