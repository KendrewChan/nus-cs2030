import java.util.Scanner;

class Main {
	static Point[] readPoints() {
		Scanner sc = new Scanner(System.in);
		// Scans the next input
		int n = sc.nextInt();
		// 2d array
		Point[] points = new Point[n];
		// Reading points
		for (int i = 0; i < n; i++) {
			points[i] = new Point(sc.nextDouble(), sc.nextDouble());
		}

		sc.close();
		
		return points;
	}

	static void printPoints(Point[] points) {
		for (Point p : points) {
			System.out.println(p);
		}
	}

	static int discCover(Point point, Point[] points) {
		int count = 0;
		UnitCircle uc = new UnitCircle(point);
		for (Point p : points) {
			if (uc.contains(p)) {
				count++;
			}
		}
		return count;
	}

	// Byte code is portable (Can be run across different environments)
	// Need main method to start running java
	public static void main(String[] args) {
		Point[] points = readPoints();
		printPoints(points);
	}
}
