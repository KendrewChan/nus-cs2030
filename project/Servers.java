import java.util.List;
import java.util.ArrayList;

/**
 * This class represents a cluster of servers.
 */
class Servers {
    /**
     * Array of servers in this clustor of servers.
     */
    private Server[] servers;

    /**
     * List of waitingTimes for the servers which require waiting.
     */
    private List<Double> waitingTimes;

    /**
     * Numbers of customers with state LEAVES.
     */
    private int nLeaves;

    /**
     * Numbers of customers served.
     */ 
    private int nServed;

    /**
     * Initiates the fields in this cluster of servers.
     * @param n is used to initialise n number of servers
     */
    public Servers(int n) {
        this.servers = new Server[n];

        for (int i = 0; i < n; i++) {
            this.servers[i] = new Server(i + 1);
        }

        this.nLeaves = 0;
        this.nServed = 0;
        this.waitingTimes = new ArrayList<>();
    }

    /**
     * Adds specfied waitingTime to the waitingTimes list.
     * @param waitingTime required for the customer to be served
     */
    public void addWaitingTime(Double waitingTime) {
        this.waitingTimes.add(waitingTime);
    }

    /**
     * <p> Checks if there is a server with a server with a waitingCustomer waiting
     * for the customer in the argument.</p>
     * @param customer is used to to find the server with a waitingCustomer waiting for it
     * @return <p>true if there is a server with a waitingCustomer waiting for the
     *         customer in the argument</p>
     */
    public boolean checkWaitingCustomer(Customer customer) {
        for (Server server : this.servers) {
            if (customer.equals(server.getCustomer()) && server.hasWaitingCustomer()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Removes and return the waitingCustomer in one of the servers.
     * @param customer is used to find the server containing itself
     * @return <ul>
     *          <li>waitingCustomer in one of the servers waiting for the customer in
     *         the argumemt</li>
     *          <li>null if there are no waiting customers</li>
     *         </ul>
     */
    public Customer popWaitingCustomer(Customer customer) {
        for (Server server : this.servers) {
            if (customer.equals(server.getCustomer()) && server.hasWaitingCustomer()) {
                return server.popWaitingCustomer();
            }
        }

        return null;
    }

    /**
     * Gets server containing the customer specified in the argument.
     * @param customer is used to find the server containing itself
     * @return <ol>
     *          <li>server serving the customer in argument or the server which has the
     *         customer as a waitingCustomer</li>
     *          <li>null if there is no such server</li>
     *         </ol>
     */
    public Server getServer(Customer customer) {
        for (Server server : this.servers) {
            if (customer.equals(server.getCustomer()) ||
                customer.equals(server.getWaitingCustomer())) {
                return server;
            }
        }

        return null;
    }

    /**
     * <p>Find the server containing the customer in the arguement and remove that
     * customer from the server.</p>
     * @param customer used to find the matching customer in the servers
     * @return <ul>
     *          <li>customer in one of the servers matching customer specified in
     *         argument</li>
     *          <li>null if customer in argument is not in any of the servers</li>
     *         </ul>
     */
    public Customer popCustomer(Customer customer) {
        for (Server server : this.servers) {
            if (customer.equals(server.getCustomer())) {
                return server.popCustomer();
            }
        }

        return null;
    }

    /**
     * Find the first empty server.
     * @return <ul>
     *          <li>the first empty server</li>
     *          <li>null if there are no empty servers</li>
     *         </ul>
     */
    public Server findEmptyServer() {
        for (Server server : this.servers) {
            if (!server.hasCustomer()) {
                return server;
            }
        }

        return null;
    }

    /**
     * Find the first server with no waitingCustomer.
     * @return <ul>
     *          <li>first server with no waitingCustomer</li>
     *          <li>null if all servers have a waiting customer</li>
     *         </ul>
     */
    public Server findNoWcServer() {
        for (Server server : this.servers) {
            if (!server.hasWaitingCustomer()) {
                return server;
            }
        }

        return null;
    }

    public State serve(Customer customer) {
        Server server = findEmptyServer();
        if (server != null) {
            return server.serve(customer);
        } else {
            Server noWcServer = findNoWcServer();

            if (noWcServer != null) {
                return noWcServer.serve(customer);
            }

            this.nLeaves++;
            return State.LEAVES;
        }
    }

    /**
     * Increments nServed by one.
     */
    public void incServed() {
        this.nServed++;
    }

    /**
     * Get average waiting time for each customer.
     * @return average waiting time of each customer
     */
    public double getAvgWaitingTime() {
        double sumWt = 0;
        for (double wt : this.waitingTimes) {
            sumWt += wt;
        }

        return sumWt / this.nServed;
    }

    /**
     * Prints out the statistics of the runtime.
     * Format <tt>[<1> <2> <3>]</tt>:
     * <ol>
     *   <li>average waiting time for customers who have been served</li>
     *   <li>number of customers served</li>
     *   <li>number of customers who left without being
     *       served in the following</li>
     * </ol>
     */
    public void printStats() {
        System.out.printf("[%.3f %d %d]", getAvgWaitingTime(), this.nServed, this.nLeaves);
    }
}