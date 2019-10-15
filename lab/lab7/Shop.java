import java.util.stream.Collectors;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.List;

/**
 * A shop object maintains the list of servers and support queries
 * for server.
 *
 * @author weitsang
 * @author atharvjoshi
 * @version CS2030 AY19/20 Sem 1 Lab 7
 */
class Shop {
    /** List of servers. */
    private final List<Server> servers;

    /**
     * Create a new shop with a given number of servers.
     * @param numOfServers The number of servers.
     */
    public Shop(int numOfServers) {
        this.servers = new ArrayList<>();
        IntStream.rangeClosed(1, numOfServers).forEach(x -> {
            this.servers.add(new Server(x));
        }); 
    }

    private Shop(List<Server> servers) {
        this.servers = servers;
    }

    public Shop copy() {
        return new Shop(copyServers());
    }

    public List<Server> copyServers() {
        return this.servers.stream().map(x -> x.copy()).collect(Collectors.toList());
    }

    public Optional<Server> find(Predicate<Server> fn) {
        return this.servers.stream().filter(x -> fn.test(x)).findFirst();
    }

    public Shop replace(Server svr) {
        // find server with svr id
        // change server to new server
        List<Server> svrs = copyServers();
        svrs.replaceAll(x -> x.hashCode() == svr.hashCode() ? svr : x);
        return new Shop(svrs);
    }

    /**
     * Return a string representation of this shop.
     * @return A string reprensetation of this shop.
     */
    public String toString() {
        return servers.toString();
    }
}