# Rec04

## 1

**Why override `equals`?**

**if dont' override:**

```java
list => [(1,1), (1,1), (2,1)]
list.indexOf(new Point(2, 1))
=> -1

BUT

new Point(2,1).equals(new Point(2,1))
=> true
```

- indexOf is calling the Object equals
- need to override the Object equals
- compare by reference

```java
@Override
public boolean equals(Object obj) {}
```

**(a):**

`true`
`true`
`true`
`[(1.0, 1.0), (1.0, 1.0)]`

**(b):**

- Have to override the `hashCode()` method

```java
@Override
public int hashCode() {
  return Arrays.hashCode(new double[]{this.x, this.y});
}
```

- for sets check the hashCode first then, check equals
  - compare p.hashCode(), q.hashCode()
  - check with equals after that
- for lists, they just check with equals

## 2

(a)

- forEachRemaining is used to iterate through each fo the elements

## 3

`24`

1. g()
2. print "2"
3. f()
4. Exception thrown in f
5. Exception thrown in g
6. Exception thrown in main
7. Exeption caught
8. print "4"

## 5

```java
B b = new B();
b.f();
```

## Questions

- Why using a random hashCode will work?
- If override hashCode then still need to override equals
