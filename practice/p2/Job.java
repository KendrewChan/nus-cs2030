import java.util.PriorityQueue;
import java.util.Comparator;

class Job {
    private String name;
    private double req;
    private int vac;
    private PriorityQueue<Student> stds;

    public class StudentComparator implements Comparator<Student> {
        public int compare(Student a, Student b) {
            return Double.compare(b.getTotalPts(), a.getTotalPts());
        }
    }

    public Job (String name, double req, int vac) {
        this.name = name;
        this.req = req;
        this.vac = vac;
        this.stds = new PriorityQueue<>(new StudentComparator());
    }

    public String getName() {
        return this.name;
    }

    public void apply(Student stds[]) {
        for (Student std : stds) {
            this.stds.add(std);
        }
    }

    public void hire() {
        System.out.println("Hired: ");
        int counter = 0;

        while (counter != this.vac && !this.stds.isEmpty()) {
            Student std = this.stds.poll();

            if (!std.getIsHired() && std.getTotalPts() >= this.req) {
                counter++;
                System.out.printf("%d: %s", counter, std.getName());
                System.out.print("\n");
                std.setIsHired(true);
            }
        }
    }

    @Override
    public String toString() {
        return String.format("\nFull-Time Job: %s\nVacancies: %d\nPoints Requirements: %.2f", this.name, this.vac, this.req);
    }
}
