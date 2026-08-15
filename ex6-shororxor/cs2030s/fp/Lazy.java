package cs2030s.fp;
import java.util.*;

public class Lazy<T> {
  private Producer<? extends T> producer;
  private Maybe<T> value;

  private Lazy(T value) {
    this.value = Maybe.<T>some(value);
  }

  private Lazy(Producer<? extends T> producer) {
    this.producer = producer;
    this.value = Maybe.<T>none();
  }

  public static<T> Lazy<T> of(T value) {
    return new Lazy<T>(value);
  }

  public static<T> Lazy<T> of(Producer<? extends T> producer) {
    return new Lazy<T>(producer);
  }

  public T get() {
    return this.value
      .orElseGet(
        () ->  { 
          this.value = Maybe.<T>some(this.producer.produce());
          return this.value.get();
        }
      );
  }

  public <R> Lazy<R> map(Transformer<? super T, ? extends R> transformer) {
    return Lazy.<R>of(() -> transformer.transform(this.get()));
  }

  public <R> Lazy<R> flatMap(Transformer<? super T, ? extends Lazy<? extends R>> transformer) {
    return Lazy.<R>of(() -> transformer.transform(this.get()).get()); 
  }
  
  public Lazy<Boolean> filter(BooleanCondition<? super T> condition) {
    return Lazy.<Boolean>of(() -> condition.test(this.get()));
  }

  public <S,R> Lazy<R> combine(Lazy<S> other, Combiner<? super T,? super S, ? extends R> combiner) {
    return Lazy.<R>of(() -> combiner.combine(this.get(), other.get()));
  }

  @Override
  public String toString() {
    return this.value
        .<String>map(x -> String.valueOf(x))
        .orElse("?");
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    return obj instanceof Lazy<?> other
        && Objects.equals(this.get(), other.get());
  }
}
