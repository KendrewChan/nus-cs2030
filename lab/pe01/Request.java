class Request {
    private int dist;
    private int nPassengers;
    private int initTime;

    public Request(int dist, int nPassengers, int initTime) {
        this.dist = dist;
        this.nPassengers = nPassengers;
        this.initTime = initTime;
    }

    public int getDist() {
        return this.dist;
    }

    public int getNPassengers() {
        return this.nPassengers;
    }

    public int getInitTime() {
        return this.initTime;
    }

    @Override
    public String toString() {
        return String.format("%dkm for %dpax @ %dhrs", this.dist, this.nPassengers, this.initTime);
    }
}
