// A03225500W

public abstract class Program<T> {

  abstract T get();
  abstract boolean isResult();

  public static <R> Program<R> result(R value) {
    return new Result<R>(value);
  }

  private static class Result<T> extends Program<T> {
    private T value;

    public Result(T value) {
      this.value = value;
    }

    @Override
    public T get() {
      return this.value;
    }

    @Override
    public boolean isResult() {
      return true;
    }
  }
}
