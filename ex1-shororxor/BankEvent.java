class BankEvent extends Event {

  public BankEvent(double time) {
    super(time);
  }

  @Override
  public Event[] simulate() {
    return new Event[] {};
  }
}
