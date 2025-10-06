package ca.mcgill.cccs315.assignment2;

/**
 * Runs all the tests in PostfixCalculatorTest, PrefixCalculatorTest 
 * and PrePostConverters
 */


import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;


@Suite
@SelectClasses({ PostfixCalculatorTest.class, PrefixCalculatorTest.class, PrePostConvertersTest.class })
public class AllTests {

}
