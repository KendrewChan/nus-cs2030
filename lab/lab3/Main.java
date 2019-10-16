import java.util.Scanner;

class Main {
  static Rubik readRubikInput(Scanner sc) {
    int n = 9;
    int[][][] grid = new int[6][3][3];

    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 3; j++) {
        for (int k = 0; k < 3; k++) {
          grid[i][j][k] = sc.nextInt();
        }
      }
    }

    return new Rubik(grid);
  }

  static <T extends Rubik> Rubik turnRubik (String input, T rubik) {
    if (input.contains("2")) {
      // Half turn
      return rubik.half();
    } else if (input.contains("'")) {
      // Anti-clockwise
      return rubik.left();
    } else {
      // Clockwise
      return rubik.right();
    }
  }

  static Rubik readAndMakeTurns(Scanner sc, Rubik rubik) {
    Rubik newRubik = rubik.clone();

    while (sc.hasNext()) {
      String input = sc.next();

      if (input.contains("F")) {
        newRubik = turnRubik(input, new Rubik(newRubik));
      } else if (input.contains("R")) {
        newRubik = turnRubik(input, new RubikRight(newRubik));
      } else if (input.contains("U")) {
        newRubik = turnRubik(input, new RubikUp(newRubik));
      } else if (input.contains("L")) {
        newRubik = turnRubik(input, new RubikLeft(newRubik));
      } else if (input.contains("B")) {
        newRubik = turnRubik(input, new RubikBack(newRubik));
      } else if (input.contains("D")) {
        newRubik = turnRubik(input, new RubikDown(newRubik));
      }
    }

    return newRubik;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Rubik rubik = readRubikInput(sc);
    Rubik newRubik = readAndMakeTurns(sc, rubik);
    System.out.print(newRubik);
    System.out.print("\n");
  }
}