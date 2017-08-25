package test;

import com.softmoore.math.Fraction;
import java.util.*;

public class TestFraction
{
    public static void main(String[] args)
    {
        long numerator, denominator;
        Scanner in = new Scanner(System.in);
        Fraction f1, f2;

        do
        {
            // Modified to enable testing for Fraction's IllegalArgumentException and
            // to recover from bad input (InputMismatchException).
            try {
                // Modified to correct termination condition guidance,
                // since inputs other that just 0 1 0 1 can cause termination
                System.out.println("Enter four integers representing two fractions (both with zero numerators to terminate):  ");

                numerator = in.nextLong();
                denominator = in.nextLong();
                f1 = new Fraction(numerator, denominator);

                numerator = in.nextInt();
                denominator = in.nextInt();
                f2 = new Fraction(numerator, denominator);

                // Modified to make termination check DRY by putting logic in once place

                if (f1.equals(Fraction.ZERO) && f2.equals(Fraction.ZERO)) {
                    break;
                }

                System.out.println("f1 = " + f1 + "    f2 = " + f2);
                System.out.println();

                System.out.println("f1.toDouble() = " + f1.toDouble()
                        + "    f2.toDouble() = " + f2.toDouble());
                System.out.println();

                System.out.println("f1 == f2 is " + (f1 == f2));
                System.out.println("f1.equals(f2) is " + f1.equals(f2));
                System.out.println("f1.compareTo(f2) is " + f1.compareTo(f2));
                System.out.println();

                System.out.println("-f1 = " + f1.negate());
                System.out.println();

                System.out.println("f1 + f2 = " + f1.add(f2));
                System.out.println("f1 - f2 = " + f1.subtract(f2));
                System.out.println("f1 * f2 = " + f1.multiply(f2));
                System.out.println("f1 / f2 = " + f1.divide(f2));

                System.out.println();
            }
            catch (IllegalArgumentException e)
            {
                // Print out stack trace to show origin of error

                e.printStackTrace(System.out);
            }
            catch (InputMismatchException e)
            {
                System.out.println("** Error ** non-integer input given");
            }

            // Clear out remaining input if any left due to exception or too much entered

            in.skip(".*");
        }
        // Modified to make termination check DRY by putting logic in one place
        while (true);

        // Fraction f = new Fraction(6.2);      // should not be permitted
        in.close();
    }
}