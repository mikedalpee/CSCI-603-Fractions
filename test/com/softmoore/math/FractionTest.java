package com.softmoore.math;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class FractionTest {
    private static final Logger LOG = Logger.getLogger(FractionTest.class.getName());

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
                    LOG.info("  Divide postive Fraction by negative Fraction");
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
    }

    @Test
    void negateTest() {
    }

    @Test
    void toStringTest() {
    }

    @Test
    void hashCodeTest() {
    }

    @Test
    void equalsTest() {
    }
}