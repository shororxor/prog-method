class ServiceBeginEvent extends BankEvent {
  private Customer customer;
  private CountersManager countersManager;

  public ServiceBeginEvent(Customer customer, CountersManager countersManager) {
    super(customer.getArrivalTime());
    this.customer = customer;
    this.countersManager = countersManager;
  }

  @Override
  public String toString() {
    return String.format(
        "%s: C%d %s (at S%d) begins",
        super.toString(),
        this.customer.getId(),
        this.customer.getOperation(),
        this.customer.getCounterId());
  }

  @Override
  public Event[] simulate() {
    this.countersManager.occupyCounter(this.customer.getCounterId());

    return new Event[] {new ServiceEndEvent(this.customer, this.countersManager)};
  }
}
