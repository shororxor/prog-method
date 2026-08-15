class ServiceEndEvent extends BankEvent {
  private int counterId;
  private int customerId;
  private boolean[] available;

  public ServiceEndEvent(double time, int counterId, int customerId, boolean[] available) {
    super(time);
    this.counterId = counterId;
    this.customerId = customerId;
    this.available = available;
  }

  @Override
  public String toString() {
    String str =
        String.format(
            ": Customer %d service ends (at Counter %d)", this.customerId, this.counterId);
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    this.available[this.counterId] = true;
    return new Event[] {new DepartureEvent(this.getTime(), this.customerId)};
  }
}
