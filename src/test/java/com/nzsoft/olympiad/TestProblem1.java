package com.nzsoft.olympiad;

import org.junit.Before;
import static com.nzsoft.olympiad.core.TestUtils.*;

import com.nzsoft.olympiad.core.TestInput;
import com.nzsoft.olympiad.core.TestOutput;
import com.nzsoft.olympiad.core.TestRunner;

public class TestProblem1 extends TestRunner {

	private static final String TEST_FILE = "Problem1.txt";

	private static class TestInput1 implements TestInput {
		private int a;
		private int b;
		private TestOutput1 expectedOutput;

		public TestInput1(String input) {
			String[] inputArgs = split(input);
			this.a = toInt(inputArgs[0]);
			this.b = toInt(inputArgs[1]);
			this.expectedOutput = new TestOutput1(toInt(inputArgs[2]));
		}

		@Override
		public TestOutput getExpectedOutput() {
			return expectedOutput;
		}
	}

	private static class TestOutput1 implements TestOutput {
		private int sum;

		public TestOutput1(int sum) {
			this.sum = sum;
		}

		@Override
		public boolean equals(Object other) {
			if (other != null && (other instanceof TestOutput1)) {
				TestOutput1 otherOutput1 = (TestOutput1) other;
				return this.sum == otherOutput1.sum;
			}
			return false;
		}
		
		@Override
		public String toString() {
			return String.format("[%s]", sum);
		}
	}

	@Override
	protected TestInput[] parseTestsInput() {
		String text = loadTextFile(TEST_FILE);
		String[] textLines = splitLines(text);
		TestInput[] result = new TestInput[textLines.length];
		for (int i = 0; i < textLines.length; i++) {
			result[i] = new TestInput1(textLines[i]);
		}
		return result;
	}

	private Problem1 problem;

	@Before
	public void setUp() throws Exception {
		problem = new Problem1();
	}

	@Override
	protected TestOutput getTestOutput(TestInput testInput) {
		TestInput1 testInput1 = (TestInput1)testInput;
		if (testInput1 != null) {
			return new TestOutput1(problem.calculate(testInput1.a, testInput1.b));
		} else {
			throw new RuntimeException("Did not receive correct test input");
		}
	}
}
