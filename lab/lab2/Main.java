import java.util.Scanner;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class ArrivalTimeComparator implements Comparator<Cruise> {
  @Override
  public int compare(Cruise a, Cruise b) {
    return a.getArrivalTime() - b.getArrivalTime();
  }
}

class Main {
  static Cruise[] readInput() {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    Cruise cruises[] = new Cruise[n];

    for (int i = 0; i < n; i++) {
      String id = sc.next();

      if (id.charAt(0) == 'B') {
        cruises[i] = new BigCruise(id, sc.nextInt(), sc.nextInt(), sc.nextInt());
      } else {
        cruises[i] = new Cruise(id, sc.nextInt());
      }
    }

    return cruises;
  }

  public static void printCruises(Cruise[] cruises) {
    for (Cruise cruise : cruises) {
      System.out.print(cruise);
      System.out.print("\n");
    }
  }

  public static void printLoader(Loader loader) {
    System.out.printf("%s\n", loader);
  }

  public static void main(String[] args) {
    Cruise cruises[] = readInput();
    // Sort cruises by arrival time in ascending order
    Arrays.sort(cruises, new ArrivalTimeComparator());

    List<Loader> loaders = new ArrayList<Loader>();

    int one = 1;

    for (Cruise cruise : cruises) {
      int numLoadersToFulfil = cruise.getNumLoadersRequired();

      for (int n = 0; n < numLoadersToFulfil; n++) {
        /**
         * third loader is recyled loader
         * 60 min maintenance 
         *
         *
         *
         */
        int loadersSize = loaders.size();
        if (loadersSize > 0) {
          boolean hasServed = false;

          for (int i = 0; i < loadersSize; i++) {
            Loader loader = loaders.get(i).serve(cruise);
            if (loader != null) {
              loaders.set(i, loader);
              hasServed = true;
              printLoader(loader);
              break;
            }
          }

          if (!hasServed) {
            int id = loadersSize + 1;
            Loader newLoader = new Loader(id).serve(cruise);
            // If is third loader, make it recycled
            if (id % 3 == 0) {
              newLoader.setIsRecycled(true);
            }

            loaders.add(newLoader);
            printLoader(newLoader);
          }

        } else {
          Loader newLoader = new Loader(one).serve(cruise);
          loaders.add(newLoader);
          printLoader(newLoader);
        }
      }
    }
  }
}