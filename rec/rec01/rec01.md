# Rec 01

## Q1

### Access Modifiers

| Modifier    | Class | Package | Subclass | World |
| ----------- | ----- | ------- | -------- | ----- |
| public      | Y     | Y       | Y        | Y     |
| protected   | Y     | Y       | Y        | N     |
| no modifier | Y     | Y       | N        | N     |
| private     | Y     | N       | N        | N     |

### `private` modifier

- Abstraction barrier
  - Protects client from knowing internals

```java
class P {
  private int x;

  public void changeSelf() {
    this.x = 1;
  }

  public void changeAnother(P p) {
    p.x = 1; // is ok
  }
}
```

- Change another is within abstraction barriers
  - Hence `changeAnother` works
- Notion of private is not within instances
  - 2 instances of same class can access each other's private members
- Rather it is between classes

## Q2

**(a) equals(Object) called**

- Circle
  - radius, center
  - Object
    - equals(Object)
  - equals(Object) (overrides equals method of Object)
  - equlas(Circle) (not overriding anything)

```java
Object o1 = c1;

o1.radius = 2; // Symbol radius not found
```

- Compiler assumes radius is not in Object class

**(b) equals(Object) called**
**(c) equals(Object) called**
**(d) equals(Object) called**

**(e) Circle::equals(Object)**
**(f) Circle::equals(Circle)**
**(g) Circle::equals(Circle)**
**(h) Circle::equals(Object)**

## Q5

### (b)

- You are changing a Square to a Rectangle

### (c)

- Violating LSP
- ∴ Should not inherit Square from Rectangle

## Questions

- Q2 so still have radius and center?
- Why put public as modifiter of the class?
