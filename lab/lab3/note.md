# Lab 3

1. Design CLoneable interface and implement Face class
2. Rubik

## Level 1

- Cloneable interface
- Enforces the definition of lcone method
- Must return a new object

- Face
  - Constuctor
    - take in int[][]
    - return toIntArray returns deep copy
- turn face

## Level 2 Rubik

- Implements Cloneable
- input order: top left front right down back
- Store as Face[]
- Maybe overload the constructor
- toString()

## Level 3 SideViewable

- Cuble must be able to be viewed from any of its 6 slides
- View the cube from another direction
  - rightView, leftView, topView
- rightView is leftView x 3

## Level 4

- Takes in a Rubik Object
- To allow turning of any face, there is RubikUp, RubikRight, etc

## Level 5

- Bringing it all together

## Level 6

- Clone and Sideviewable interface must do propely

## Tips

- Make use of clone when return new object
  - Deepy copy
- In Rubik,
  - Write your own private methods
  - Might want to define static final int UP = 0
    - e.g. faces[UP], etc
  - Or can just use enum

## Comments

- Don't declare methods you don't use

## For PE

- day is on Monday
