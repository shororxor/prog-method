/**
 * CS2030S Exercise 5
 * AY25/26 Semester 2
 *
 * @author Bao Nguyen
 */

package cs2030s.fp;

@FunctionalInterface
public interface Consumer<T> {
  public void consume(T t);
}
