import java.lang.Math;


class Point {
    private final double x;
    private final double y;
    
    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public double getX(){
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    @Override
    public String toString(){
        return String.format("point (%.3f, %.3f)", this.x, this.y);
    }
    
    private double calcHalf(double a, double b){
        return (a + b) / 2;
    }
    
    public Point midPoint(Point pt){
        double x = calcHalf(pt.getX(), getX());
        double y = calcHalf(pt.getY(), getY());
        return new Point(x, y);
    }

    /**
     * T: Top,
     * H: Horizontal,
     * B: Bottom,
     * R: Right,
     * L: Left
     */
    enum Quad {
        TR,
        HL,
        TL,
        BL,
        BR,
        HR,
        SAME,
    }

    public Quad getQuadrant(Point pt){
        if (pt.getX() == getX() && pt.getY() == getY()) {
            return Quad.SAME;
        }

        if (pt.getX() > getX() && pt.getY() == getY()) {
            return Quad.HR;
        }

        if (pt.getX() >= getX() && pt.getY() > getY()){
            return Quad.TR;
        }
        
        if (pt.getX() < getX() && pt.getY() > getY()){
            return Quad.TL;
        }
        
        if (pt.getX() < getX() && pt.getY() == getY()) {
            return Quad.HL;
        }

        if (pt.getX() < getX() && pt.getY() < getY()){
            return Quad.BL;
        }

        return Quad.BR;
    }

    // Get shortest angle
    public double simpleAngleTo(Point pt){
        double opp = pt.getY() - getY();
        double adj = pt.getX() - getX();
        return Math.atan(opp / adj);
    }

    public double angleTo(Point pt){
        Quad quad = getQuadrant(pt);
        double angle = simpleAngleTo(pt);
        
        if (quad == Quad.HR || quad == Quad.SAME) {
            return 0;
        }

        if (quad == Quad.HL) {
            return Math.PI;
        }
        
        if (quad == Quad.TL){
            return angle + Math.PI / 2;
        }
        if (quad == Quad.BL){
            return -Math.PI + angle;
        }
        return angle;
    }

    public Point moveTo(double angle, double dist){
        double x = getX() + dist * Math.cos(angle);
        double y = getY() + dist * Math.sin(angle);
        return new Point(x, y);
    }

    public double distance(Point q) {
        double dx = getX() - q.getX();
        double dy = getY() - q.getY();
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }
}
