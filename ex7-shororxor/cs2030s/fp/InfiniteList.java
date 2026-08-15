package cs2030s.fp;
import java.util.List;
import java.util.*;

public class InfiniteList<T> {

  private static final InfiniteList<?> SENTINEL = new Sentinel();
  private Lazy<Maybe<T>> head;
  private Lazy<InfiniteList<T>> tail;

  private InfiniteList() {
    this.head = null;
    this.tail = null;
  }

  private InfiniteList(T head, Producer<InfiniteList<T>> tail) {
    this.head = Lazy.<Maybe<T>>of(Maybe.<T>some(head));
    this.tail = Lazy.<InfiniteList<T>>of(tail);
  }

  private InfiniteList(Lazy<Maybe<T>> head, Lazy<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }

  public static <T> InfiniteList<T> generate(Producer<T> producer) {
    return new InfiniteList<T>(
        Lazy.<Maybe<T>>of(() -> Maybe.<T>some(producer.produce())),
        Lazy.<InfiniteList<T>>of(() -> generate(producer))
        );
  }

  public static <T> InfiniteList<T> iterate(T seed, Transformer<T, T> next) {
    return new InfiniteList<T>(
        Lazy.<Maybe<T>>of(() -> Maybe.<T>some(seed)),
        Lazy.<InfiniteList<T>>of(() -> iterate(next.transform(seed), next))
        );
  }

  public static <T> InfiniteList<T> iterate2(T seed, Transformer<T, T> next) {
    return new InfiniteList<T>(
        seed,
        () -> iterate(next.transform(seed), next)
        );
  }

  public static <T> InfiniteList<T> sentinel() {
    @SuppressWarnings("unchecked")
    InfiniteList<T> res = (InfiniteList<T>) SENTINEL;
    return res;
  }

  public T head() {
    Maybe<T> h = this.head.get();
    return h.equals(Maybe.EMPTY) ? this.tail.get().head() : h.get();
  }

  public InfiniteList<T> tail() {
    Maybe<T> h = this.head.get();
    return h.equals(Maybe.EMPTY) ? this.tail.get().tail() : this.tail.get();
  }

  public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
    if (this == SENTINEL) {
      return InfiniteList.<R>sentinel();
    }
    return new InfiniteList<R> (
        Lazy.<Maybe<R>>of(() -> this.head.get().<R>map(mapper)),
        Lazy.<InfiniteList<R>>of(() -> this.tail.get().<R>map(mapper))
        );
  }

  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
    return new InfiniteList<T> ( 
        Lazy.<Maybe<T>>of(() -> this.head.get().filter(predicate)),
        Lazy.<InfiniteList<T>>of(() -> this.tail.get().filter(predicate))
        );
  }

  public InfiniteList<T> limit(long n) {
    if (n <= 0) {
      return InfiniteList.<T>sentinel();
    }

    T h = this.head();

    return new InfiniteList<T> (
        Lazy.<Maybe<T>>of(() -> Maybe.<T>some(h)),
        Lazy.<InfiniteList<T>>of(
          () -> n == 1 
          ? InfiniteList.<T>sentinel()
          : this.tail().limit(n - 1) 
          )
        );
  }

  public List<T> toList() {
    List<T> list = new ArrayList<T>();

    if (this == SENTINEL) {
      return list;
    }

    T h = this.head();
    list.add(h);
    
    List<T> list2 = this.tail().toList();
    list.addAll(list2);
    return list;
  }

  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
    T h = this.head();
    if (predicate.test(h)) {
      return new InfiniteList<T>(
          Lazy.<Maybe<T>>of(() -> Maybe.<T>some(h)),
          Lazy.<InfiniteList<T>>of(() -> this.tail().takeWhile(predicate))
      );
    } else {
      return InfiniteList.<T>sentinel();
    }
  }

  public boolean isSentinel() {
    return false;
  }

  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
    if (this == SENTINEL) {
      return identity;
    }
  
    U res = accumulator.combine(identity, this.head());
    return this.tail().reduce(res, accumulator);
  }

  public long count() {
    if (this == SENTINEL) {
      return 0;
    }

    return this.reduce(0, (x, y) -> x + 1);
  }

  public String toString() {
    return "[" + this.head.toString() + " " + this.tail.toString() + "]";
  }

  private static class Sentinel extends InfiniteList<Object> {
    public Sentinel() {

    }

    @Override
    public boolean isSentinel() {
      return true;
    }

    @Override
    public Object head() throws NoSuchElementException {
      throw new NoSuchElementException();
    }

    @Override
    public Sentinel tail() throws NoSuchElementException {
      throw new NoSuchElementException();
    }

    @Override
    public Sentinel filter(BooleanCondition<? super Object> condition) {
      return this;
    }

    @Override 
    public <R> InfiniteList<R> map(Transformer<? super Object, ? extends R> mapper) {
      return InfiniteList.<R>sentinel();
    }

    @Override
    public Sentinel limit(long n) {
      return this;
    }

    @Override
    public List<Object> toList() {
      return List.of();
    }
    
    @Override
    public Sentinel takeWhile(BooleanCondition<? super Object> predicate) {
      return this;
    }

    @Override
    public <U> U reduce(U identity, Combiner<U, ? super Object, U> accumulator) {
      return identity;
    }

    @Override
    public long count() {
      return 0;
    }

    @Override
    public String toString() {
      return "-";
    }
  }
}
