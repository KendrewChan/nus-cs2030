 public class Book {
  private final String title;
  private final Student student;

  public Book(String title, Student student) {
    this.title = title;
    this.student = student;
  }

  public String getTitle() {
    return this.title;
  }

  public String getStudentName() {
    return this.student.getName();
  }
}