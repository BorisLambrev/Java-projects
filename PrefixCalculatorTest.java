package ca.mcgill.cccs315.assignment2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PrefixCalculatorTest {

	@Test
	void testEvaluatePrefixExpression() {
		assertEquals(7.0, PrefixCalculator.evaluatePrefixExpression("+ 3 4"));
		assertEquals(8.0, PrefixCalculator.evaluatePrefixExpression("- + 8 / 6 3 2"));
		assertEquals(25.0, PrefixCalculator.evaluatePrefixExpression("- + 7 * 4 5 + 2 0"));
	}
}
