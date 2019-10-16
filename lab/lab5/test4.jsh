jshell -q *Exception.java  Food.java Chocolate.java Tuna.java  Cheese.java Animal.java Dog.java Cat.java Farm.java
Farm f = new Farm();
f.runInstruction("new cat Kitty 1 grey");
f.runInstruction("new dog Doggie 3 woof");
f.runInstruction("add chocolate Cadbury dark");
f.runInstruction("add cheese Magnolia");
f.runInstruction("add tuna Ayam");
f;
try {
        f.runInstruction("new duck");
    } catch (IllegalInstructionException e) {
        System.err.println(e.getMessage());
    }
try {
        f.runInstruction("add food");
    } catch (IllegalInstructionException e) {
        System.err.println(e.getMessage());
    }

 try {
        f.runInstruction("new dog Doggie 3");
    } catch (IllegalInstructionException e) {
        System.err.println(e.getMessage());
    }
 try {
        f.runInstruction("add chocolate Cadbury");
    } catch (IllegalInstructionException e) {
        System.err.println(e.getMessage());
    }
f.runInstruction("eat");
f.runInstruction("greet");
 try {
        f.runInstruction("meow");
    } catch (IllegalInstructionException e) {
        System.err.println(e.getMessage());
    }
