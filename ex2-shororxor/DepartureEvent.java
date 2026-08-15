class DepartureEvent extends BankEvent {
  private Customer customer;
  private CountersManager countersManager;

  public DepartureEvent(Customer customer, CountersManager countersManager) {
    super(customer.getEndTime());
    this.customer = customer;
    this.countersManager = countersManager;
  }

  @Override
  public Event[] simulate() {
    if (this.customer.getCounterId() != -1 && !this.countersManager.isQueueEmpty()) {
      Customer newCustomer = (Customer) this.countersManager.getQueue().deq();
      newCustomer.setArrivalTime(this.customer.getEndTime());
      newCustomer.setCounterId(this.customer.getCounterId());
      return new Event[] {new ServiceBeginEvent(newCustomer, countersManager)};
    } else {
      return new Event[] {};
    }
  }

  @Override
  public String toString() {
    return String.format("%s: C%d departs", super.toString(), this.customer.getId());
  }
}
