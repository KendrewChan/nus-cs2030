class BigCruise extends Cruise {
  public BigCruise(String id, int arrivalTime, int numLoadersRequired, int timeRequired) {
    super(id, arrivalTime);
    super.setTimeRequired(timeRequired);
    super.setNumLoadersRequired(numLoadersRequired);
  }
}