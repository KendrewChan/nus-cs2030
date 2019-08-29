import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

class Main {
    public static Circle createCircle(Point p, Point q, double radius) {
        if (p.getX() == q.getX() && p.getY() == q.getY()) {
            return null;
        }
        Point m = p.midPoint(q);
        double distPM = p.distance(m);
        double distMC = Math.sqrt(Math.pow(radius, 2) - Math.pow(distPM, 2));
        // Angle of p to q
        double theta = p.angleTo(q);
        m = m.moveTo(theta + Math.PI / 2, distMC);
        if (Double.isNaN(m.getX())|| Double.isNaN(m.getY())) {
            return null;
        }
        return Circle.getCircle(m, radius);
    }

    public static Circle createUnitCircle(Point p, Point q) {
        return createCircle(p, q, 1);
    }

    static Point[] readPoints() {
        Scanner sc = new Scanner(System.in);
        // Indicates the no. of points
        int n = sc.nextInt();
        // Array of n points
        Point[] points = new Point[n];

        for (int i = 0; i < n; i++) {
            points[i] = new Point(sc.nextDouble(), sc.nextDouble());
        }

        return points;
    }

    static Circle[] makeCircles(Point[] points) {
        Set<Circle> circlesSet = new HashSet<Circle>();
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                Circle c = createUnitCircle(points[i], points[j]);
                if (j != i && c != null) {
                    circlesSet.add(c);
                }
            }
        }

        Circle[] circlesArray = circlesSet.toArray(new Circle[circlesSet.size()]);
        
        return circlesArray;
    }


    static int findDiscCover(Circle c, Point[] points) {
        int count = 0;
        for (Point p : points) {
            if (c.contains(p)) {
                count++;
            }
        }
        return count;
    }

    static int findMaxDiscCover(Circle[] circles, Point[] points) {
        int maxCount = -1;
        for (Circle c : circles) {
            int count = findDiscCover(c, points);
            
            if (count > maxCount) {
                maxCount = count;
            }
        }

        return maxCount;
    }

    static int solve(Point[] points) {
        // Make all possible circles
        Circle[] circles = makeCircles(points);
        return findMaxDiscCover(circles, points);
    }
    
    public static void main(String[] args) {
        Point[] points = readPoints();
        System.out.printf("Maximum Disc Coverage: %d\n", solve(points));
    }
}
