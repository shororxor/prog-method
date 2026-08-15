/**
 * A boolean condition with an integer parameter y that can be
 * apply to another integer x.  Returns true if x is divisible
 * by y, false otherwise.
 * CS2030S Exercise 4
 * AY25/26 Semester 2
 *
 * @author Bao Nguyen
 */
class IsDivisibleBy implements BooleanCondition<Integer> {
  private int num;

  public IsDivisibleBy(int num) {
    this.num = num;
  }

  @Override
  public boolean test(Integer n) {
    return n % this.num == 0;
  }
}
