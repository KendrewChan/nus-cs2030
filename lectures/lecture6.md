# 18 Sep

## Lecture 6

### `static`

- can be used in the declaration of a field, method, clock or class
- static field is `class-level member` declared to be shared by all objects of the class
  - use for aggregated data
    - e.g. keep count of how many objects are created for the class
  - use for constants
    - `static double final PI = 3.14`

```java
class A {
  static int x = 0;
}

A.x; // 0
A a = null;
a.x; // 0
```

**Static Methods:**

- belongs to the class instead of an object
  - no overriding as static methods resolved at compile time

**Static block:**

- to intialize static fields
- e.g.

```java
class MyColors {
  static List<Color> colors = new ArrayList<>();
  static {
    colors.add(Color.BLUE);
  }
}
```

### Nested class

- Encapsulation
  - non-static
    - can access all members of the enclosing class
  - s             tatic
    - can only access static emmebers of enclosing class
    - top-level class cannot be made static

### Java Memory Model

- 3 areas of memory model

**Stack:**

- LIFO stack for storing activation records of _method calls_
- method local variables are stored here

**Heap:**

- for storing Java object upon invoking `new`
- garbage collection is done here

**Non-heap:** (k.a. metaspace since Java 8)

- for storing loaded classes, and other meta data
- _static fields are stored here_

### `main(String[] args)`

- allows command line arguments to be passed in

### Error Handling Code

**`throws` Exception out of a method:**

- `try catch` block
- can catch multiple exceptions

```java
catch (InputMismatchException | NoSuchElementException err) {
      System.err.println(err);
}
```

- Pipe means either errors

### Types of Exceptions

- Checked exceptions
  - one that a programmer can anticipated
- Unchecked exception is one that is unanticipated
  - e.g. NullPointerException

### `throw` an Exception

```java
public Circle(Point p, Point q, double radius) {
  if (p.distanceTo(q) > 2 * radius) {
    throw new IllegalArgumentException("Input points are too far apart");
  }
  // ...
}
```

### Generating Exception

- Can generate exception with a subclass

```java
class IllegalCircleException extends IllegalArgumentException {}
```

**Note:**

- when overriding a method that throws a checked exception, the overriding method must throw only the same or more specific exception
- avoid catching `Exception`
  - catching everything is a bit useless
- hanle exceptions at the appropriate abstraction level, do not just throw and break the abstraction barrier

```java
// 🔴 Bad
public void m2() throws E2 {
  // set up resources
  m3();
  // clean up resources
}

// ✅ Good
public void m2() throws E2 {
  try {
    // set up resources
    m3();
  } catch (E2 e) {
    throw e;
  } finally {
    // clean up resources
  }
}
```

### Assertions

- preconditions: assertions about a program's state when a program is invoked
- postconditions: assertions about a program's state when the program is finished
- `-ea` flag tells the JVM to enable assertions
- for a more meaningful message, replace the aseertion with
  - `assert distance >= 0: this.toString() + "" + q.toString()`

```java
public double distanceTo(Point q) {
  double distance = Math.sqrt(Math.pow(dx (q)), 2) + Math.pow(dy(q),2);
  assert distance >= 0;
  return distance;
}
```

### Enumeration

```java
enum Color {
  BLACK, WHITE, RED
}

Color color = Color.BLUE;
```

- enum is a special type of class used for defining constants
- each constant of an enum type is an instance of the enum class and is a field declared with public static final
- enum can have constructors, fields and methods, just like a class

### Access Modifiers

| modifier  | desc                                |
| --------- | ----------------------------------- |
| private   | visible to class                    |
| default   | visible to package                  |
| protected | visible to package and all subclass |
| public    | visible to the world                |

| modifier               | private | default | protected | public |
| ---------------------- | ------- | ------- | --------- | ------ |
| inside class           | Y       | Y       | Y         | Y      |
| same package class     | N       | Y       | Y         | Y      |
| same package subclass  | N       | Y       | Y         | Y      |
| other package class    | N       | N       | N         | Y      |
| other package subclass | N       | N       | Y         | Y      |

### Packages

- `javac -d . *.java`
  - compiles all the java files in the folder to a single Main class
  - other classes are compiled and sent to another folder

### Javadoc

- to generate javadoc
  - `javadoc *.java -d docs`
    - document all files and send to docs directory

### Note

- Anything from java.lang don't need to import
- Peer learning
  - from piazza
  - from task creation in codecrunch
