/**
 * A transformer with a parameter k that takes in an object x
 * and outputs the last k digits of the hash value of x.
 * CS2030S Exercise 4
 * AY25/26 Semester 2
 *
 * @author Bao Nguyen
 */
class LastDigitsOfHashCode implements Transformer<Object, Integer> {
  private int k;

  public LastDigitsOfHashCode(int k) {
    this.k = k;
  }

  @Override
  public Integer transform(Object obj) {
    int code = obj.hashCode();
    int res = code % (int) Math.pow(10, this.k);
    return res < 0 ? -res : res;
  }
}
