class Cruise {
  private final String id;
  // Stored as HHMM
  private final int arrivalTime;
  private int timeRequired;
  private int numLoadersRequired;

  public Cruise(String id, int arrivalTime) {
    this.id = id;
    this.arrivalTime = arrivalTime;
    setTimeRequired(30);
    setNumLoadersRequired(1);
  }

  public void setTimeRequired(int n) {
    this.timeRequired = n;
  }
  
  public int getTimeRequired(int n) {
    return this.timeRequired;
  }

  public void setNumLoadersRequired(int n) {
    this.numLoadersRequired = n;
  }

  public int getNumLoadersRequired() {
    return this.numLoadersRequired;
  }

  public int getServiceCompletionTime() {
    return this.getArrivalTime() + this.timeRequired;
  }

  /**
   * @return arrivalTime as minutes from 0000
   */
  public int getArrivalTime() {
    int hours = Math.floorDiv(this.arrivalTime, 100);
    int minutes = this.arrivalTime % 100;
    int time = hours * 60 + minutes;;
    return time;
  }

  @Override
  public String toString() {
    // %04d: integer will be represented by an 4-digit zero-padded number
    return String.format("%s@%04d", this.id, this.arrivalTime);
  }
}