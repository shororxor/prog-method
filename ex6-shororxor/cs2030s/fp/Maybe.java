/**
 * CS2030S Exercise 5
 * AY25/26 Semester 2
 *
 * @author Bao Nguyen
 */

package cs2030s.fp;
import java.util.*;

public abstract class Maybe<T> {
  private static final Maybe<?> EMPTY = new None();

  protected abstract T get();
  public abstract T orElse(T other);
  public abstract T orElseGet(Producer<T> producer);
  public abstract void ifPresent(Consumer<T> consumer);

  public abstract Maybe<T> filter(BooleanCondition<? super T> condition);
  public abstract <R> Maybe<R> map(Transformer<? super T, ? extends R> transformer);
  public abstract <R> Maybe<R> flatMap(Transformer<? super T, ? extends Maybe<? extends R>> transformer);

  public static<R> Maybe<R> none() {
    @SuppressWarnings("unchecked")
    Maybe<R> r = (Maybe<R>) EMPTY;
    return r;
  }

  public static<R> Maybe<R> some(R r) {
    Maybe<R> res = new Some<R>(r);
    return res;
  }

  public static<R> Maybe<R> of(R r) {
    if (r == null) {
      return Maybe.<R>none();
    } else {
      return Maybe.<R>some(r);
    }
  }

  private static class None extends Maybe<Object> {
    @Override
    protected Object get() throws NoSuchElementException {
      throw new NoSuchElementException();
    }

    @Override
    public Object orElse(Object other) {
      return other;
    }

    @Override
    public Object orElseGet(Producer<Object> producer) {
      return producer.produce();
    }

    @Override
    public void ifPresent(Consumer<Object> consumer) {
      return;
    }

    @Override
    public Maybe<Object> filter(BooleanCondition<? super Object> condition) {
      return Maybe.<Object>none();
    }

    @Override
    public <R> Maybe<R> map(Transformer<? super Object, ? extends R> transformer) {
      return Maybe.<R>none();
    }

    @Override
    public <R> Maybe<R> flatMap(Transformer<? super Object, ? extends Maybe<? extends R>> transformer) {
      return Maybe.<R>none();
    }

    @Override
    public String toString() {
      return "[]";
    }

    @Override
    public boolean equals(Object obj) {
      return obj == EMPTY;
    }

  }

  private static final class Some<T> extends Maybe<T> {
    private T value;

    public Some(T value) {
      this.value = value;
    }

    @Override
    protected T get() {
      return this.value;
    }

    @Override
    public T orElse(T other) {
      return this.get();
    }

    @Override
    public T orElseGet(Producer<T> producer) {
      return this.get();
    }

    @Override
    public void ifPresent(Consumer<T> consumer) {
      consumer.consume(this.get());
    }

    @Override
    public Maybe<T> filter(BooleanCondition<? super T> condition) {
      if (this.get() != null && !condition.test(this.get())) {
        return Maybe.<T>none();
      }
      return this;
    }

    @Override
    public <R> Maybe<R> map(Transformer<? super T, ? extends R> transformer) {
      return Maybe.<R>some(transformer.transform(this.get())); 
    }

    @Override
    public <R> Maybe<R> flatMap(Transformer<? super T, ? extends Maybe<? extends R>> transformer) {
      @SuppressWarnings("unchecked")
      Maybe<R> res = (Maybe<R>) transformer.transform(this.get());
      return res;
    }

    @Override
    public String toString() {
      return value == null ? "[null]" : String.format("[%s]", value.toString());
    }

    @Override
    public boolean equals(Object obj)  {
      if (this == obj) {
        return true;
      }
      return obj instanceof Some<?> other 
        && Objects.equals(this.get(), other.get());
    }
  }
}
