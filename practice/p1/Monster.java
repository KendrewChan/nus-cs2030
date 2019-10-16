import java.util.PriorityQueue;
import java.util.Comparator;

class Monster {
    public static class MonsterComparator implements Comparator<Monster> {
        @Override
        public int compare(Monster a, Monster b) {
            int x = Integer.compare(b.getHealth(), a.getHealth());
            return x;
        }
    }

    private String name;
    private int health;
    public static PriorityQueue<Monster> monsters;

    private Monster(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public String getName() {
        return this.name;
    }

    public int getHealth() {
        return this.health;
    }

    public static Monster createNewMonster(String name, int health) {
        return new Monster(name, health);
    }

    public static void addNewMonster(String name, int health) {
       if (!(Monster.monsters.comparator() instanceof MonsterComparator)) {
           Monster.monsters = new PriorityQueue<>(new MonsterComparator());
       }

       Monster.monsters.add(Monster.createNewMonster(name, health)); 
    }

    public static Monster getStrongestMonster() {
        return Monster.monsters.peek();
    }

    @Override
    public String toString() {
        return String.format("%s (Hit Points: %d)", this.name, this.health);
    }
}
