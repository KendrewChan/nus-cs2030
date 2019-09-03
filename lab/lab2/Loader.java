class Loader {
  private final int id;
  private final boolean isRecycled;
  private Cruise cruise;

  public Loader(int id, boolean isRecycled) {
    this.id = id;
    this.cruise = null;
    this.isRecycled = isRecycled;
  }

  public Loader initWithCruise(Cruise newCruise) {
    Loader newLoader = new Loader(this.id, this.isRecycled);
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
    if (this.isRecycled) {
      // 60 min maintenance time
      if (newCruise.getArrivalTime() >= this.cruise.getServiceCompletionTime() + 60) {
        return initWithCruise(newCruise);
      }
    } else {
      if (newCruise.getArrivalTime() >= this.cruise.getServiceCompletionTime()) {
        return initWithCruise(newCruise);
      }
    }

    return null;
  }

  public String toString() {
    String recycledLabel = this.isRecycled ? " (recycled)" : "";

    if (this.cruise == null) {
      return String.format("Loader %d%s", this.id, recycledLabel);
    }

    return String.format("Loader %d%s serving %s", this.id, recycledLabel, this.cruise);
  }
}