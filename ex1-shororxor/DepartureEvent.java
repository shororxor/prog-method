class DepartureEvent extends BankEvent {
  private int customerId;

  public DepartureEvent(double time, int customerId) {
    super(time);
    this.customerId = customerId;
  }

  @Override
  public String toString() {
    String str = String.format(": Customer %d departs", this.customerId);
    return super.toString() + str;
  }
}
