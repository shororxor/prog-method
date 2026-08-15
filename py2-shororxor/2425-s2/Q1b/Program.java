// A03225500W

public abstract class Program<T> {

  abstract T get();
  abstract boolean isResult();

  public static <T> Program<T> result(T value) {
    return new Result<T>(value);
  }

  public static <T, R> Program<R> chain(Program<? extends T> program, Transformer<? super T, ? extends Program<? extends R>> transformer) {
    return new Chain<T, R>(program, transformer);
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

  private static class Chain<T, R> extends Program<R> {

    private Program<? extends T> program;
    private Transformer<? super T, ? extends Program<? extends R>> transformer;

    public Chain(Program<? extends T> program, Transformer<? super T, ? extends Program<? extends R>> transformer) {
      this.program = program;
      this.transformer = transformer;
    }

    @Override
    public R get() {
      return this.transformer.transform(this.program.get()).get();
    }

    @Override
    public boolean isResult() {
      return false;
    }
  }
}
