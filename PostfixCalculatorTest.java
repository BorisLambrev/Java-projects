package ca.mcgill.cccs315.assignment2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PostfixCalculatorTest {

	@Test
	void testEvaluatePostfixExpression() {
		assertEquals(7.0, PostfixCalculator.evaluatePostfixExpression("3 4 +"));
		assertEquals(8.0, PostfixCalculator.evaluatePostfixExpression("6 3 / 2 - 8 +"));
		assertEquals(25.0, PostfixCalculator.evaluatePostfixExpression("4 5 * 7 + 2 - 0 +"));
	}
}
