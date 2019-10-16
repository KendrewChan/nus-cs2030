import java.util.PriorityQueue;

/**
 * <p>
 *   This class contains a <tt>PriorityQueue</tt> of <tt>Evt</tt>.
 * </p>
 * <p>It contains methods to run these <tt>Evt</tt>.</p>
 */
class Evts extends PriorityQueue<Evt> {
    /**
     * <p>
     *   Initiates the PriorityQueue by passing <tt>EvtComparator</tt>
     *   as argument to <tt>super()</tt>.
     * </p>
     */
    public Evts() {
        super(new EvtComparator());
    }
    
    /**
     * Makes a copy of the Evts object.
     * @return a copy of this Evts object
     */
    public Evts copy() {
        Evts newEvts = new Evts();
        for (Evt e : this) {
            newEvts.add(e);
        }
        return newEvts;
    }

    /**
     * Prints evts in the order of which they are removed from the queue.
     */
    public void print() {
        Evts evtsCopy = copy();
        while (evtsCopy.size() > 0) {
            System.out.println(evtsCopy.poll());
        }
    }

    /**
     * Calls poll method (i.e. removes top most item in evts) and prints output.
     * based on the state and server of the evt
     * @param servers are the servers used to serve the customers in the evts
     * @return the evt that is polled from the queue
     */
    public Evt pollEvt(Servers servers) {
        Evt evt = this.poll();
        Server server = servers.getServer(evt.getCustomer());
        if (evt.getState() == State.DONE) {
            System.out.printf("%s serving by %d\n", evt, server.getId());
        } else if (evt.getState() == State.SERVED) {
            System.out.printf("%s by %d\n", evt, server.getId());
        } else if (evt.getState() == State.WAITS) {
            System.out.printf("%s to be served by %d\n", evt, server.getId());
        } else {
            System.out.println(evt);
        }

        return evt;
    }

    /**
     * Runs this queue of evts and print outputs.
     * <ol>
     *  <li>Prints the evts occuring in sequence.</li>
     *  <li>Prints statistics from the servers.</li>
     * </ol>
     * @param servers used to serve the customer in this queue of evts
     */
    public void run(Servers servers) {
        Evts evts = copy();
        while (evts.size() > 0) {
            Evt e = evts.pollEvt(servers);
            // Add served event / done event
            if (e.getState() == State.ARRIVES) {
                State state = servers.serve(e.getCustomer());
                Evt newE = e.setState(state);
                evts.add(newE);
            } else if (e.getState() == State.SERVED) {
                Evt newE = e.setDone();
                servers.incServed();
                evts.add(newE);
            } else if (
                e.getState() == State.DONE &&
                servers.checkWaitingCustomer(e.getCustomer())
            ) {
                // Check for waiting customer if a server is done
                Customer waitingCustomer = servers.popWaitingCustomer(e.getCustomer());
                servers.popCustomer(e.getCustomer());

                double currentTime = e.getCustomer().getTime();

                // Calc waiting time
                double waitingTime = currentTime - waitingCustomer.getTime();

                // Set waiting customer time to currentTime and add to evts
                waitingCustomer = waitingCustomer.setTime(currentTime);
                State state = servers.serve(waitingCustomer);
                evts.add(new Evt(waitingCustomer, state));

                // Add waiting time to servers stats;
                servers.addWaitingTime(waitingTime);
            } else if (e.getState() == State.DONE) {
                servers.popCustomer(e.getCustomer());
            }
        }

        servers.printStats();
        System.out.println();
    }
}