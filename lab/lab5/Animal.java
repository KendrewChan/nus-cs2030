class Animal {
    private String name;
    private int appetite;
    private String species;

    public Animal(String name, int appetite, String species) {
        this.name = name;
        this.appetite = appetite;
        this.species = species;
    }

    public String getName() {
        return this.name;
    }

    public int getAppetite() {
        return this.appetite;
    }

    public boolean isFull() {
        return this.appetite == 0;
    }

    public String getSpecies() {
        return this.species;
    }

    public void greet() {
        System.out.println("");
    }

    public String getEatMessage(Food food) {
       return String.format("%s eats %s", toString(), food);
    }

    public String getEatExceptionMessage(Food food) {
        return String.format("%s cannot eat %s", this, food);
    }

    public String getFullExceptionMessage(Food food) {
        String retString = getEatExceptionMessage(food) + " as it is full";

        return retString;
    }

    public void eat(Food food) throws CannotEatException {
        if (this.appetite == 0) {
            throw new CannotEatException(getFullExceptionMessage(food));
        } else {
            this.appetite -= 1;
            System.out.println(getEatMessage(food));
        }
    }

    public void eatAny(Food food) throws CannotEatException {
        if (this.appetite == 0) {
            throw new CannotEatException(getFullExceptionMessage(food));
        } else {
            this.appetite -= 1;
            System.out.println(getEatMessage(food));
        }
    }

    @Override
    public String toString() {
        return getName();
    }
}