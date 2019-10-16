import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * The LabOFourA class is the entry point into Lab 4a.
 *
 * @author atharvjoshi
 * @author weitsang
 * @version CS2030 AY19/20 Sem 1 Lab 7
 */
class Main {
  /**
   * The main method for Lab 4a. Reads data from file and
   * then run a simulation based on the input data.
   *
   * @param args two arguments, first an integer specifying number of servers
   *     in the shop. Second a file containing a sequence of double values, each
   *     being the arrival time of a customer (in any order).
   */
  public static void main(String[] args) {
    try {
        Scanner scanner = createScanner(args);
        SimState state = initSimState(scanner);
        System.out.println(state.run());
    } catch (Exception e) {
        System.err.println(e);
    }
  }

  /**
   * Read from inputs, populate the simulator with events, and run.
   *
   * @param scanner The scanner to read inputs from.
   */
  public static SimState initSimState(Scanner scanner) {
    // Read the first line of input as number of servers in the shop
    int numOfServers = scanner.nextInt();
    SimState state = new SimState(numOfServers);
    state = getState(scanner, state);

    return state;
  }

  public static SimState getState(Scanner scanner, SimState refState) {
    if (scanner.hasNextDouble()) {
      double arrivalTime = scanner.nextDouble();
      SimState state = refState.addEvent(arrivalTime, s -> s.simulateArrival(arrivalTime));
      return getState(scanner, state);
    }

    return refState;
  }

  /**
   * Create and return a scanner. If a command line argument is given,
   * treat the argument as a file and open a scanner on the file. Else,
   * create a scanner that reads from standard input.
   *
   * @param args The arguments provided for simulation.
   * @return A scanner or {@code } if a filename is provided but the file
   *     cannot be open.
   * @throws FileNotFoundException
   */
  private static Scanner createScanner(String[] args) throws FileNotFoundException {
    try {
      // Read from stdin if no filename is given, otherwise read from the
      // given file.
      if (args.length == 0) {
        // If there is no argument, read from standard input.
        return new Scanner(System.in);
      } else {
        // Else read from file
        FileReader fileReader = new FileReader(args[0]);
        return new Scanner(fileReader);
      }
    } catch (FileNotFoundException exception) {
      System.err.println("Unable to open file " + args[0] + " "
          + exception);

      throw exception;
    }
  }
}
