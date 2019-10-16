class Cat extends Animal {
    private String color;
    public boolean isLazy;

    public Cat(String name, int appetite, String color) {
        super(name, appetite, "cat");

        this.isLazy = false;
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    public void setIsLazy(boolean isLazy) {
        this.isLazy = isLazy;
    }

    @Override
    public void eat(Food food) throws CannotEatException {
        if (food.getName() == "cheese") {
            throw new CannotEatException(super.getEatExceptionMessage(food));
        } else {
            super.eat(food);
        }
    }

    @Override
    public void greet() {
        String prtString = toString();

        if (this.isLazy) {
           prtString += " is lazy";
           setIsLazy(false);
        } else {
           prtString += " says meow and gets lazy"; 
           setIsLazy(true);
        }

        System.out.println(prtString); 
    }

    @Override
    public String toString() {
        return String.format("%s(%s)", super.getName(), this.color);
    }
}
