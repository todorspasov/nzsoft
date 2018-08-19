package com.nzsoft.olympiad;

import java.util.Arrays;

import org.junit.Before;

import com.nzsoft.olympiad.core.TestInput;
import com.nzsoft.olympiad.core.TestOutput;
import com.nzsoft.olympiad.core.TestRunner;
import static com.nzsoft.olympiad.core.TestUtils.*;

public class TestProblem3 extends TestRunner {

	private static final String TEST_FILE = "Problem3.txt";

	private static class TestInput3 implements TestInput {
		private int[] a;
		private TestOutput3 expectedOutput;

		public TestInput3(String input) {
			String[] inputArgs = split(input);
			int arrayLength = inputArgs.length / 2;
			this.a = toIntArray(Arrays.copyOfRange(inputArgs, 0, arrayLength));
			this.expectedOutput = new TestOutput3(toIntArray(Arrays.copyOfRange(inputArgs, arrayLength, arrayLength * 2)));
		}

		@Override
		public TestOutput getExpectedOutput() {
			return expectedOutput;
		}
	}

	private static class TestOutput3 implements TestOutput {
		private int[] sorted;

		public TestOutput3(int[] sorted) {
			this.sorted = sorted;
		}

		@Override
		public boolean equals(Object other) {
			if (other != null && (other instanceof TestOutput3)) {
				TestOutput3 otherOutput3 = (TestOutput3) other;
				return Arrays.equals(sorted, otherOutput3.sorted);
			}
			return false;
		}
		
		@Override
		public String toString() {
			return String.format("[%s]", Arrays.toString(sorted));
		}
	}

	@Override
	protected TestInput[] parseTestsInput() {
		String text = loadTextFile(TEST_FILE);
		String[] textLines = splitLines(text);
		TestInput[] result = new TestInput[textLines.length];
		for (int i = 0; i < textLines.length; i++) {
			result[i] = new TestInput3(textLines[i]);
		}
		return result;
	}

	private Problem3 problem;

	@Before
	public void setUp() throws Exception {
		problem = new Problem3();
	}

	@Override
	protected TestOutput getTestOutput(TestInput testInput) {
		TestInput3 testInput3 = (TestInput3) testInput;
		if (testInput3 != null) {
			return new TestOutput3(problem.calculate(testInput3.a));
		} else {
			throw new RuntimeException("Did not receive correct test input");
		}
	}
}
