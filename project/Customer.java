/**
 * This class represents a customer.
 */
class Customer {
    /**
     * <tt>id</tt> used to identify the customer.
     */
    int id;
    /**
     * <tt>time</tt> the customer is at.
     */
    double time;

    /**
     * <p>id and time are passed into the constructor to initialise
     * their corresponding fields.</p>
     */
    public Customer(int id, double time) {
        this.id = id;
        this.time = time;
    }

    /**
     * Gets the id of the customer.
     * @return id of the customer
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets the time of the customer.
     * @return time of the customer
     */
    public double getTime() {
        return this.time;
    }

    /**
     * Sets a new time for the customer.
     * @return a new Customer with the new time
     */
    public Customer setTime(double time) {
        return new Customer(this.id, time);
    }

    /**
     * Make a copy of the customer.
     * @return a copy of the customer.
     */
    public Customer copy() {
        return new Customer(this.id, this.time);
    }

    /**
     * Get the string representation of the customer.
     * @return <p>a string in this format:</p>
     *         <tt>{id} arrives at {time}</tt>
     */
    @Override
    public String toString() {
        return String.format("%d arrives at %.3f", this.id, this.time);
    }

    /**
     * Compares equality of this customer with another Object.
     * @param o is an Object to be compared with
     * @return <p>true</p>
     *         <ol>
     *           <li>if the Object is the same Object</li>
     *           <li>if the Object is a Customer with the same id</li>
     *         </ol>
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Customer)) {
            return false;
        }

        Customer c = (Customer) o;

        return this.id == c.getId();
    }
}
