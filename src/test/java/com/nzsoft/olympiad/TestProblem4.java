package com.nzsoft.olympiad;

import static com.nzsoft.olympiad.core.TestUtils.split;
import static com.nzsoft.olympiad.core.TestUtils.splitLines;
import static com.nzsoft.olympiad.core.TestUtils.toInt;

import org.junit.Before;

import com.nzsoft.olympiad.core.TestInput;
import com.nzsoft.olympiad.core.TestOutput;
import com.nzsoft.olympiad.core.TestRunner;

public class TestProblem4 extends TestRunner {

	private static final String TEST_FILE = "Problem4.txt";

	private static class TestInput4 implements TestInput {
		private int broiKraka;
		private int broiGlavi;
		private TestOutput4 expectedOutput;

		public TestInput4(String input) {
			String[] inputArgs = split(input);
			this.broiKraka = toInt(inputArgs[0]);
			this.broiGlavi = toInt(inputArgs[1]);
			this.expectedOutput = new TestOutput4(toInt(inputArgs[2]), toInt(inputArgs[3]));
		}

		@Override
		public TestOutput getExpectedOutput() {
			return expectedOutput;
		}
	}

	private static class TestOutput4 implements TestOutput {
		protected int broiKokoshki;
		protected int broiZaici;

		public TestOutput4(int broiKokoshki, int broiZaici) {
			this.broiKokoshki = broiKokoshki;
			this.broiZaici = broiZaici;
		}

		@Override
		public boolean equals(Object other) {
			if (other != null && (other instanceof TestOutput4)) {
				TestOutput4 otherOutput4 = (TestOutput4) other;
				return broiKokoshki == otherOutput4.broiKokoshki && broiZaici == otherOutput4.broiZaici;
			}
			return false;
		}
		
		@Override
		public String toString() {
			return String.format("[%s, %s]", this.broiKokoshki, this.broiZaici);
		}
	}

	@Override
	protected TestInput[] parseTestsInput() {
		String text = loadTextFile(TEST_FILE);
		String[] textLines = splitLines(text);
		TestInput[] result = new TestInput[textLines.length];
		for (int i = 0; i < textLines.length; i++) {
			result[i] = new TestInput4(textLines[i]);
		}
		return result;
	}

	private Problem4 problem;

	@Before
	public void setUp() throws Exception {
		problem = new Problem4();
	}

	@Override
	protected TestOutput getTestOutput(TestInput testInput) {
		TestInput4 testInput4 = (TestInput4) testInput;
		if (testInput4 != null) {
			int[] actualResult = problem.calculate(testInput4.broiKraka, testInput4.broiGlavi);
			return new TestOutput4(actualResult[0], actualResult[1]);
		} else {
			throw new RuntimeException("Did not receive correct test input");
		}
	}
}
