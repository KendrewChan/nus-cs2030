 # Lecture 7

## The case against null

```java
static getCircle () {
    if (x) {
        return y;
    } else {
        return null;
    }
}
```

Problem:

- Creating a circle via getCircle may return a circle or nothing
- Chaining method call may cause NullPointerException

Solution:

- Need an object with connotations of mayb that wraps around another object of type T

```java
class Maybe<T> {
    private final T t;

    public Maybe() {
        t = null;
    }

    public Maybe(T t) {
        this.t = t;
    }

    @Override String toString() {
        return String.format("Maybe[%s]", (t == null) ? "empty" : t);
    }
}
```

Problem:

- will still throw error of cannot find symbol `contains` in Maybe

Solution:

- define `ifPresent method` in Maybe that takes an action as argument to be applied on the object encased wihin Maybe
- `Circle.getCircle(new Point(0, 0), 1).ifPresent(contains(new Point(0, 0)))`
- Need to pass an action to the method
  - e.g. in `List.sort(Comparable<?s super E> comp)

```java
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

  @Override
  public String toString() {
      return String.format("Maybe[%s]", (t == null) ? "empty" : t);
  }
}


public interface Actionable<T> {
  // Contract to do something on t
  public void doit(T t);
}

Actionable<Circle> action = new Actionable<>() {
    public void doit(Circle c) {
        System.out.println(c.contains(new Point(0,0)));
    }
}

Circle.getCircle(new Point(0,0), 1).ifPresent(action);
```

- if invalid point
  - then won't print anything

1. Circle is in the constructor of Maybe `Maybe<Circle>`, set as property t
2. when ifPresent if maybe is called action.doit(circle) is called if it is not nulll
3. Circle is called in `doit` to check contains

## Lambda

Inner Anonymous class

- no constructor
- only argument and action in method is nesssary
- class name Actionable does not add value
- if there is only a single abstract method in the class, then the method name `doit` doesn't add value

Solution:

- A lambda expression can be used instead as a shorthand
  - `Circle.getCircle(new Point(1, 1), 1).ifPresent(x -> System.out.println(x))`

**Syntax:**

- `(params) -> { statement }`
- if only one param: `x -> 2 * x`

**Possibilities:**

- methods can now be treated as values
  - assign lambdas to variables
  - pass lambdaas as arguments to other methods
  - return lambdas from methods
  - e.g. `Actionable<Circle> action = c -> System.out.println(c);

| Java     | Examples here |
| -------- | ------------- |
| Optional | Maybe         |
| Consumer | Actionable    |
| Function | Mappable      |

## Local Class and Variable Capture

- Lambdas and anonymous classes declared inside a method are called local classes
- Local class is scoped within the methods
  - has access to the variables of the enclosing method/class
  - local class makes a copy of these variables insite itself

```java
static boolean foo() {
    boolean flag = false;
    Circle.getCircle(new Point(0, 0), 2)
          .ifPresent(x -> flag = x.contains(new Point(1, 1));)
}
```

- cannot change flag
- but can use flag

Elaboration:

- java memory model involving variable capture

### map vs flatMap

- flatMap

  - if a value is present, returns the result of applying the given Optional bearing mapping function to the value, otherwise returns an empty Optional

- map
  - If a value is present, returns an Optional describing (as if by ofNullable(T)) the result of applying the given mapping function to the value, otherwise returns an empty Optional.
