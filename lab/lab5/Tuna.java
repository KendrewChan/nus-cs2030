class Tuna extends Food {
    public Tuna(String brand) {
        super(brand, "tuna");
    }

    @Override
    public String toString() {
        return String.format("%s %s", super.getBrand(), super.getName());
    }
}