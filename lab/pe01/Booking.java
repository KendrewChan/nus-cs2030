import java.util.*;

class Booking implements Comparable<Booking> {
    private Driver driver;
    private Service service;
    private Request request;

    public Booking(Driver driver, Service service, Request request) {
        this.driver = driver;
        this.service = service;
        this.request = request;
    }

    public int getComputedFare() {
        int fare = this.service.computeFare(this.request);

        if (this.service.getName() == "ShareARide") {
            fare = fare / this.request.getNPassengers();
        } 

        return fare;
    }

    public double getDollarFare() {
        return getComputedFare() * 0.01;
    }

    public Driver getDriver() {
        return this.driver;
    }
    
    public int compareTo(Booking b) {
        int round1 = Integer.compare(getComputedFare(), b.getComputedFare());

        if (round1 == 0) {
            int round2 = Integer.compare(this.driver.getWaitingTime(), b.getDriver().getWaitingTime()); 
            return round2;
        } else {
            return round1;
        }
    }

    @Override
    public String toString() {
        return String.format("$%.2f using %s (%s)", getDollarFare(), this.driver, this.service);
    }
}
