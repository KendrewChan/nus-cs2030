import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

class Main {
  public static void main(String[] args) throws FileNotFoundException {
    Scanner scanner;
    // File exception error will be thrown
    try {
      FileReader file = new FileReader(args[0]);
      scanner = new Scanner(file);
      System.out.println(scanner.nextDouble());
    } catch (FileNotFoundException err) {
      System.err.println(err);
    } catch (InputMismatchException err) {
      System.err.println(err);
    } finally {
      System.out.println("Done");
    }
  }
}