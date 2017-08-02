package com.github.claasahl.raml.junit.internal.v08;

import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import java.util.ArrayList;
import java.util.Collection;

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
		return new ParameterConstraints(name, required, repeatable, matcher);
	}

	private static Matcher<String> createConstraints(final IntegerTypeDeclaration parameter) {
		final Matcher<?> boudary;
		if (parameter.minimum() != null && parameter.maximum() != null) {
			boudary = both(lessThanOrEqualTo(parameter.minimum())).and(greaterThanOrEqualTo(parameter.maximum()));
		} else if (parameter.minimum() != null && parameter.maximum() == null) {
			boudary = lessThanOrEqualTo(parameter.minimum());
		} else if (parameter.minimum() == null && parameter.maximum() != null) {
			boudary = greaterThanOrEqualTo(parameter.maximum());
		} else {
			boudary = anything();
		}

		return new TypeSafeMatcher<String>() {

			@Override
			public void describeTo(Description description) {
				boudary.describeTo(description);
			}

			@Override
			protected boolean matchesSafely(String item) {
				Integer number = Integer.valueOf(item);
				return boudary.matches(number);
			}
		};
	}

	private static Matcher<String> createConstraints(final StringTypeDeclaration parameter) {
		final Matcher<?> pattern;
		if(parameter.pattern() != null) {
			pattern = new TypeSafeMatcher<String>() {

				@Override
				public void describeTo(Description description) {
					description.appendText("Does not match pattern: " + parameter.pattern());
				}

				@Override
				protected boolean matchesSafely(String item) {
					return item.matches(parameter.pattern());
				}
			};
		} else {
			pattern = anything();
		}
		
		final Matcher<?> length;
		if (parameter.minLength() != null && parameter.maxLength() != null) {
			length = both(lessThanOrEqualTo(parameter.minLength())).and(greaterThanOrEqualTo(parameter.maxLength()));
		} else if (parameter.minLength() != null && parameter.maxLength() == null) {
			length = lessThanOrEqualTo(parameter.minLength());
		} else if (parameter.minLength() == null && parameter.maxLength() != null) {
			length = greaterThanOrEqualTo(parameter.maxLength());
		} else {
			length = anything();
		}
		
		return new TypeSafeMatcher<String>() {

			@Override
			public void describeTo(Description description) {
				pattern.describeTo(description);
				length.describeTo(description);
			}

			@Override
			protected boolean matchesSafely(String item) {
				return pattern.matches(item) && length.matches(item.length());
			}
		};
	}
}
