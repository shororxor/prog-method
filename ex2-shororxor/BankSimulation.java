import java.util.Scanner;

/**
 * This class implements a bank simulation.
 *
 * @author Wei Tsang
 * @version CS2030S AY25/26 Semester 2
 */
class BankSimulation extends Simulation {
  /**
   * The list of customer arrival events to populate
   * the simulation with.
   */
  private Event[] initEvents;

  /**
   * Constructor for a bank simulation.
   *
   * @param sc A scanner to read the parameters from.  The first
   *           integer scanned is the number of customers; followed
   *           by the number of service counters.  Next is a
   *           sequence of (arrival time, service time) pair, each
   *           pair represents a customer.
   */
  public BankSimulation(Scanner sc) {
    int numOfCustomers = sc.nextInt();
    initEvents = new Event[numOfCustomers];

    int numOfCounters = sc.nextInt();
    int queueMaxSize = sc.nextInt();
    CountersManager countersManager = new CountersManager(numOfCounters, queueMaxSize);

    int customerId = 0;
    while (sc.hasNextDouble()) {
      double arrivalTime = sc.nextDouble();
      double serviceTime = sc.nextDouble();
      int operation = sc.nextInt();
      initEvents[customerId] =
          new ArrivalEvent(
              new Customer(arrivalTime, serviceTime, customerId, operation), countersManager);
      customerId += 1;
    }
  }

  /**
   * Retrieve an array of events to populate the
   * simulator with.
   *
   * @return An array of events for the simulator.
   */
  @Override
  public Event[] getInitialEvents() {
    return initEvents;
  }
}
