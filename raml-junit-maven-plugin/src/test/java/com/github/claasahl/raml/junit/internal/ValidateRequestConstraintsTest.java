package com.github.claasahl.raml.junit.api;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.github.claasahl.raml.junit.api.model.ParameterConstraints;
import com.github.claasahl.raml.junit.internal.TestCase;
import com.github.claasahl.raml.junit.internal.Utils;

@RunWith(Parameterized.class)
public class ValidateRequestConstraintsTest {
	private final TestCase testCase;

	public ValidateRequestConstraintsTest(TestCase testCase) {
		this.testCase = testCase;
	}

	@Parameters
	public static Collection<Object[]> data() {
		return Utils.getTestCases().map(t -> new Object[] { t }).collect(Collectors.toList());
	}

	@Test
	public void queryParametersMustBeUnique() {
		Collection<ParameterConstraints> parameters = this.testCase.getRequestConstraints().getRequestQueryParameters();
		List<String> distinctNames = parameters.stream().map(ParameterConstraints::getName).distinct()
				.collect(Collectors.toList());
		assertThat(distinctNames, hasSize(parameters.size()));
	}

	@Test
	public void formParametersMustBeUnique() {
		Collection<ParameterConstraints> parameters = this.testCase.getRequestConstraints().getRequestFormParameters();
		List<String> distinctNames = parameters.stream().map(ParameterConstraints::getName).distinct()
				.collect(Collectors.toList());
		assertThat(distinctNames, hasSize(parameters.size()));
	}

	@Test
	public void pathParametersMustBeUnique() {
		Collection<ParameterConstraints> parameters = this.testCase.getRequestConstraints().getRequestPathParameters();
		List<String> distinctNames = parameters.stream().map(ParameterConstraints::getName).distinct()
				.collect(Collectors.toList());
		assertThat(distinctNames, hasSize(parameters.size()));
	}

	@Test
	public void headersMustBeUnique() {
		Collection<ParameterConstraints> parameters = this.testCase.getRequestConstraints().getRequestHeaders();
		List<String> distinctNames = parameters.stream().map(ParameterConstraints::getName).distinct()
				.collect(Collectors.toList());
		assertThat(distinctNames, hasSize(parameters.size()));
	}

	@Test
	public void cookiesMustBeUnique() {
		Collection<ParameterConstraints> parameters = this.testCase.getRequestConstraints().getRequestCookies();
		List<String> distinctNames = parameters.stream().map(ParameterConstraints::getName).distinct()
				.collect(Collectors.toList());
		assertThat(distinctNames, hasSize(parameters.size()));
	}

	@Test
	public void queryParametersMustNotBeNull() {
		Collection<ParameterConstraints> parameters = this.testCase.getRequestConstraints().getRequestQueryParameters();
		assertThat(new ArrayList<>(parameters), everyItem(notNullValue()));
	}

	@Test
	public void formParametersMustNotBeNull() {
		Collection<ParameterConstraints> parameters = this.testCase.getRequestConstraints().getRequestFormParameters();
		assertThat(new ArrayList<>(parameters), everyItem(notNullValue()));
	}

	@Test
	public void pathParametersMustNotBeNull() {
		Collection<ParameterConstraints> parameters = this.testCase.getRequestConstraints().getRequestPathParameters();
		assertThat(new ArrayList<>(parameters), everyItem(notNullValue()));
	}

	@Test
	public void headersMustNotBeNull() {
		Collection<ParameterConstraints> parameters = this.testCase.getRequestConstraints().getRequestHeaders();
		assertThat(new ArrayList<>(parameters), everyItem(notNullValue()));
	}

	@Test
	public void cookiesMustNotBeNull() {
		Collection<ParameterConstraints> parameters = this.testCase.getRequestConstraints().getRequestCookies();
		assertThat(new ArrayList<>(parameters), everyItem(notNullValue()));
	}

}
