class Driver {
    protected String license;
    protected int waitingTime;
    private String driverType;

    public Driver(String l, int w, String d) {
        this.license = l;
        this.waitingTime = w;
        this.driverType = d;
    }

    public int getWaitingTime() {
        return this.waitingTime;
    }

    public String getDT() {
        return this.driverType;
    }

    @Override
    public String toString() {
        return String.format("%s (%d mins away) %s", this.license, this.waitingTime, this.driverType);
    }
}