package com.github.claasahl.raml.junit;

import static org.junit.Assert.*;

import org.junit.Test;


public class RamlTestCaseTest {
	
	@Test
	public void implementationOfEqualsShouldHandleNull() {
		RamlTestCase testCase = new RamlTestCase();
		assertFalse(testCase.equals(null));
	}

	@Test
	public void implementationOfEqualsShouldBeReflexive() {
		RamlTestCase testCase = new RamlTestCase();
		assertTrue(testCase.equals(testCase));
	}

	@Test
	public void implementationOfEqualsShouldBeSymmetric() {
		RamlTestCase testCaseA = new RamlTestCase();
		RamlTestCase testCaseB = new RamlTestCase();
		assertTrue(testCaseA.equals(testCaseB));
		assertTrue(testCaseB.equals(testCaseA));
	}

	@Test
	public void implementationOfEqualsShouldBeTransitive() {
		RamlTestCase testCaseA = new RamlTestCase();
		RamlTestCase testCaseB = new RamlTestCase();
		RamlTestCase testCaseC = new RamlTestCase();
		assertTrue(testCaseA.equals(testCaseB));
		assertTrue(testCaseB.equals(testCaseC));
		assertTrue(testCaseA.equals(testCaseC));
	}
	
	@Test
	public void implementationOfHashCodeShouldBeReproducible() {
		RamlTestCase testCase = new RamlTestCase();
		int hashCode1 = testCase.hashCode();
		int hashCode2 = testCase.hashCode();
		assertEquals(hashCode1, hashCode2);
	}
	
	@Test
	public void implementationOfHashCodeShouldBeConsistent() {
		RamlTestCase testCaseA = new RamlTestCase();
		RamlTestCase testCaseB = new RamlTestCase();
		assertEquals(testCaseA.hashCode(), testCaseB.hashCode());
	}

}
