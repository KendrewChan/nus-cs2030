import java.util.Scanner;

/**
 * This is main class used to run the program.
 */
class Main {
    /**
     * Get an int from user input to intiate a <tt>Servers</tt> class.
     * @return Servers with size equals the int the user input.
     */
    public static Servers getServers(Scanner sc) {
        return new Servers(sc.nextInt());
    }

    /**
     * <p>Get events based on user input.</p>
     * <ul>
     *   <li>User enters a series of doubles</li>
     *   <li>Each double represents the customer arrival times</li>
     * </ul>
     * @return Evts that represents the set of customer arrival times in chronological order
     */ 
    public static Evts getEvts(Scanner sc) {
        Evts evts = new Evts();
        int counter = 0;

        while (sc.hasNextDouble()) {
            counter++;

            Customer customer = new Customer(counter, sc.nextDouble());

            Evt evt = new Evt(customer, State.ARRIVES);

            evts.add(evt);
        }

        return evts;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Servers servers = getServers(sc);
        Evts evts = getEvts(sc);
        evts.run(servers);
    }
}
