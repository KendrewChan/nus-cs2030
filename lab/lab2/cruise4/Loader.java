class Loader {
  private final int id;
  private Cruise cruise;

  public Loader(int id) {
    this.id = id;
    this.cruise = null;
  }

  public Loader initWithCruise(Cruise newCruise) {
    Loader newLoader = new Loader(this.id);
    newLoader.cruise = newCruise;
    return newLoader;
  }

  public Cruise getCruise() {
    return this.cruise;
  }

  public int getId() {
    return this.id;
  }

  /**
   * @return String if can serve else null
   */
  public Loader serve(Cruise newCruise) {
    if (this.cruise == null) {
      return initWithCruise(newCruise);
    }

    // Check arrival time of cruise
    if (newCruise.getArrivalTime() >= this.cruise.getServiceCompletionTime()) {
      return initWithCruise(newCruise);
    }

    return null;
  }

  public String toString() {
    if (this.cruise == null) {
      return String.format("Loader %d", this.id);
    }

    return String.format("Loader %d serving %s", this.id, this.cruise);
  }
}