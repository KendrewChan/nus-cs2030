import java.util.*;

class Main {
    public static List<Integer> getIntRow(Scanner sc) {
        List<Integer> ins = new ArrayList<>();
        ins.add(sc.nextInt());
        
        String restOfLine[] = sc.nextLine().split(" ");
        

        for (String i : restOfLine) {
            if (!i.isBlank()) {
                ins.add(Integer.parseInt(i));
            }
        }

        return ins;
    }

    public static String getMultiWord(Scanner sc) {
        String multiWord = "";

        while (sc.hasNext() && !sc.hasNextDouble()) {
            multiWord += sc.next();
            
            if (!sc.hasNextDouble()) {
                multiWord += " ";
            }
        }
        
        return multiWord;
    }

    public static List<Internship> getInternships(Scanner sc) {
        int n = sc.nextInt();

        List<Internship> itns = new ArrayList<>(3);

        while (sc.hasNext() && !sc.hasNextInt()) {
            String name = getMultiWord(sc);
            double pts = sc.nextDouble();

            itns.add(new Internship(name, pts)); 
        }

        return itns;
    }

    public static List<Student> getStudents(Scanner sc, List<Internship> itns) {
        int n = sc.nextInt();

        List<Student> stds = new ArrayList<>(n);

        while (sc.hasNext() && !sc.hasNextInt()) {
            String name = sc.next();
            List<String> grades = new ArrayList<>();
            List<Internship> stdItns = new ArrayList<>();

            while (sc.hasNext() && !sc.hasNextInt()) {
                grades.add(sc.next());
            }
            
            for (int i : getIntRow(sc)) {
               stdItns.add(itns.get(i));
            }

            Student std = new Student(name, grades.toArray(new String[grades.size()]), stdItns.toArray(new Internship[stdItns.size()]));

            stds.add(std);
        }

        return stds;
    }

    public static List<Job> getJobs(Scanner sc, List<Student> stds) {
        int n = sc.nextInt();
        List<Job> jobs = new ArrayList<>(n);

        while (sc.hasNext() && !sc.hasNextInt()) {
            String name = getMultiWord(sc); 
            double req = sc.nextDouble();
            int vac = sc.nextInt();

            Job job = new Job(name, req, vac);
            
            for (int idx : getIntRow(sc)) {
                Student std = stds.get(idx);
                job.apply(new Student[] {std});
            }

            jobs.add(job);
        }
        
        return jobs;
    }

    public static void getHire(Scanner sc, List<Job> jobs) {
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            Job job = jobs.get(n);
            System.out.println(job.getName() + " ");
            job.hire();
            System.out.println();
            System.out.println();
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        List<Internship> itns = getInternships(sc);
        List<Student> stds = getStudents(sc, itns);
        List<Job> jobs = getJobs(sc, stds);
        getHire(sc, jobs);
    }
}
