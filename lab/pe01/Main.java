import java.util.*;
import java.io.*;

class BC implements Comparator<Booking> {
    @Override
    public int compare(Booking a, Booking b) {
        return a.compareTo(b);
    }
}

class Main {
    public static Request getRequest(Scanner sc) {
        List<Integer> ints = new ArrayList<>();
        while (sc.hasNextInt()) {
            ints.add(sc.nextInt());
        }

        Request r = new Request(ints.get(0), ints.get(1), ints.get(2));

        return r;
    }

    public static List<Driver> getDrivers(Scanner sc) {
        List<Driver> drivers = new ArrayList<>();

        while (sc.hasNext()) {
            String type = sc.next();

            if (type.equals("NormalCab")) {
                drivers.add(new NormalCab(sc.next(), sc.nextInt()));
            } else {
                drivers.add(new PrivateCar(sc.next(), sc.nextInt()));
            }
        } 

        return drivers;
    }


    public static void makeBookings(Request req, List<Driver> drivers, Service svcs[]) {
        List<Booking> bookings = new ArrayList<>();
        
        for (Driver driver : drivers) {
            for (Service svc : svcs) {
                if (driver.getDT().equals("NormalCab") && svc.getName().equals("ShareARide")) {
                   continue; 
                }


                if (driver.getDT().equals("PrivateCar") && svc.getName().equals("TakeACab")) {
                   continue; 
                }

                bookings.add(new Booking(driver, svc, req));
            }
        }

        bookings.sort(new BC());
        
        for (Booking b : bookings) {
            System.out.println(b);
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        Request req = getRequest(sc);
        Service svcs[] = {new ShareARide(), new JustRide(), new TakeACab()};
        List<Driver> drvs = getDrivers(sc);
        makeBookings(req, drvs, svcs);
    }
}