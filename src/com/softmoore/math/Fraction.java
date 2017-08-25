package com.softmoore.math;


/**
 * This class encapsulates mathematical fractions (or rational numbers).
 * Fractions have a numerator and a denominator, both of type long.
 * All fractions are normalized when they are constructed.  A normalized
 * fraction has a positive denominator and is in reduced form.  For
 * example, the fraction 2/4 is normalized to 1/2, and the fraction
 * 3/(-4) is normalized to (-3)/4.  Also, all fraction objects are
 * immutable;  they cannot be changed once they have been constructed.
 */
public final class Fraction implements Comparable<Fraction>
  {
    private final long numerator;
    private final long denominator;


    public static final Fraction ZERO = new Fraction(0);
    public static final Fraction ONE  = new Fraction(1);


    /**
     * Construct a fraction with the given numerator and with a denominator of 1.
     * @param numerator the numerator of the fraction.
     */
    public Fraction(long numerator)
      {
        this.numerator = numerator;
        denominator = 1;
      }


    /**
     * Constructs a fraction with the given numerator and den     * @param  numerator    the numerator of the fraction.
     ominator.
     * @param  denominator  the denominator of the fraction.
     * @throws IllegalArgumentException  if the denominator is 0.
     */
    public Fraction(long numerator, long denominator)
      {
        if (denominator == 0)
            throw new IllegalArgumentException("Fraction with zero in denominator");

        // Normalize the fraction.
        if (numerator == 0)
            denominator = 1;
        else
          {
            long divisor = gcd(numerator, denominator);

            numerator   = numerator/divisor;
            denominator = denominator/divisor;

            if (denominator < 0)
              {
                denominator = -denominator;
                numerator   = -numerator;
              }
          }

        this.numerator   = numerator;
        this.denominator = denominator;
      }


    /**
     * Returns the value of the fraction's numerator.
     */
    public long getNumerator()
      {
        return numerator;
      }


    /**
     * Returns the value of the fraction's denominator.
     */
    public long getDenominator()
      {
        return denominator;
      }


    /**
     * Returns value of the fraction converted to a double, where
     * the numerator is divided by the denominator.
     */
    public double toDouble()
      {
        return (double)numerator/(double)denominator;
      }


    /**
     * Returns a new Fraction that is the result of adding the
     * specified Fraction to this Fraction.
     *
     * @param f the Fraction to be added.
     */
    public Fraction add(Fraction f)
      {
        long numer, denom;

        numer = numerator*f.getDenominator() + denominator*f.getNumerator();
        denom = denominator*f.getDenominator();

        return new Fraction(numer, denom);
      }


    /**
     * Returns a new Fraction that is the result of subtracting the
     * specified Fraction from this Fraction.
     *
     * @param f the Fraction to be subtracted.
     */
    public Fraction subtract(Fraction f)
      {
        return
            add(f.negate());
      }


    /**
     * Returns a new Fraction that is the result of multiplying the
     * specified Fraction with this Fraction.
     *
     * @param f the Fraction to be multiplied.
     */
    public Fraction multiply(Fraction f)
      {
          long numer, denom;

          numer = numerator*f.getNumerator();
          denom = denominator*f.getDenominator();

          return new Fraction(numer, denom);
      }


    /**
     * Returns a new Fraction that is the result of dividing this
     * Fraction by the specified Fraction.
     *
     * @param f the Fraction to be used as the divisor.
     *
     * @throws IllegalArgumentException if the specified fraction
     *             is Fraction(0, 1).
     */
    public Fraction divide(Fraction f)
      {
        // divide = multiply by reciprocal.
        // Fraction constructor throws IllegalArgumentException for reciprocal with zero denominator.
        // Alternatively, could have checked for f.equals(ZERO) and thrown IllegalArgumentException before
        // trying to create reciprocal Fraction.
        return multiply(new Fraction(f.getDenominator(),f.getNumerator()));
      }


    /**
     * Returns a new Fraction that is the result of adding 1 to this Fraction.
     */
    public Fraction inc()
      {
        return new Fraction(numerator + denominator, denominator);
      }


    /**
     * Returns the negation of this Fraction.
     */
    public Fraction negate()
      {
        return new Fraction(-numerator,denominator);
      }


    /**
     * Returns a string representation for the fraction of the form
     * "n/d", where n is the numerator and d is the denominator.
     */
    @Override
    public String toString()
      {
        return Long.toString(numerator) + '/' + Long.toString(denominator);
      }


    /**
     * Compares this Fraction with the specified Fraction.
     *
     * @param   f the Fraction to be compared.
     * @return  a negative integer, zero, or a positive integer as this
     *          Fraction is less than, equal to, or greater than the
     *          specified Fraction.
     */
    public int compareTo(Fraction f)
      {
        long compare1 = numerator*f.getDenominator();
        long compare2 = denominator*f.getNumerator();

        return Long.compare(compare1,compare2);
      }


    @Override
    public int hashCode()
      {
        final int prime = 31;
        int result = 1;

        result = prime * result + (int) (denominator ^ (denominator >>> 32));
        result = prime * result + (int) (numerator ^ (numerator >>> 32));

        return result;
      }


    @Override
    public boolean equals(Object obj)
      {
        return (obj == this) ||
                ((obj instanceof Fraction) &&
                  compareTo((Fraction)obj) == 0);
      }


    /**
     * Compute the greatest common divisor of two longs.
     */
    private static long gcd(long a , long b)
      {
        long a1 = Math.abs(a);
        long b1 = Math.abs(b);
        long temp;

        while (b1 != 0)
          {
            temp = a1;
            a1 = b1;
            b1 = temp % b1;
          }
        return a1;
      }
  }