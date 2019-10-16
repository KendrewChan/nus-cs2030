class Service {
    protected int kmFare;
    protected int surcharge;
    protected String name;

    public Service(int k, int s, String n) {
       this.kmFare = k;
       this.surcharge = s;
       this.name = n;
    }

    public int computeFare(Request request) {
        int fare = request.getDist() * this.kmFare;

        if (request.getInitTime() >= 600 && request.getInitTime() <= 900) {
            fare += this.surcharge; 
        }

        return fare;
    }

    public double getDollarFare(Request request) {
        return this.computeFare(request) * 0.01;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
