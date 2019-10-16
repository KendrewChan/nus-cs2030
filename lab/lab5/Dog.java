class Dog extends Animal {
    private int greetCounter;
    private String sound;

    public Dog(String name, int appetite, String sound) {
        super(name, appetite, "dog");

        this.sound = sound;
        this.greetCounter = 0;
    }

    public String getSound() {
        return this.sound;
    }

    public void incGreetCounter() {
        this.greetCounter++;
    }

    @Override
    public void eat(Food food) throws CannotEatException {
        if (food.getName() == "chocolate") {
            throw new CannotEatException(super.getEatExceptionMessage(food));
        } else {
            super.eat(food);
        }
    }

    @Override
    public void greet() {
        String prtString = String.format("%s says ", super.toString());

        for (int i = 0; i <= this.greetCounter; i++) {
            prtString += this.sound;
        }

        System.out.println(prtString);

        incGreetCounter();
    }
}
