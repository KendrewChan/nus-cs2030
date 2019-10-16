import java.util.HashMap;
import java.util.Map;

class Gpa {
    public static Map<String, Double> gradeMap = new HashMap<>();

    static {
        Gpa.gradeMap.put("A+", 5.0);
        Gpa.gradeMap.put("A", 5.0);
        Gpa.gradeMap.put("A-", 4.5);
        Gpa.gradeMap.put("B+", 4.0);
        Gpa.gradeMap.put("B", 3.5);
        Gpa.gradeMap.put("B-", 3.0);
        Gpa.gradeMap.put("C+", 2.5);
        Gpa.gradeMap.put("C", 2.0);
        Gpa.gradeMap.put("D+", 1.5);
        Gpa.gradeMap.put("D", 1.0);
        Gpa.gradeMap.put("F", 0.0);
    }

    public static double calculate(String grades[]) {
       double sumGpa = 0;

       for (String grade : grades) {
           if (Gpa.gradeMap.containsKey(grade)) {
                sumGpa += Gpa.gradeMap.get(grade);
           }
       }

       double ovGpa = sumGpa / grades.length;

       return ovGpa;
    }
}
