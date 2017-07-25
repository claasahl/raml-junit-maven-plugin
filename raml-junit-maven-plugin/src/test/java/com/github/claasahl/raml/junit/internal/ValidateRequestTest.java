package com.github.claasahl.raml.junit.internal;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
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

import com.github.claasahl.raml.junit.api.model.Parameter;
import com.github.claasahl.raml.junit.internal.TestCase;
import com.github.claasahl.raml.junit.internal.Utils;

@RunWith(Parameterized.class)
public class ValidateRequestTest {
	private final TestCase testCase;

	public ValidateRequestTest(TestCase testCase) {
		this.testCase = testCase;
	}

	@Parameters
	public static Collection<Object[]> data() {
		return Utils.getTestCases().map(t -> new Object[] { t }).collect(Collectors.toList());
	}

	@Test
	public void queryParametersMustBeUnique() {
		Collection<Parameter> parameters = this.testCase.getRequest().getRequestQueryParameters();
		List<String> distinctNames = parameters.stream().map(Parameter::getName).distinct()
				.collect(Collectors.toList());
		assertThat(distinctNames, hasSize(parameters.size()));
	}

	@Test
	public void formParametersMustBeUnique() {
		Collection<Parameter> parameters = this.testCase.getRequest().getRequestFormParameters();
		List<String> distinctNames = parameters.stream().map(Parameter::getName).distinct()
				.collect(Collectors.toList());
		assertThat(distinctNames, hasSize(parameters.size()));
	}

	@Test
	public void pathParametersMustBeUnique() {
		Collection<Parameter> parameters = this.testCase.getRequest().getRequestPathParameters();
		List<String> distinctNames = parameters.stream().map(Parameter::getName).distinct()
				.collect(Collectors.toList());
		assertThat(distinctNames, hasSize(parameters.size()));
	}

	@Test
	public void headersMustBeUnique() {
		Collection<Parameter> parameters = this.testCase.getRequest().getRequestHeaders();
		List<String> distinctNames = parameters.stream().map(Parameter::getName).distinct()
				.collect(Collectors.toList());
		assertThat(distinctNames, hasSize(parameters.size()));
	}

	@Test
	public void cookiesMustBeUnique() {
		Collection<Parameter> parameters = this.testCase.getRequest().getRequestCookies();
		List<String> distinctNames = parameters.stream().map(Parameter::getName).distinct()
				.collect(Collectors.toList());
		assertThat(distinctNames, hasSize(parameters.size()));
	}

	@Test
	public void queryParametersMustNotBeNull() {
		Collection<Parameter> parameters = this.testCase.getRequest().getRequestQueryParameters();
		assertThat(new ArrayList<>(parameters), everyItem(notNullValue()));
	}

	@Test
	public void formParametersMustNotBeNull() {
		Collection<Parameter> parameters = this.testCase.getRequest().getRequestFormParameters();
		assertThat(new ArrayList<>(parameters), everyItem(notNullValue()));
	}

	@Test
	public void pathParametersMustNotBeNull() {
		Collection<Parameter> parameters = this.testCase.getRequest().getRequestPathParameters();
		assertThat(new ArrayList<>(parameters), everyItem(notNullValue()));
	}

	@Test
	public void headersMustNotBeNull() {
		Collection<Parameter> parameters = this.testCase.getRequest().getRequestHeaders();
		assertThat(new ArrayList<>(parameters), everyItem(notNullValue()));
	}

	@Test
	public void cookiesMustNotBeNull() {
		Collection<Parameter> parameters = this.testCase.getRequest().getRequestCookies();
		assertThat(new ArrayList<>(parameters), everyItem(notNullValue()));
	}

	@Test
	public void pathParametersMustNotHaveMultipleValues() {
		Collection<Parameter> parameters = this.testCase.getRequest().getRequestPathParameters();
		List<Integer> values = parameters.stream().map(p -> p.getValues().size()).collect(Collectors.toList());
		assertThat(values, everyItem(lessThanOrEqualTo(1)));
	}

}
