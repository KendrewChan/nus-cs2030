class Chocolate extends Food {
    private String flavor;

    public Chocolate(String brand, String flavor) {
        super(brand, "chocolate");
        this.flavor = flavor;
    }

    public String getFlavor() {
        return this.flavor;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", super.getBrand(), this.flavor, super.getName());
    }
}
