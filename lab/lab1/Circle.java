class Circle {
    private final Point center;
    private final double radius;

    private Circle(Point center, double radius){
        this.center = center;
        this.radius = radius;
    }

    public static Circle getCircle(Point center, double radius) {
        if (radius > 0) {
            return new Circle(center, radius);
        }

        return null;
    }


    public boolean contains(Point point) {
        return this.center.distance(point) < radius + 1E-15;
    }

    @Override
    public String toString() {
        return String.format("circle of radius %.1f centered at point (%.3f, %.3f)", this.radius, this.center.getX(), this.center.getY());
    }
}
