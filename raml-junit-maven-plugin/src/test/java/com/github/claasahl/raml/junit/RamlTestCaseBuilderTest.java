package com.github.claasahl.raml.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.raml.model.MimeType;

public class RamlTestCaseBuilderTest {

	private RamlTestCaseBuilder builder;

	@Before
	public void before() {
		this.builder = new RamlTestCaseBuilder();
	}

	@Test
	public void shouldBuildEmptyTestCase() {
		RamlTestCase testCase = this.builder.build();
		assertEquals(new RamlTestCase(), testCase);
	}

	@Test
	public void shouldBuildIdenticalTestCases() {
		RamlTestCase testCaseA = this.builder.build();
		RamlTestCase testCaseB = this.builder.build();
		assertEquals(testCaseA, testCaseB);
	}

	@Test
	public void shouldBuildFreshTestCase() {
		RamlTestCase testCaseA = this.builder.build();
		MimeType mimeType = new MimeType("application/json");
		this.builder.setMimeType(mimeType);
		RamlTestCase testCaseB = this.builder.build();

		assertNull(testCaseA.getMimeType());
		assertEquals(mimeType, testCaseB.getMimeType());
	}

}
