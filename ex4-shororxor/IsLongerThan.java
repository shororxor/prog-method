/**
 * A boolean condition with parameter x that can be applied to
 * a string.  Returns true if the string is longer than x; false
 * otherwise.
 * CS2030S Exercise 4
 * AY25/26 Semester 2
 *
 * @author Bao Nguyen
 */
class IsLongerThan implements BooleanCondition<String> {
  private int limit;

  public IsLongerThan(int limit) {
    this.limit = limit;
  }

  @Override
  public boolean test(String s) {
    return s.length() > this.limit;
  }
}
