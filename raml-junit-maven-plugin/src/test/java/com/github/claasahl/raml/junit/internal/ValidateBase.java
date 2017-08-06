package com.github.claasahl.raml.junit.internal;

import static com.github.claasahl.raml.junit.internal.matchers.Matchers.hasValues;
import static com.github.claasahl.raml.junit.internal.matchers.Matchers.isRequired;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

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

		// required vs. optional parameters
		if (constraints.isRequired()) {
			assertThat(reason, parameter, isRequired());
		} else if (parameter == null) {
			return;
		}

		// singular vs. repeatable parameters
		assertThat(reason, parameter, hasValues(hasSize(both(greaterThanOrEqualTo(constraints.getMinValues()))
				.and(lessThanOrEqualTo(constraints.getMaxValues())))));

		// additional constraints
		assertThat(reason, parameter, hasValues(everyItem(constraints.getMatcher())));
	}

}
