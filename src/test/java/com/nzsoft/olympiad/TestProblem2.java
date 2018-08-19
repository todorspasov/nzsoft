package com.nzsoft.olympiad;

import java.util.Arrays;

import org.junit.Before;

import com.nzsoft.olympiad.core.TestInput;
import com.nzsoft.olympiad.core.TestOutput;
import com.nzsoft.olympiad.core.TestRunner;
import static com.nzsoft.olympiad.core.TestUtils.*;

public class TestProblem2 extends TestRunner {

	private static final String TEST_FILE = "Problem2.txt";

	private static class TestInput2 implements TestInput {
		private int[] a;
		private TestOutput2 expectedOutput;

		public TestInput2(String input) {
			String[] inputArgs = split(input);
			this.expectedOutput = new TestOutput2(toInt(inputArgs[0]));
			String[] arrayStr = Arrays.copyOfRange(inputArgs, 1, inputArgs.length);
			this.a = toIntArray(arrayStr);
		}

		@Override
		public TestOutput getExpectedOutput() {
			return expectedOutput;
		}
	}

	private static class TestOutput2 implements TestOutput {
		private int max;

		public TestOutput2(int max) {
			this.max = max;
		}

		@Override
		public boolean equals(Object other) {
			if (other != null && (other instanceof TestOutput2)) {
				TestOutput2 otherOutput2 = (TestOutput2) other;
				return this.max == otherOutput2.max;
			}
			return false;
		}
		
		@Override
		public String toString() {
			return String.format("[%s]", max);
		}
	}

	@Override
	protected TestInput[] parseTestsInput() {
		String text = loadTextFile(TEST_FILE);
		String[] textLines = splitLines(text);
		TestInput[] result = new TestInput[textLines.length];
		for (int i = 0; i < textLines.length; i++) {
			result[i] = new TestInput2(textLines[i]);
		}
		return result;
	}

	private Problem2 problem;

	@Before
	public void setUp() throws Exception {
		problem = new Problem2();
	}

	@Override
	protected TestOutput getTestOutput(TestInput testInput) {
		TestInput2 testInput2 = (TestInput2) testInput;
		if (testInput2 != null) {
			return new TestOutput2(problem.calculate(testInput2.a));
		} else {
			throw new RuntimeException("Did not receive correct test input");
		}
	}
}
