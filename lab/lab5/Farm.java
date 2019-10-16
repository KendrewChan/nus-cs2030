import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

class Farm {
    private List<Animal> animals;
    private List<Food> foods;

    public Farm() {
        this.animals = new ArrayList<>();
        this.foods = new ArrayList<>();
    }
    
    private class AnimalComparator implements Comparator<Animal> {
        public int compare(Animal a, Animal b) {
            return a.getName().compareTo(b.getName());    
        }
    }

    public void addAnimal(String inst) throws IllegalInstructionException {
        String[] instArr = inst.split(" ");
        String species = instArr[0];

        Animal animal = new Animal("", 0, "");

        if (!(species.equals("cat") || species.equals("dog"))) {
            String errString = String.format("%s is not a valid species", species);
            throw new IllegalInstructionException(errString);
        } else if (instArr.length < 4) {
            throw new IllegalInstructionException("Too few arguments");
        } else {
            String name = instArr[1];
            int appetite = Integer.parseInt(instArr[2]);
            String color = instArr[3];
            if (species.equals("cat")) {
                animal = new Cat(name, appetite, color);
            } else if (species.equals("dog")) {
                animal = new Dog(name, appetite, color);
            }
        }

        this.animals.add(animal);
        animals.sort(new AnimalComparator());
        String prtString = String.format("%s was created", animal);
        System.out.println(prtString);
    }

    public void addFood(String inst) throws IllegalInstructionException {
        String[] instArr = inst.split(" ");
        String foodType = instArr[0];

        Food food = new Food("", "");

        List<String> validFoodTypes = new ArrayList<>(List.of("cheese", "tuna", "chocolate"));

        if (!validFoodTypes.contains(foodType)){
            String errString = String.format("%s is not a valid food type", foodType);
            throw new IllegalInstructionException(errString);
        } else {
            try {
                if (foodType.equals("chocolate")) {
                    food = new Chocolate(instArr[1], instArr[2]);
                } else if (foodType.equals("cheese")) {
                    food = new Cheese(instArr[1]);
                } else if (foodType.equals("tuna")) {
                    food = new Tuna(instArr[1]);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalInstructionException("Too few arguments");
            }
        }

        this.foods.add(food);
        String prtString = String.format("%s was added", food);
        System.out.println(prtString);
    }

    public void runAnimalEat(Animal animal) {
        // While food is not empty
        // Eat all the food
        // Iterate through all the food
        List<Food> eatenFood = new ArrayList<>();

        for (int i = 0; i < this.foods.size(); i++) {
            try {
                animal.eatAny(this.foods.get(i));
                eatenFood.add(this.foods.get(i));
            } catch (CannotEatException e) {}
        }

        for (Food food : eatenFood) {
            this.foods.remove(food);
        }
    }

    public void runEat() {
        for (Animal animal : this.animals) {
            runAnimalEat(animal);
        }
    }

    public void runGreet() {
        for (Animal animal : this.animals) {
            animal.greet();
        }
    }

    public void runInstruction(String inst) throws IllegalInstructionException {
        String instArr[] = inst.split(" ", 2);
        try {
            if (instArr[0].equals("new")) {
                addAnimal(instArr[1]);
            } else if (instArr[0].equals("add")) {
                addFood(instArr[1]);
            } else if (instArr[0].equals("eat")) {
                runEat();
            } else if (instArr[0].equals("greet")) {
                runGreet();
            } else {
                String errMessage = String.format("%s is not a valid instruction", inst);
                throw new IllegalInstructionException(errMessage);
            }
        } catch (IllegalInstructionException e) {
            throw e;
        }
    }

    public String toString() {
        String retString = "Animals:\n";
        for (Animal animal : this.animals) {
            retString += animal.toString() + "\n";
        }
        
        retString += "\nFood:\n";
        for (int i = 0; i < this.foods.size(); i++) {
            retString += this.foods.get(i).toString();

            if (i != this.foods.size() - 1) {
                retString += "\n";
            }
        }

        return retString;
    }
}
