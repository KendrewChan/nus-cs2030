class Student {
  private final String name;
  private final Book book;
  
  public Student(String name, Book book) {
    this.name = name;
    this.book = book;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getBookTitle() {
    return this.book.getTitle();
  }
}