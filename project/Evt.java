/**
 * This class represent each event that occurs.
 */
class Evt {
    /**
     * The customer involved in this event.
     */
    private Customer customer;
    /**
     * The state of the event.
     */
    private State state;

    /**
     * <p>customer and state are passed into the constructor
     * to initialise the customer and state fields respectively.</p>
     */
    public Evt(Customer customer, State state) {
        this.customer = customer;
        this.state = state;
    }

    /**
     * Get the customer stored.
     * @return customer stored
     */
    public Customer getCustomer() {
        return this.customer;
    }

    /**
     * Get the state stored.
     * @return state stored
     */
    public State getState() {
        return this.state;
    }

    /**
     * Sets time in the customer field.
     * @param time is the new time to set
     * @return a new Evt with a customer with the new time
     */
    public Evt setTime(double time) {
        Customer newCustomer = this.customer.copy().setTime(time);
        return new Evt(newCustomer, this.state);
    }

    /**
     * Sets a new state to the state field.
     * @param state is the new state to set
     * @return a new <tt>Evt</tt> with the new state
     */
    public Evt setState(State state) {
        return new Evt(this.customer.copy(), state);
    }
    
    /**
     * Set customer's time to when the event is done and sets state as done.
     * @return a new <tt>Evt</tt> with
     *         <ol>
     *           <li>a new customer with the new time</li>
     *           <li>the new DONE state</li>
     *         </ol>
     */
    public Evt setDone() {
        double newTime = this.customer.getTime() + Constant.SERVICE_TIME;
        Customer newCustomer = this.customer.copy().setTime(newTime);
        State newState = State.DONE;
        return new Evt(newCustomer, newState);
    }
    
    /**
     * Makes a copy of the current <tt>Evt</tt>.
     * @return a copy of the current <tt>Evt</tt>.
     */
    public Evt copy() {
        return new Evt(this.customer.copy(), this.state);
    }

    /**
     * Prints the <tt>toString</tt> representation of this evt.
     */
    public void print() {
        System.out.println(toString());
    }

    /**
     * <p>Get the string representation of this evt.</p>
     * @return <p>details of this evt in this format:</p>
     *         <tt>{time} {id} {state}</tt>
     */
    @Override
    public String toString() {
        return String.format(
                "%.3f %d %s",
                this.customer.getTime(),
                this.customer.getId(),
                this.state
            );
    }
}