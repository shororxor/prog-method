/**
 * A generic box storing an item.
 * CS2030S Exercise 4
 * AY25/26 Semester 2
 *
 * @author Bao Nguyen
 */

import java.util.*;

class Box<T> {
  private final T item;
  private static final Box<?> EMPTY_BOX = new Box<>(null);

  public Box(T item) {
    this.item = item;
  }

  public static <R> Box<R> empty() {
    @SuppressWarnings("unchecked")
    Box<R> box = (Box<R>) EMPTY_BOX;
    return box;
  }

  public static <R> Box<R> of(R item) {
    if (item == null) {
      return null;
    }
    return new Box<R>(item);
  }

  public static <R> Box<R> ofNullable(R item) {
    if (item == null) {
      return Box.<R>empty();
    } else {
      return Box.<R>of(item);
    }
  }

  public T get() {
    return this.item;
  }

  public boolean isPresent() {
    return this.item == null ? false : true;
  }

  public Box<T> filter(BooleanCondition<? super T> condition) {
    if (this.equals(EMPTY_BOX) || !condition.test(this.item)) {
      return Box.<T>empty();
    } else {
      return this;
    }
  }

  public <R> Box<R> map(Transformer<? super T, ? extends R> transformer) {
    if (this.equals(EMPTY_BOX)) {
      return Box.<R>empty();
    } else {
      return Box.<R>ofNullable(transformer.transform(this.item));
    }
  }

  @Override
  public String toString() {
    if (this.item == null) {
      return String.format("[]");
    }
    return String.format("[%s]", this.item.toString());
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    return obj instanceof Box<?> other
           && Objects.equals(this.get(), other.get());
  }
}
