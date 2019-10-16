/**
 * This class represents a server.
 */
class Server {
    /**
     * end time for serving the customer who is currently served by the server.
     */
    private double endTime;

    /**
     * the customer the server is serving currently.
     */
    private Customer customer;

    /**
     * the customer waiting to be served after the current customer.
     */
    private Customer waitingCustomer;

    /**
     * unique identification number for the server.
     */
    private int id;

    /**
     * An id is pass into the constructor to initiate it's corresponding field.
     */
    public Server(int id) {
        this.id = id;
    }

    /**
     * Get the id of the server.
     * @return the id of the server
     */
    public int getId() {
        return this.id;
    }

    /**
     * Check if this server can serve the new customer.
     * @param newCustomer to check if he/she can be served by this server
     * @return true 
     *         <ol>
     *          <li>if there are no customers</li>
     *          <li>if newCustomer arrivalTime is more than 
     *              the current customer endTime</li>
     *         </ol>
     */
    public boolean checkCanServe(Customer newCustomer) {
        return this.customer == null || newCustomer.getTime() >= this.endTime;
    }

    /**
     * Checks if this sever has a customer.
     * @return true if this server has a customer
     */
    public boolean hasCustomer() {
        return this.customer != null;
    }

    /**
     * Checks if this server has any waiting customer.
     * @return true if there is a waiting customer
     */
    public boolean hasWaitingCustomer() {
        return this.waitingCustomer != null;
    }

    /**
     * Get the customer of this server.
     * @return customer of this server
     */
    public Customer getCustomer() {
        return this.customer;
    }

    /**
     * Get the waitingCustomer of this server.
     * @return waitingCustomer of this server
     */
    public Customer getWaitingCustomer() {
        return this.waitingCustomer;
    }

    /**
     * Removes and return the customer of the server.
     * @return the customer of the server
     */
    public Customer popCustomer() {
        Customer c = this.customer.copy();
        this.customer = null;
        return c;
    }

    /**
     * Removes and return the waitingCustomer of the server.
     * @return the waitingCustomer of the server
     */
    public Customer popWaitingCustomer() {
        Customer wc = this.waitingCustomer.copy();
        this.waitingCustomer = null;
        return wc;
    }

    /**
     * Serves the new customer.
     * @param newCustomer to be served
     * @return state of the new customer
     */
    public State serve(Customer newCustomer) {
        if (checkCanServe(newCustomer)) {
            this.customer = newCustomer;
            this.endTime = this.customer.getTime() + Constant.SERVICE_TIME;
            return State.SERVED;
        } else if (waitingCustomer == null) {
            this.waitingCustomer = newCustomer;
            return State.WAITS;
        } else {
            return State.LEAVES;
        }
    }
}
