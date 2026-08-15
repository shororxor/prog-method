class Customer {
  private double arrivalTime;
  private double serviceTime;
  private double endTime;
  private int id;
  private int operation;
  private int counterId;

  public Customer(double arrivalTime, double serviceTime, int id, int operation) {
    this.arrivalTime = arrivalTime;
    this.serviceTime = serviceTime;
    this.endTime = arrivalTime + serviceTime;
    this.id = id;
    this.operation = operation;
    this.counterId = -1;
  }

  /**
   * Returns arrival time of customer
   * @return arrival time of customer
   */
  public double getArrivalTime() {
    return this.arrivalTime;
  }

  /**
   * Returns end time of customer
   * @return end time of customer
   */
  public double getEndTime() {
    return this.endTime;
  }

  /**
   * Return customer id
   * @return customer id
   */
  public int getId() {
    return this.id;
  }

  /**
   * Return operation string of customer
   * @return operation string of customer
   */
  public String getOperation() {
    return this.operation == 0 ? "deposit" : "withdrawal";
  }

  /**
   * Returns counter id that current customer use
   * @return counter id that current customer use
   */
  public int getCounterId() {
    return this.counterId;
  }

  /**
   * set arrival time
   * @param time
   */
  public void setArrivalTime(double time) {
    this.arrivalTime = time;
    this.endTime = this.arrivalTime + this.serviceTime;
  }

  /**
   * set counter id for customer
   * @param id
   */
  public void setCounterId(int id) {
    this.counterId = id;
  }

  /**
   * set end time for customer
   * @param time
   */
  public void setEndTime(double time) {
    this.endTime = time;
  }

  @Override
  public String toString() {
    return String.format("C%d", this.id);
  }
}
