import java.util.function.Function;
import java.util.Optional;
/**
 * This class encapsulates all the simulation states.  There are four main
 * components: (i) the event queue, (ii) the statistics, (iii) the shop
 * (the servers) and (iv) the event logs.
 *
 * @author atharvjoshi
 * @author weitsang
 * @version CS2030 AY19/20 Sem 1 Lab 7
 */
public class SimState {
    /** The priority queue of events. */
    private final PriorityQueue<Event> events;

    /** The statistics maintained. */
    private final Statistics stats;

    /** The shop of servers. */
    private final Shop shop;

    private int customerIdCounter;

    private String log;

    /**
     * Constructor for creating the simulation state from scratch.
     * @param numOfServers The number of servers.
     */
    public SimState(int numOfServers) {
        this.shop = new Shop(numOfServers);
        this.stats = new Statistics();
        this.events = new PriorityQueue<>();
        this.log = "";
        this.customerIdCounter = 1;
    }

    private SimState(Shop shop, Statistics stats, PriorityQueue<Event> events, String log, int customerIdCounter) {
        this.shop = shop;
        this.stats = stats;
        this.events = events;
        this.log = log;
        this.customerIdCounter = customerIdCounter;
    }

    public class Event implements Comparable<Event> {
        /** The time this event occurs at. */
        protected double time;
        protected Function<SimState, SimState> fn;

        /**
         * Creates an event and initializes it.
         *
         * @param time The time of occurrence.
         */
        public Event(double time, Function<SimState, SimState> fn) {
            this.time = time;
            this.fn = fn;
        }

        /**
         * Defines natural ordering of events by their time.
         * Events ordered in ascending order of their timestamps.
         *
         * @param other Another event to compare against.
         * @return 0 if two events occur at same time, a positive number if
         *     this event has later than other event, a negative number otherwise.
         */
        public int compareTo(Event other) {
            return (int) Math.signum(this.time - other.time);
        }

        public SimState simulate(SimState st) {
            return fn.apply(st); 
        }

        // @Override
        public String toString() {
            return String.format("Event: %.3f", this.time);
        }
    }

    public PriorityQueue<Event> getEvents() {
        return this.events;
    }

    public SimState copy(){
        return new SimState(this.shop.copy(), this.stats.copy(), this.events.copy(), this.log, this.customerIdCounter);
    }

    public Shop getShop() {
        return this.shop;
    }

    /**
     * Add an event to the simulation's event queue.
     * @param  e The event to be added to the queue.
     * @return The new simulation state.
     */
    public SimState addEvent(double time, Function<SimState, SimState> fn) {
        Event newEvent = new Event(time, fn);
        PriorityQueue<Event> newEvents = events.add(newEvent);
        return new SimState(this.shop.copy(), this.stats.copy(), newEvents, this.log, this.customerIdCounter);
    }

    /**
     * Retrieve the next event with earliest time stamp from the
     * priority queue, and a new state.  If there is no more event, an
     * Optional.empty will be returned.
     * @return A pair object with an (optional) event and the new simulation
     *     state.
     */
    private Pair<Optional<Event>, SimState> nextEvent() {
        PriorityQueue<Event> newEvents = this.events.copy();
        Pair<Optional<Event>, PriorityQueue<Event>> result = newEvents.poll();

        System.out.println(newEvents.size());

        return Pair.of(result.first, new SimState(this.shop.copy(), this.stats.copy(), result.second, this.log, this.customerIdCounter));
    }

    /**
     * Log a customer's arrival in the simulation.
     * @param time The time the customer arrives.
     * @param c The customer that arrrives.
     * @return A new state of the simulation after the customer arrives.
     */
    public SimState noteArrival(double time, Customer c) {
        String newLog = this.log + String.format("%.3f %s arrives\n", time, c);
        return new SimState(this.shop.copy(), this.stats.copy(), this.events.copy(), newLog, this.customerIdCounter);
    }

    /**
     * Log when a customer waits in the simulation.  
     * @param time The time the customer starts waiting.
     * @param s The server the customer is waiting for.
     * @param c The customer who waits.
     * @return A new state of the simulation after the customer waits.
     */
    public SimState noteWait(double time, Server s, Customer c) {
        String newLog = this.log + String.format("%.3f %s waits to be served by %s\n", time, c, s);
        return new SimState(this.shop.copy(), this.stats.copy(), this.events.copy(), newLog, this.customerIdCounter);
    }

    /**
     * Log when a customer is served in the simulation.  
     * @param time The time the customer arrives.
     * @param s The server that serves the customer.
     * @param c The customer that is served.
     * @return A new state of the simulation after the customer is served.
     */
    public SimState noteServed(double time, Server s, Customer c) {
        String newLog = this.log + String.format("%.3f %s served by %s\n", time, c, s);
        Statistics newStats = stats.serveOneCustomer();
        newStats = newStats.recordWaitingTime(c.timeWaited(time));
        return new SimState(this.shop.copy(), newStats, this.events.copy(), newLog, this.customerIdCounter);
    }

    /**
     * Log when a customer is done being served in the simulation.
     * @param time The time the customer arrives.
     * @param s The server that serves the customer.
     * @param c The customer that is served.
     * @return A new state of the simulation after the customer is done being
     *     served.
     */
    public SimState noteDone(double time, Server s, Customer c) {
        String newLog = this.log + String.format("%.3f %s done serving by %s\n", time, c, s);
        return new SimState(this.shop.copy(), this.stats.copy(), this.events.copy(), newLog, this.customerIdCounter);
    }

    /**
     * Log when a customer leaves the shops without service.
     * @param  time  The time this customer leaves.
     * @param  customer The customer who leaves.
     * @return A new state of the simulation.
     */
    public SimState noteLeave(double time, Customer customer) {
        String newLog = this.log + String.format("%.3f %s leaves\n", time, customer);
        Statistics newStats = stats.looseOneCustomer();
        return new SimState(this.shop.copy(), newStats, this.events.copy(), newLog, this.customerIdCounter);
    }

    /**
     * Simulates the logic of what happened when a customer arrives.
     * The customer is either served, waiting to be served, or leaves.
     * @param time The time the customer arrives.
     * @return A new state of the simulation.
     */
    public SimState simulateArrival(double time) {
        Customer customer = new Customer(time, this.customerIdCounter);
        this.customerIdCounter++;

        SimState newSimState = noteArrival(time, customer).processArrival(time, customer);
        return newSimState;
    }

    /**
     * Handle the logic of finding idle servers to serve the customer, 
     * or a server that the customer can wait for, or leave.  Called
     * from simulateArrival.
     * @param time The time the customer arrives.
     * @param customer The customer to be served.
     * @return A new state of the simulation.
     */
    private SimState processArrival(double time, Customer customer) {
        Optional<Server> s = this.shop.find(x -> x.isIdle());
        if (!s.isEmpty()) {
            return serveCustomer(time, s.get(), customer);
        }
        s = shop.find(x -> !x.hasWaitingCustomer());
        if (!s.isEmpty()) {
            Server server = s.get().askToWait(customer);
            SimState newSimState = replaceShopServer(server).noteWait(time, server, customer);

            return newSimState;
        }
        return noteLeave(time, customer);
    }

    private SimState replaceShopServer(Server server) {
        Shop newShop = this.shop.replace(server);
        SimState newSimState = new SimState(newShop, this.stats, this.events, this.log, this.customerIdCounter);
        return newSimState;
    }


    /**
     * Simulate the logic of what happened when a customer is done being
     * served.  The server either serve the next customer or becomes idle.
     * @param time The time the service is done.
     * @param server The server serving the customer.
     * @param customer The customer being served.
     * @return A new state of the simulation.
     */
    public SimState simulateDone(double time, Server server, Customer customer) {
        Server mServer = this.shop.find(x -> x.hashCode() == server.hashCode()).get();
        SimState newSimState = noteDone(time, mServer, customer);
        Optional<Customer> c = mServer.getWaitingCustomer();
        if (!c.isEmpty()) {
            return newSimState.serveCustomer(time, mServer, c.get());
        }

        mServer = mServer.makeIdle();
        newSimState = newSimState.replaceShopServer(mServer);
        return newSimState;
    }

    /**
     * Handle the logic of server serving customer.  A new done event
     * is generated and scheduled.
     * @param  time  The time this customer is served.
     * @param  server The server serving this customer.
     * @param  customer The customer being served.
     * @return A new state of the simulation.
     */
    private SimState serveCustomer(double time, Server server, Customer customer) {
        double doneTime = time + Simulation.SERVICE_TIME;

        Server newServer = server.serve(customer);
        SimState newSimState = replaceShopServer(newServer).noteServed(time, newServer, customer).addEvent(doneTime, state -> state.simulateDone(doneTime, newServer, customer));

        return newSimState;
    }

    /**
     * The main simulation loop.  Repeatedly get events from the event
     * queue, simulate and update the event.  Return the final simulation
     * state.
     * @return The final state of the simulation.
     */
    public SimState run() {
        Pair<Optional<Event>, SimState> p = nextEvent();
        
        if (!p.first.isEmpty()) {
            return p.first.get().simulate(p.second).run();
        }

        return p.second;
    }

    /**
     * Return a string representation of the simulation state, which
     * consists of all the logs and the stats.
     * @return A string representation of the simulation.
     */
    public String toString() {
        return this.log + stats.toString();
    }
}