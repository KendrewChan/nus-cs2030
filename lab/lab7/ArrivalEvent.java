/**
 * Encapsulates information and methods pertaining to an arrival event.
 * This class does not do much for now, except letting the simulator call
 * {@code simulateArrival} through polymorphism.
 *
 * @author Ooi Wei Tsang
 * @author Evan Tay
 * @version CS2030 AY19/20 Sem 1 Lab 7
 */
public class ArrivalEvent extends Event {
  /**
   * Create a new ArrivalEvent to be executed at time {@code time}.
   * @param  time  The time a customer arrives.
   */
  public ArrivalEvent(double time) {
    super(time);
  }

  /**
   * Ask the simulator to simulate the arrival of a customer.
   * @param sim The simulator
   */
  public SimState simulate(SimState sim) {
    sim.simulateArrival(time);
    return sim;
  }
}
