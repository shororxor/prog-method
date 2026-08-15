/**
 * The Transformer interface that can transform a type T
 * to type U.
 * CS2030S Exercise 4
 * AY25/26 Semester 2
 *
 * @author Bao Nguyen
 */

@FunctionalInterface
interface Transformer<T, R> {
  public R transform(T t);
}
