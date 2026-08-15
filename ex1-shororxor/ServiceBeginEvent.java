class ServiceBeginEvent extends BankEvent {
  private int counterId;
  private int customerId;
  private double serviceTime;
  private boolean[] available;

  public ServiceBeginEvent(
      double time, int counterId, int customerId, double serviceTime, boolean[] available) {
    super(time);
    this.counterId = counterId;
    this.customerId = customerId;
    this.serviceTime = serviceTime;
    this.available = available;
  }

  @Override
  public String toString() {
    String str =
        String.format(
            ": Customer %d service begins (at Counter %d)", this.customerId, this.counterId);

    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    this.available[this.counterId] = false;
    double endTime = this.getTime() + this.serviceTime;

    return new Event[] {
      new ServiceEndEvent(endTime, this.counterId, this.customerId, this.available)
    };
  }
}
