class CountersManager {
  private boolean[] available;
  private Queue queue;

  public CountersManager(int numCounters, int maxQueueSize) {
    this.available = new boolean[numCounters];
    for (int i = 0; i < numCounters; i++) {
      available[i] = true;
    }

    this.queue = new Queue(maxQueueSize);
  }

  public boolean isQueueFull() {
    return this.queue.isFull();
  }

  public boolean isQueueEmpty() {
    return this.queue.isEmpty();
  }

  public int getAvailableCounter() {
    for (int i = 0; i < this.available.length; i++) {
      if (available[i]) {
        return i;
      }
    }
    return -1;
  }

  public void occupyCounter(int counterId) {
    this.available[counterId] = false;
  }

  public void freeCounter(int counterId) {
    this.available[counterId] = true;
  }

  public Queue getQueue() {
    return this.queue;
  }

  public boolean[] getAvailable() {
    return this.available;
  }

  public void pushToQueue(Object object) {
    this.queue.enq(object);
  }
}
