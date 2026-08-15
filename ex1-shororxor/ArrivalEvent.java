class ArrivalEvent extends BankEvent {
  private int counterId;
  private int customerId;
  private double serviceTime;
  private boolean[] available;

  public ArrivalEvent(double time, int customerId, double serviceTime, boolean[] available) {
    super(time);
    this.counterId = -1;
    this.customerId = customerId;
    this.serviceTime = serviceTime;
    this.available = available;
  }

  @Override
  public String toString() {
    String str = String.format(": Customer %d arrives", this.customerId);
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    for (int i = 0; i < this.available.length; i += 1) {
      if (available[i]) {
        this.counterId = i;
        break;
      }
    }

    if (this.counterId == -1) {
      return new Event[] {new DepartureEvent(this.getTime(), this.customerId)};
    } else {
      return new Event[] {
        new ServiceBeginEvent(
            this.getTime(), this.counterId, this.customerId, this.serviceTime, this.available)
      };
    }
  }
}
