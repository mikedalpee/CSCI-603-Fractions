package com.softmoore.math;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.jupiter.api.Assertions.*;

class FractionTest {
    private static final Logger LOG = LogManager.getLogger(FractionTest.class.getName());

    @Test
    void createFractionTest1() {
        LOG.info(
                "Testing Constructor: Fraction(long numerator)");
        assertAll(
                "Fraction(long numerator)",
                ()->
                {
                    LOG.info("  Fraction with positive numerator");
                    Fraction f = new Fraction(5);
                    assertAll(
                            "positive numerator",
                            ()-> assertTrue(f.getNumerator()==5,"Bad Numerator"),
                            ()-> assertTrue(f.getDenominator() == 1,"Bad Denominator"));
                },
                ()->
                {
                    LOG.info("  Fraction with zero numerator");
                    Fraction f = new Fraction(0);
                    assertAll(
                            "zero numerator",
                            ()-> assertTrue(f.getNumerator()==0,"Bad Numerator"),
                            ()-> assertTrue(f.getDenominator() == 1,"Bad Denominator"));
                },
                ()->
                {
                    LOG.info("  Fraction with negative numerator");
                    Fraction f = new Fraction(-5);
                    assertAll(
                            "negative numerator",
                            ()-> assertTrue(f.getNumerator()==-5,"Bad Numerator"),
                            ()-> assertTrue(f.getDenominator() == 1,"Bad Denominator"));
                });
    }

    @Test
    void createFractionTest2() {
        LOG.info(
                "Testing Constructor: Fraction(long numerator,long denominator)");
        assertAll(
                "Fraction(long numerator,long denominator)",
                ()->
                {
                    LOG.info("  Fraction with prime numerator and denominator");
                    Fraction f = new Fraction(7,3);
                    assertAll(
                            "prime numerator and denominator",
                            ()-> assertTrue(f.getNumerator() == 7,"Bad Numerator"),
                            ()-> assertTrue(f.getDenominator() == 3,"Bad Denominator"));
                },
                ()->
                {
                    LOG.info("  Fraction with non-prime numerator and denominator");
                    Fraction f = new Fraction(28,14);
                    assertAll(
                            "non-prime numerator and denominator",
                            ()-> assertTrue(f.getNumerator() == 2, "Bad Numerator"),
                            ()-> assertTrue(f.getDenominator() == 1, "Bad Denominator"));
                },
                ()->
                {
                    LOG.info("  Fraction with zero in denominator (throws IllegalArgumentException)");
                    Throwable e =
                            assertThrows(
                                    IllegalArgumentException.class,
                                    ()-> new Fraction(1,0));
                    assertEquals("Fraction with zero in denominator",e.getMessage());
                },
                ()->
                {
                    LOG.info("  Fraction with zero in numerator");
                    Fraction f = new Fraction(0,10);
                    assertAll(
                            "zero in denominator",
                            ()-> assertTrue(f.getNumerator() == 0, "Bad Numerator"),
                            ()-> assertTrue(f.getDenominator() == 1, "Bad Denominator"));
                },
                ()->
                {
                    LOG.info("  Fraction with negative numerator, positive denominator");
                    Fraction f = new Fraction(-5,2);
                    assertAll(
                            "negative numerator, positive denominator",
                            ()-> assertTrue(f.getNumerator() == -5, "Bad Numerator"),
                            ()-> assertTrue(f.getDenominator() == 2, "Bad Denominator"));
                },
                ()->
                {
                    LOG.info("  Fraction with positive numerator, negative denominator");
                    Fraction f = new Fraction(5,-2);
                    assertAll(
                            "negative numerator, negative denominator",
                            ()-> assertTrue(f.getNumerator() == -5, "Bad Numerator"),
                            ()-> assertTrue(f.getDenominator() == 2, "Bad Denominator"));
                },
                ()->
                {
                    LOG.info("  Fraction with negative numerator, negative denominator");
                    Fraction f = new Fraction(-5,-2);
                    assertAll(
                            "negative numerator, negative denominator",
                            ()-> assertTrue(f.getNumerator() == 5, "Bad Numerator"),
                            ()-> assertTrue(f.getDenominator() == 2, "Bad Denominator"));
                }
                );
    }

    @Test
    void toDoubleTest() {
        LOG.info(
                "Testing Fraction.toDouble()");
        assertAll(
                "Fraction.toDouble()",
                ()->
                {
                    LOG.info("  Fraction with zero in numerator");
                    Fraction f = new Fraction(0,10);
                    assertTrue(f.toDouble() == 0.0, "Bad Conversion");
                },
                ()->
                {
                    LOG.info("  Fraction with positive numerator, positive denominator");
                    Fraction f = new Fraction(1,2);
                    assertTrue(f.toDouble() == 0.5, "Bad Conversion");
                },
                ()->
                {
                    LOG.info("  Fraction with negative numerator, positive denominator");
                    Fraction f = new Fraction(-1,2);
                    assertTrue(f.toDouble() == -0.5, "Bad Conversion");
                },
                ()->
                {
                    LOG.info("  Fraction with positive numerator, negative denominator");
                    Fraction f = new Fraction(1,-2);
                    assertTrue(f.toDouble() == -0.5, "Bad Conversion");
                },
                ()->
                {
                    LOG.info("  Fraction with negative numerator, negative denominator");
                    Fraction f = new Fraction(-1,-2);
                    assertTrue(f.toDouble() == 0.5, "Bad Conversion");
                 }
        );
    }

    @Test
    void addTest() {
        LOG.info(
                "Testing Fraction.add(Fraction f)");
        assertAll(
                "Fraction.add(Fraction f)",
                ()->
                {
                    LOG.info("  Add zero Fraction to non-zero Fraction");
                    Fraction f = new Fraction(1,2);
                    assertTrue(f.add(Fraction.ZERO).equals(f), "Addition Error");
                },
                ()->
                {
                    LOG.info("  Add non-zero Fraction to zero Fraction");
                    Fraction f = new Fraction(1,2);
                    assertTrue(Fraction.ZERO.add(f).equals(f), "Addition Error");
                },
                ()->
                {
                    LOG.info("  Add positive Fraction to positive Fraction");
                    Fraction f1 = new Fraction(1,2);
                    Fraction f2 = new Fraction(1,2);
                    assertTrue(f1.add(f2).equals(Fraction.ONE), "Addition Error");
                },
                ()->
                {
                    LOG.info("  Add negative Fraction to positive Fraction");
                    Fraction f1 = new Fraction(1,2);
                    Fraction f2 = new Fraction(-1,2);
                    assertTrue(f1.add(f2).equals(Fraction.ZERO), "Addition Error");
                },
                ()->
                {
                    LOG.info("  Add positive Fraction to negative Fraction");
                    Fraction f1 = new Fraction(-1,2);
                    Fraction f2 = new Fraction(1,2);
                    assertTrue(f1.add(f2).equals(Fraction.ZERO), "Addition Error");
                },
                ()->
                {
                    LOG.info("  Add negative Fraction to negative Fraction");
                    Fraction f1 = new Fraction(-1,2);
                    Fraction f2 = new Fraction(-1,2);
                    assertTrue(f1.add(f2).equals(Fraction.ONE.negate()), "Addition Error");
                }
         );
    }

    @Test
    void subtractTest() {
        LOG.info(
                "Testing Fraction.subtract(Fraction f)");
        assertAll(
                "Fraction.subtract(Fraction f)",
                ()->
                {
                    LOG.info("  Subtract zero Fraction from non-zero Fraction");
                    Fraction f = new Fraction(1,2);
                    assertTrue(f.subtract(Fraction.ZERO).equals(f), "Subtraction Error");
                },
                ()->
                {
                    LOG.info("  Subtract non-zero Fraction from zero Fraction");
                    Fraction f = new Fraction(1,2);
                    assertTrue(Fraction.ZERO.subtract(f).equals(f.negate()), "Subtraction Error");
                },
                ()->
                {
                    LOG.info("  Subtract positive Fraction from positive Fraction");
                    Fraction f1 = new Fraction(1,2);
                    Fraction f2 = new Fraction(1,2);
                    assertTrue(f1.subtract(f2).equals(Fraction.ZERO), "Subtraction Error");
                },
                ()->
                {
                    LOG.info("  Subtract negative Fraction from positive Fraction");
                    Fraction f1 = new Fraction(1,2);
                    Fraction f2 = new Fraction(-1,2);
                    assertTrue(f1.subtract(f2).equals(Fraction.ONE), "Subtraction Error");
                },
                ()->
                {
                    LOG.info("  Subtract positive Fraction from negative Fraction");
                    Fraction f1 = new Fraction(-1,2);
                    Fraction f2 = new Fraction(1,2);
                    assertTrue(f1.subtract(f2).equals(Fraction.ONE.negate()), "Subtraction Error");
                },
                ()->
                {
                    LOG.info("  Subtract negative Fraction from negative Fraction");
                    Fraction f1 = new Fraction(-1,2);
                    Fraction f2 = new Fraction(-1,2);
                    assertTrue(f1.subtract(f2).equals(Fraction.ZERO), "Subtraction Error");
                }
        );
    }

    @Test
    void multiplyTest() {
        Fraction ONE_FORTH = new Fraction(1,4);
        LOG.info(
                "Testing Fraction.multiply(Fraction f)");
        assertAll(
                "Fraction.multiply(Fraction f)",
                ()->
                {
                    LOG.info("  Multiply non-zero Fraction by zero Fraction");
                    Fraction f = new Fraction(1,2);
                    assertTrue(f.multiply(Fraction.ZERO).equals(Fraction.ZERO), "Multiplication Error");
                },
                ()->
                {
                    LOG.info("  Multiply zero Fraction by non-zero Fraction");
                    Fraction f = new Fraction(1,2);
                    assertTrue(Fraction.ZERO.multiply(f).equals(Fraction.ZERO), "Multiplication Error");
                },
                ()->
                {
                    LOG.info("  Multiply positive Fraction by positive Fraction");
                    Fraction f1 = new Fraction(1,2);
                    Fraction f2 = new Fraction(1,2);
                    assertTrue(f1.multiply(f2).equals(ONE_FORTH), "Multiplication Error");
                },
                ()->
                {
                    LOG.info("  Multiply positive Fraction by negative Fraction");
                    Fraction f1 = new Fraction(1,2);
                    Fraction f2 = new Fraction(-1,2);
                    assertTrue(f1.multiply(f2).equals(ONE_FORTH.negate()), "Multiplication Error");
                },
                ()->
                {
                    LOG.info("  Multiply negative Fraction by positive Fraction");
                    Fraction f1 = new Fraction(-1,2);
                    Fraction f2 = new Fraction(1,2);
                    assertTrue(f1.multiply(f2).equals(ONE_FORTH.negate()), "Multiplication Error");
                },
                ()->
                {
                    LOG.info("  Multiply negative Fraction by negative Fraction");
                    Fraction f1 = new Fraction(-1,2);
                    Fraction f2 = new Fraction(-1,2);
                    assertTrue(f1.multiply(f2).equals(ONE_FORTH), "Multiplication Error");
                }
        );
    }

    @Test
    void divideTest() {
        LOG.info(
                "Testing Fraction.divide(Fraction f)");
        assertAll(
                "Fraction.divide(Fraction f)",
                ()->
                {
                    LOG.info("  Divide zero Fraction by non-zero Fraction");
                    Fraction f = new Fraction(1,2);
                    assertTrue(Fraction.ZERO.divide(f).equals(Fraction.ZERO), "Division Error");
                },
                ()->
                {
                    LOG.info("  Divide non-zero Fraction by zero Fraction");
                    Fraction f = new Fraction(1,2);
                    Throwable e = assertThrows(IllegalArgumentException.class,()->f.divide(Fraction.ZERO));
                    assertEquals("Fraction with zero in denominator",e.getMessage());
                },
                ()->
                {
                    LOG.info("  Divide positive Fraction by positive Fraction");
                    Fraction f1 = new Fraction(1,2);
                    Fraction f2 = new Fraction(1,2);
                    assertTrue(f1.divide(f2).equals(Fraction.ONE), "Division Error");
                },
                ()->
                {
                    LOG.info("  Divide positive Fraction by negative Fraction");
                    Fraction f1 = new Fraction(1,2);
                    Fraction f2 = new Fraction(-1,2);
                    assertTrue(f1.divide(f2).equals(Fraction.ONE.negate()), "Division Error");
                },
                ()->
                {
                    LOG.info("  Divide negative Fraction by positive Fraction");
                    Fraction f1 = new Fraction(-1,2);
                    Fraction f2 = new Fraction(1,2);
                    assertTrue(f1.divide(f2).equals(Fraction.ONE.negate()), "Division Error");
                },
                ()->
                {
                    LOG.info("  Divide negative Fraction to negative Fraction");
                    Fraction f1 = new Fraction(-1,2);
                    Fraction f2 = new Fraction(-1,2);
                    assertTrue(f1.divide(f2).equals(Fraction.ONE), "Division Error");
                }
        );
    }

    @Test
    void incTest() {
        LOG.info(
                "Testing Fraction.inc()");
        assertAll(
                "Fraction.inc()",
                ()->
                {
                    LOG.info("  Inc zero Fraction");
                     assertTrue(Fraction.ZERO.inc().equals(Fraction.ONE), "Increment Error");
                },
                ()->
                {
                    LOG.info("  Inc positive Fraction");
                    Fraction f1 = new Fraction(1,2);
                    assertTrue(f1.inc().equals(new Fraction(3,2)), "Increment Error");
                },
                ()->
                {
                    LOG.info("  Inc negative Fraction");
                    Fraction f1 = new Fraction(-1,2);
                    assertTrue(f1.inc().equals(new Fraction(1,2)), "Increment Error");
                }
        );
    }

    @Test
    void negateTest() {
        LOG.info(
                "Testing Fraction.negate()");
        assertAll(
                "Fraction.negate()",
                ()->
                {
                    LOG.info("  Negate zero Fraction");
                     assertTrue(Fraction.ZERO.negate().equals(Fraction.ZERO), "Negate Error");
                },
                ()->
                {
                    LOG.info("  Negate positive Fraction");
                    Fraction f1 = new Fraction(1,2);
                    assertTrue(f1.negate().equals(new Fraction(-1,2)), "Negate Error");
                },
                ()->
                {
                    LOG.info("  Negate negative Fraction");
                    Fraction f1 = new Fraction(-1,2);
                    assertTrue(f1.negate().equals(new Fraction(1,2)), "Negate Error");
                }
        );
    }

    @Test
    void toStringTest() {
        LOG.info(
                "Testing Fraction.toString()");
        assertAll(
                "Fraction.toString()",
                ()->
                {
                    LOG.info("  ToString zero Fraction");
                    assertTrue(Fraction.ZERO.toString().equals("0/1"), "ToString Error");
                },
                ()->
                {
                    LOG.info("  ToString positive Fraction");
                    Fraction f1 = new Fraction(1,2);
                    assertTrue(f1.toString().equals("1/2"), "ToString Error");
                },
                ()->
                {
                    LOG.info("  ToString negative Fraction");
                    Fraction f1 = new Fraction(-1,2);
                    assertTrue(f1.toString().equals("-1/2"), "ToString Error");
                }
        );
    }

    @Test
    void hashCodeTest() {
        LOG.info(
                "Testing Fraction.hashCode()");

        // For fun, compute the efficiency of the hashCode algorithm by randomly
        // generating one million unique fractions and counting any collisions that
        // occur

        Map<Integer,List<String>> hashes = new HashMap<>();
        long collisions = 0;
        long entries = 0;
        long duplicates = 0;
        long zeroes = 0;

        Random rand = new Random(0); // generate the same sequence every test

        do
        {
            long numerator = rand.nextLong();

            // make sure denominator is not zero

            long denominator = rand.nextLong();

            while (denominator == 0)
            {
                zeroes++;

                denominator = rand.nextLong();
             }

            Fraction f = new Fraction(numerator,denominator);
            String   fStr = f.toString();

            //System.out.println(fStr);

            int hash = new Fraction(numerator,denominator).hashCode();

            // check for collision

            if (hashes.containsKey(hash))
            {
                List<String> fractions = hashes.get(hash);

                if (fractions.contains(fStr))
                {
                    // generated a duplicate normalized fraction - ignore.
                    duplicates++;
                    continue;
                }

                // a collision occurred

                fractions.add(fStr);

                entries++;
                collisions++;
            }
            else
            {
                // this fraction is unique

                List<String> fractions = new ArrayList<>();

                fractions.add(fStr);

                hashes.put(hash,fractions);

                entries++;
            }
        }
        while (entries < 1000000);

        double hashEfficiency = (1.0 - (double)collisions/(double)entries)*100.0;

        LOG.info(
                "Fraction.hashCode() experienced "+
                Long.toString(collisions)+
                " collisions out of "+
                Long.toString(entries)+
                " unique fractions - efficiency: "+
                Double.toString(hashEfficiency)+
                "%.\nThe test generated "+
                Long.toString(duplicates)+
                " duplicate normalized Fractions and "+
                Long.toString(zeroes)+
                " zero denominators");
   }

    @Test
    void compareToTest() {
        LOG.info(
                "Testing Fraction.compareTo()");
        assertAll(
                "Fraction.compareTo()",
                ()->
                {
                    LOG.info("  Compare smaller Fraction to larger fraction");
                    Fraction f1 = new Fraction(1,3);
                    Fraction f2 = new Fraction(1,2);
                    assertTrue(f1.compareTo(f2) < 0, "Comparison Error");
                },
                ()->
                {
                    LOG.info("  Compare larger Fraction to smaller fraction");
                    Fraction f1 = new Fraction(1,2);
                    Fraction f2 = new Fraction(1,3);
                    assertTrue(f1.compareTo(f2) > 0, "Comparison Error");
                },
                ()->
                {
                    LOG.info("  Compare two Fractions with same value");
                    Fraction f1 = new Fraction(1,2);
                    Fraction f2 = new Fraction(1,2);
                    assertTrue(f1.compareTo(f2) == 0, "Comparison Error");
                },
                ()->
                {
                    LOG.info("  Compare Fraction to itself");
                    Fraction f1 = new Fraction(1,2);
                    assertTrue(f1.compareTo(f1) == 0, "Comparison Error");
                }
        );
    }
    
    @Test
    void equalsTest() {
        LOG.info(
                "Testing Fraction.equals()");
        assertAll(
                "Fraction.equals()",
                ()->
                {
                    LOG.info("  Compare smaller Fraction to larger fraction for equality");
                    Fraction f1 = new Fraction(1,3);
                    Fraction f2 = new Fraction(1,2);
                    assertFalse(f1.equals(f2), "Equality Error");
                },
                ()->
                {
                    LOG.info("  Compare larger Fraction to smaller fraction for equality");
                    Fraction f1 = new Fraction(1,2);
                    Fraction f2 = new Fraction(1,3);
                    assertFalse(f1.equals(f2), "Equality Error");
                },
                ()->
                {
                    LOG.info("  Compare two Fractions with same value equality");
                    Fraction f1 = new Fraction(1,2);
                    Fraction f2 = new Fraction(1,2);
                    assertTrue(f1.equals(f2), "Equality Error");
                },
                ()->
                {
                    LOG.info("  Compare Fraction to itself for equality");
                    Fraction f1 = new Fraction(1,2);
                    assertTrue(f1.equals(f1), "Equality Error");
                },
                ()->
                {
                    LOG.info("  Compare Fraction to null for equality");
                    Fraction f1 = new Fraction(1,2);
                    assertFalse(f1.equals(null), "Equality Error");
                },
                ()->
                {
                    LOG.info("  Compare Fraction to non-Fraction for equality");
                    Fraction f1 = new Fraction(1,2);
                    assertFalse(f1.equals("1/2"), "Equality Error");
                }
        );
    }
}