/**
 * A conditional statement that returns either true of false.
 * CS2030S Exercise 4
 * AY25/26 Semester 2
 *
 * @author Bao Nguyen
 */

@FunctionalInterface
interface BooleanCondition<T> {
  public boolean test(T t);
}
