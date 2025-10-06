package ca.mcgill.cccs315.assignment2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PrePostConvertersTest {

	@Test
	void testPostfixToPrefix() {
		assertEquals("+ 3 4", PrePostConverters.postfixToPrefix("3 4 +"));
		assertEquals("+ - / 6 3 2 8", PrePostConverters.postfixToPrefix("6 3 / 2 - 8 +"));
		assertEquals("+ - + * 4 5 7 2 0", PrePostConverters.postfixToPrefix("4 5 * 7 + 2 - 0 +"));
	}
	
	@Test
	void testPrefixToPostfix() {
		assertEquals("3 4 +", PrePostConverters.prefixToPostfix("+ 3 4"));
		assertEquals("8 6 3 / + 2 -", PrePostConverters.prefixToPostfix("- + 8 / 6 3 2"));
		assertEquals("7 4 5 * + 2 0 + -", PrePostConverters.prefixToPostfix("- + 7 * 4 5 + 2 0"));
	}

}
