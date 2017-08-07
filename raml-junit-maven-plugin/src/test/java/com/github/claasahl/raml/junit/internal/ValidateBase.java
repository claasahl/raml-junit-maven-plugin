package com.github.claasahl.raml.junit.internal;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.hamcrest.Matcher;

import com.github.claasahl.raml.junit.api.model.Parameter;
import com.github.claasahl.raml.junit.api.model.ParameterConstraints;

public class ValidateBase {

	protected final <T> void parametersMustBeUnique(Collection<T> parameters, Function<T, String> mapToName) {
		List<String> distinctNames = parameters.stream().map(mapToName).distinct().collect(Collectors.toList());
		assertThat(distinctNames, hasSize(parameters.size()));
	}

	protected final <T> void parametersMustNotBeNull(Collection<T> parameters) {
		assertThat(new ArrayList<>(parameters), everyItem(notNullValue()));
	}

	protected final void validateConstraints(TestCase testCase, Collection<Parameter> parameters,
			Collection<ParameterConstraints> constraints) {
		for (ParameterConstraints constraint : constraints) {
			Optional<Parameter> parameter = parameters.stream().filter(p -> constraint.getName().equals(p.getName()))
					.findFirst();
			validateParameter(testCase, parameter.orElse(null), constraint);
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
