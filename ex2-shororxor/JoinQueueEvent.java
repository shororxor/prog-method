class JoinQueueEvent extends BankEvent {
  private Customer customer;
  private CountersManager countersManager;

  public JoinQueueEvent(Customer customer, CountersManager countersManager) {
    super(customer.getArrivalTime());
    this.customer = customer;
    this.countersManager = countersManager;
  }

  @Override
  public String toString() {
    return String.format(
        "%s: C%d joins queue %s",
        super.toString(), this.customer.getId(), this.countersManager.getQueue().toString());
  }

  @Override
  public Event[] simulate() {
    this.countersManager.pushToQueue(this.customer);
    return new Event[] {};
  }
}
