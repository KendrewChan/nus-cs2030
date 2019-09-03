# Lecture 3

- Substitutability Principle in Object-oriented Design

## Overriding: equals method

- Method in the Object class
- Compartes if 2 object references refer to the same object

`==` operator

- Compares the object address

`.equals` method

- mimics `==` operator

- If points with the same coordinate values are equal, we need to overrid ethe equals method inherited from Object

**String class**

```java
new Point(0, 0).toString() == new Point(0, 0).toString() // false

new Point(0, 0).toString().equals(new Point(0, 0).toString()) // true
```

**Abstraction Principle**

`Where similar functions are carried out by ditinct pieces of code, it is generally benficial to combine them into one by abstracting out various parts`

## Composition

- has-a relationship
  - FilledCircle
    - has a Circle property that points to a circle
    - and a color property

```java
public class FilledCircle {
  private final Circle circle;
  private final Color color;

  public FilledCircle(double radius, Color color) {
    circle = new Circle(radius);
    this.color = color;
  }

  public double getArea() {
    return circle.getArea();
  }

  public double getPerimeter() {
    return circle.getPerimeter();
  }

  public Color getColor() {
    return color;
  }
}
```

## Inheritance

- is-a relationship: FilledCircle is a Circle

```java
public class FilledCircle extends Circle {
  private final Color color;

  public FilledCircle(double radius, Color color) {
    super(radius);
    this.color = color;
  }

  public Color getColor() {
    return color;
  }

  @Override
  public String toString() {
    return super.toString() + ", " + getColor();
  }
}
```

- in jshell, you should type the parent first then the child

## Modeling Inheritance

- Child object wraps around the parent
- `FilledCircle fc = new Circle(1);`
  - not possible since FilledCircle has one more property
- Type-casting a child object to a super class, `e.g. (Circle) filledCircle` refers to the parent object where attributes/methods can be assessed
  - e.g `Circle c = new FilledCircle(1, Color.BLUE);`
    - in this case, c only can have `Circle` methods
    - `c.getColor // A FilledCircle method` will throw error
  - The only exception is overridden methods
    - calling them through the parent or child will invoke the overriding methods
  - Overriden parent method can only be called within the child class via super

## Polymorphism

- Why inheritance?
  - Because of the need to be polymorphic
- Polymorphism means `many forms`

## Static Binding

- Given an array Cirlce[] circles comprising both Circle and FilledCircle objects
- We can do this in static binding

```java
for (Cirlce circle : circles) {
  if (circle instanceof Circle) {
    System.out.println((Circle) circle);
  } else if (circle instanceof FilledCircle) {
    System.out.println((FilledCircle) circle);
  }
}
```

- Static binding occurs during compile time (all information needed to call a specific method can be known at compile time)

## Dynamic Binding

- Fives same output
- Exact type of circle and the exact `toString` method to be overriden, is not known until runtime

```java
for (Circle circle : circles) {
  System.out.println(cirlce);
}
```

- More easily extensible implementations (open-closed principle)
  - `open`: for extensions
  - `closed`: for modifications
    - Client doesn't need to change
  - Simply add a new sub-class of circle that extends the Circle class and overridde the appropriate mthods
  - Does not require client code to be modified

## Method Overloading

- Methods of the same name can co-exist if the signatures are different
  - `signatures: number, order, and type of arguments`
- The method to be called is determined during compile time
- e.g. in constructors

```java
public Circle() {
  this.radius = 1.0;
}

public Circle(double radius) {
  this.radius = radius;
}
```

## Liskov Substitution Principle (LSP)

- Meaning: `If S is subclass of T, then an object of type T can be replaced by that of type S without changing the desirable property of the program`
- Example:
  - If FilledCircle is a subclass of Circle, then everywhere we can expect circles to be used, we can replace a circle with a filled-circle
  - i.e. can use `getArea()` and `getPerimeter()`

**Question: Which is substitutable?**

`Account.java`

```java
public class Account {
  protected final int balance;

  public Account(int balance) {
    this.balance = balance;
  }

  public boolean checkBalance() {
    return this.balance > 0 && this.balance < 100;
  }
}
```

`AccountA.java`

```java
public class AccountA extends Account {
  public AccountA(int balance) {
    super(balance);
  }

  public boolean checkBalance() {
    return this.balance > 0 && this.balance < 10;
  }
}
```

`AccountB.java`

```java
public class AccountB extends Account {
  public AccountB(int balance) {
    super(balance);
  }

  public boolean checkBalance() {
    return this.balance > 0 && this.balance < 1000;
  }
}
```

- `AccountA.java` is not substitutable
  - `0 < x < 10` is subset of `0 < x < 100` (`Account.java`)
- `AccountB.java` is substitutable
  - `0 < x < 1000` is superset of `0 < x < 100` (`Account.java`)

## Notes

- can use `var`
  - type will be inferred (like typescript)
- if no constructor is stated, empty constructor will be auto-generated

## Questions

- For the question on which is substitutable
  - Doesn't the fact of whether the class is substitutable or not depend on what value is passed into the constructor?
