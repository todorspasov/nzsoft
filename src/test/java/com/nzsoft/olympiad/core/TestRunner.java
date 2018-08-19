package com.nzsoft.olympiad.core;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public abstract class TestRunner {

	protected abstract TestInput[] parseTestsInput();

	protected abstract TestOutput getTestOutput(TestInput testInput);

	private void runTest(int index) {
		TestInput[] testInputs = parseTestsInput();
		TestInput testInput = testInputs[index];
		TestOutput actualOutput = getTestOutput(testInput);
		assertEquals(testInput.getExpectedOutput(), actualOutput);
	}

	protected String loadTextFile(String fileName) {
		String relativeFileName = String.format("/%s", fileName);
		try {
			return IOUtils.toString(this.getClass().getResourceAsStream(relativeFileName), "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException("Cannot open test input file: " + relativeFileName);
		}
	}

	@Test
	public void test0() {
		runTest(0);
	}

	@Test
	public void test1() {
		runTest(1);
	}

	@Test
	public void test2() {
		runTest(2);
	}

	@Test
	public void test3() {
		runTest(3);
	}

	@Test
	public void test4() {
		runTest(4);
	}

	@Test
	public void test5() {
		runTest(5);
	}

	@Test
	public void test6() {
		runTest(6);
	}

	@Test
	public void test7() {
		runTest(7);
	}

	@Test
	public void test8() {
		runTest(8);
	}

	@Test
	public void test9() {
		runTest(9);
	}
}
