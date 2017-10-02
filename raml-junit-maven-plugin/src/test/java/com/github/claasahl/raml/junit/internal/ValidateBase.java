package com.github.claasahl.raml.junit.internal;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.hamcrest.Matcher;

import com.github.claasahl.raml.junit.api.common.Body;
import com.github.claasahl.raml.junit.api.common.BodyConstraints;
import com.github.claasahl.raml.junit.api.common.Parameter;
import com.github.claasahl.raml.junit.api.common.ParameterConstraints;

public class ValidateBase {

	protected final <T> void parametersMustBeUnique(Collection<T> parameters, Function<T, String> mapToName) {
		List<String> distinctNames = parameters.stream().map(mapToName).distinct().collect(Collectors.toList());
		assertThat(distinctNames, hasSize(parameters.size()));
	}

	protected final <T> void parametersMustNotBeNull(Collection<T> parameters) {
		assertThat(new ArrayList<>(parameters), everyItem(notNullValue()));
	}
	
	protected final void validateConstraints(TestCase testCase) {
		String reason = String.format("Response code '%s' for %s failed validation.",
				testCase.getResponse().getResponseCode(), testCase.getKey());
		assertThat(reason, testCase.getResponse().getResponseCode(),
				equalTo(testCase.getResponseConstraints().getResponseCode()));
	}

	protected final void validateConstraints(TestCase testCase, Collection<Parameter> parameters,
			Collection<ParameterConstraints> constraints) {
		for (ParameterConstraints constraint : constraints) {
			Optional<Parameter> parameter = parameters.stream().filter(p -> constraint.getName().equals(p.getName()))
					.findFirst();
			validateParameter(testCase, parameter.orElse(null), constraint);
		}
	}

	protected final void validateConstraints(TestCase testCase, Body body, BodyConstraints constraints) {
		if (constraints == null) {
			String reason = String.format("Body for %s failed validation.", testCase.getKey());
			assertThat(reason, body, nullValue());
			return;
		}

		String reason = String.format("Body of type '%s' for %s failed validation.", constraints.getContentType(),
				testCase.getKey());
		for (Matcher<Body> matcher : constraints.getMatchers()) {
			assertThat(reason, body, matcher);
		}
	}

	private final void validateParameter(TestCase testCase, Parameter parameter, ParameterConstraints constraints) {
		String reason = String.format("Parameter '%s' for %s failed validation.", constraints.getName(),
				testCase.getKey());
		for (Matcher<Parameter> matcher : constraints.getMatchers()) {
			assertThat(reason, parameter, matcher);
		}
	}

}
