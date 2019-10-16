# 03-09-19

## Lecture 4

- Interface as abstraction barrier
- Addtional forms of inheritance
  - Abstract class
  - Interface
- Polymorphism with interfaces

### Abstract Class

- have abstract methods
- & concrete implementation
- cannot be instantiated
- used as base class
- subclasses of abstract class must complete definition of abstract methods

```java
public abstract class Shape {
  public abstract double getArea();

  public abstract double getPerimeter();
}
```

```t
Shape ____
|         |
Circle   Rect
```

### Single Responsibility Principle

`A class should have only one reason to change.`

- Downside of abstract classes
  - Single inheritance only for classes in java

### Program design with contracts

- A class can implement many interfaces
- example
  - can return area, perimeter
  - can be scaled
  - can be printed

```java
public class Circle implements Shape, Scalable, Printable {}
```

```t
Scalable
⇡
Circle
```

**implements**

- is-a relationship towards a non-concrete super class
- can-do relationship
- common naming
  - `<something>able`
  - e.g. `Printable`

```java
Shape[] shapes = {new Circle(2)}
shapes[0].getArea(); // works
shapes[0].print(); // symbol not found (as Shape does not have print method)
((Printable) shapes[0]).print(); // works
```

`((Printable) shapes[0]).getArea();`

- Doesn't get area?

### Polymorphic Shape Objects

- Supports polymorphism as well
- Case the object to respective interfaces
- Can extend a new shape without modifying the client's implementation (Open-Closed Principle)
  - Open for extension
  - Client's implementation doesn't change (Closed for modification)

### Interface Segragation Principle

`No client should be forced to depend on methods it does not use.`

```java
public interface MummysBoy {
  public String hi();
}
```

```java
public class Me implements MummysBoy {
  @Override
  public String hi() {
    return "Hi mum... :)";
  }

  public String hey() {
    return "Hey babe... :x";
  }

  public String supp() {
    return "Supp dudes... :p";
  }
}
```

```java
MummysBoy me = new Me();

me.hi(); // Only this is possible

me.hey() // Not Possible
```

- Basically hiding methods of a class

### Dependency Inversion Principle

`High level modules should depend on low-level modules. Both should depend on abstractions. Abstractions should not depend on details. Details should depend on abstractions.`

- Abstractions: interface
- Details: concrete implementation
- Program to an interface, not an implementation
- Interface is the contract

High Level
↓
Abstractions
↑
Low Level

### Programming to an Interface

Concrete - Abstract - Interface

- Concrete class: Actual Implementation
- Interface: Contract that specifies the abstraction that its implementer should implement
- Abstract class: Trade of between the two (partial implementation of the contact)
- Benefits are maintainability, extensibility and testability

### Stubbing

```java
static Circle createCircle(Point p, Point q, double radius) {
  double distPQ = p.distanceTo(q);
  if (distPQ < 2 * radius + 1E-15 && distPQ > 0) {
    Point c = p.midPoint(q);
    double cp = Math.sqrt(Math.pow(radius, 2) - Math.pow(p.distanceTo(c), 2));
    double theta = p.angleTo(q0);
    return Cricle.getCircle(c.moveTo(theta + Math.PI / 2, cp), radius);
  }

  return null;
}
```

- Dependency injection via inheritance: PointStub is-a Point

### Preventing Inheritance and Overriding

- final keyword to explicitly prevent inheritance

```java
public final class Circle {}
```

- final for methods to prevent overriding

```java
public final double getArea() {}
```

### Summmary

- Know how to define concrete/abstract classes or an interface
- Program to an interface

### Notes

- To run assertions in jshell
  - Type `jshell -R -ea <filename>`

