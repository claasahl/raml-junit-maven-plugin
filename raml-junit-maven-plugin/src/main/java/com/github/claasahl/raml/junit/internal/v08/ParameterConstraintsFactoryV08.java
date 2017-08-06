package com.github.claasahl.raml.junit.internal.v08;

import static com.github.claasahl.raml.junit.internal.matchers.Matchers.hasLength;
import static com.github.claasahl.raml.junit.internal.matchers.Matchers.toDouble;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.raml.v2.api.model.v08.parameters.IntegerTypeDeclaration;
import org.raml.v2.api.model.v08.parameters.Parameter;
import org.raml.v2.api.model.v08.parameters.StringTypeDeclaration;

import com.github.claasahl.raml.junit.api.model.ParameterConstraints;

public final class ParameterConstraintsFactoryV08 {

	public static Collection<ParameterConstraints> createConstraints(Collection<Parameter> parameters) {
		Collection<ParameterConstraints> constraints = new ArrayList<>();
		for (Parameter parameter : parameters) {
			constraints.add(createConstraints(parameter));
		}
		return constraints;
	}

	private static ParameterConstraints createConstraints(Parameter parameter) {
		String name = parameter.name();
		boolean required = Boolean.TRUE.equals(parameter.required());
		boolean repeatable = Boolean.TRUE.equals(parameter.repeat());
		Matcher<String> matcher = null;

		switch (parameter.type()) {
		case "integer":
			matcher = createConstraints((IntegerTypeDeclaration) parameter);
			break;
		case "string":
			matcher = createConstraints((StringTypeDeclaration) parameter);
			break;
		default:
			System.err.println(parameter.type());
			return null;
		}
		return new ParameterConstraints(name, required, 1, repeatable ? Integer.MAX_VALUE : 1, matcher);
	}

	private static Matcher<String> createConstraints(final IntegerTypeDeclaration parameter) {
		List<Matcher<? super String>> matchers = new ArrayList<>();
		matchers.add(toDouble(lessThanOrEqualTo(2d)));
		if (parameter.minimum() != null) {
			matchers.add(toDouble(greaterThanOrEqualTo(parameter.minimum())));
		}
		if (parameter.maximum() != null) {
			matchers.add(toDouble(lessThanOrEqualTo(parameter.maximum())));
		}

		return allOf(matchers);
	}

	private static Matcher<String> createConstraints(final StringTypeDeclaration parameter) {
		List<Matcher<? super String>> matchers = new ArrayList<>();
		if (parameter.pattern() != null) {
			matchers.add(new TypeSafeMatcher<String>() {

				@Override
				public void describeTo(Description description) {
					description.appendText("Does not match pattern: " + parameter.pattern());
				}

				@Override
				protected boolean matchesSafely(String item) {
					return item.matches(parameter.pattern());
				}
			});
		}
		matchers.add(hasLength(lessThanOrEqualTo(2)));
		if (parameter.minLength() != null) {
			matchers.add(hasLength(greaterThanOrEqualTo(parameter.minLength())));
		}
		if (parameter.maxLength() != null) {
			matchers.add(hasLength(lessThanOrEqualTo(parameter.maxLength())));
		}
		return allOf(matchers);
	}
}
