# Recitation 02

## 1

(a)

```java
public interface Shape {
  public double getArea();
}

public interface Printable {
  public void print();
}

// whether we can invoke a method depends on the compile-time type
Circle c = new Circle(new Point(0,0), 10);
Shape s = c; // compile-time type for s is Shape
             // run-time type for s is Circle
Printable p = c;
```

`compile-time type`

- Java is a statically typed language
- Type sticks to the variables

(i) Not allowed. Because interface Shape doesn't have print method.

(ii) Allowed

(iii) Allowed

(iv) Not allowed. Because interface Printable doesn't have getArea method.

(b) It would not work as Circle would only be able to extend one of the classes.

(c) Yes.

But:

- violates Interface Segragation Principle
- violates single responsibility principle (why actually?)

## 2

Yes it would compile.

If we switch then it will not be able to compile.

```t
return type A is not compatible with B
B.java:6: error: method does not override or implement a method from a supertype
```

- When you overwrite, you can only overwrite a subclass not a superclass

```java
void g(A a) {
  A newA = a.mthoed();
  newA.f();
}

g(new B());

class A {
  int x;

  A(int x) {
    this.x = x;
  }

  public A method() {
    return new A(1);
  }
}

class B extends A {
  B(int x) {
    super(x);
  }

  // return type can A or any class that extends A
  // Object cannot
  @Override
  public B method() {
    return ...;
  }
}
```

## 3

```java
class A {}

class B {}

class C {}

class D {}

class AB extends A, B {}

class CD extends C, D {}

class ABCD extends AB, CD {}
```

Why not inherit from multiple parent classes?

- [Diamond of the dead](https://stackoverflow.com/questions/406081/why-should-i-avoid-multiple-inheritance-in-c)

A
|\
B C
| /
D

- Harder to code trace
- Resolving of naming conflicts can be complicated

Why can implement multiple interfaces?

- No chance of diamond of the dead happening
- Interface doesn't contain logic of execution?

## 4

```java
solidCuboid = new Object3D(m, new Cuboid());

class Object3D {
  Material matrial;
  Shape shape;

  getMass() {
    return shape.getVolume() * matrial.getDensity
  }
}

interface Shape {
  getVolume();
}

class Cuboid implements Shape {}
```

## 5

(a)
Runtime error. f method not found.

(b)
No error.

```t
"B f"
"A f"
"A f"
```

(c)

```t
"A f"
"B f"
"A f"
```

(d)
Runtime error. Out of memory because of recursion.

(e)

```t
"B f"
"A f"
```

(f)

```t
"B f"
"B f"
"A f"
"A f"
```

(g)

```t
"A f"
"B f"
```

(h)

```t
"A f"
"B f"
```

(i)

```t
"A f"
"B f"
```

(j)

```t
"A f"
"B f"
"A f"
"B f"
```

(j)

Compilation error. x is not defined.

(l)

Compilation error. Cannot access x as x is private.

(m)

Compilation error. x is not defined.

(n)

```t
1
```

(o)

```t
0
```

## Notes

- jar file
  - zip file of all the classes

## Questions

- Is methods of original class overriden after you typecast?
- For q4, is it really necessary to abstract everything away?
