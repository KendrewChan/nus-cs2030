import java.util.List;
import java.util.Scanner;

class QA {
  String question;
  char answer;
  List<Character> possibleAnswers;

  public QA(String question, List<Character> possibleAnswers) {
    this.question = question;
    this.possibleAnswers = possibleAnswers;
  }

  void getAnswer() throws Exception {
    System.out.print(question + "");
    this.answer = (new Scanner(System.in)).next().charAt(0);
    if (!this.possibleAnswers.contains(this.answer)) {
      throw new Exception("Answer");
    }
  }
}

abstract class QA {
  String question;
  char answer;

  public QA(String question) {
    this.question = question;
  }

  abstract void checkValidAnswer(char answer);

  void getAnswer() {
    System.out.print(question + "");
    this.answer = (new Scanner(System.in)).next().charAt(0);
    checkValidAnswer(answer);
  }
}

class MCQ extends QA {
  public MCQ(String question) {
    super(question);
  }

  public void checkValidAnswer(char answer) {
    if (answer >= 'A' && answer <= 'E') {
      throw new Exception("Answer");
    }
  }
}