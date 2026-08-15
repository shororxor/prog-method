class ServiceEndEvent extends BankEvent {
  private Customer customer;
  private CountersManager countersManager;

  public ServiceEndEvent(Customer customer, CountersManager countersManager) {
    super(customer.getEndTime());
    this.customer = customer;
    this.countersManager = countersManager;
  }

  @Override
  public String toString() {
    return String.format(
        "%s: C%d %s (at S%d) ends",
        super.toString(),
        this.customer.getId(),
        this.customer.getOperation(),
        this.customer.getCounterId());
  }

  @Override
  public Event[] simulate() {
    this.countersManager.freeCounter(this.customer.getCounterId());
    return new Event[] {new DepartureEvent(this.customer, this.countersManager)};
  }
}
