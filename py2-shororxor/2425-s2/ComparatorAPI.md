# Comparator API (Abridged)
This abridged Comparator<T> API contains documentation for the following methods:

- compare

## compare

    int compare(T o1, T o2)

    Compares its two arguments for order. Returns a negative integer, zero, 
    or a positive integer as the first argument is less than, equal to, or 
    greater than the second.

    It is generally the case, but not strictly required that 
    (compare(x, y)==0) == (x.equals(y))

    Parameters:
        o1 - the first object to be compared.
        o2 - the second object to be compared.

    Returns:
        a negative integer, zero, or a positive integer as the first argument 
        is less than, equal to, or greater than the second.
