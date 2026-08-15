class ArrivalEvent extends BankEvent {
  private Customer customer;
  private CountersManager countersManager;

  public ArrivalEvent(Customer customer, CountersManager countersManager) {
    super(customer.getArrivalTime());
    this.customer = customer;
    this.countersManager = countersManager;
  }

  @Override
  public String toString() {
    return String.format(
        "%s: C%d arrives %s",
        super.toString(), this.customer.getId(), this.countersManager.getQueue().toString());
  }

  @Override
  public Event[] simulate() {
    int counter = this.countersManager.getAvailableCounter();

    if (counter == -1) {
      if (this.countersManager.isQueueFull()) {
        this.customer.setEndTime(this.customer.getArrivalTime());
        return new Event[] {new DepartureEvent(this.customer, this.countersManager)};
      } else {
        return new Event[] {new JoinQueueEvent(this.customer, this.countersManager)};
      }
    } else {
      this.customer.setCounterId(counter);
      return new Event[] {new ServiceBeginEvent(this.customer, this.countersManager)};
    }
  }
}
