package com.github.claasahl.raml.junit.internal.v08;

import static com.github.claasahl.raml.junit.internal.matchers.Matchers.hasLength;
import static com.github.claasahl.raml.junit.internal.matchers.Matchers.*;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.raml.v2.api.model.v08.parameters.IntegerTypeDeclaration;
import org.raml.v2.api.model.v08.parameters.StringTypeDeclaration;

import com.github.claasahl.raml.junit.api.model.Parameter;
import com.github.claasahl.raml.junit.api.model.ParameterConstraints;

public final class ParameterConstraintsFactoryV08 {

	public static Collection<ParameterConstraints> createConstraints(Collection<org.raml.v2.api.model.v08.parameters.Parameter> parameters) {
		Collection<ParameterConstraints> constraints = new ArrayList<>();
		for (org.raml.v2.api.model.v08.parameters.Parameter parameter : parameters) {
			constraints.add(createConstraints(parameter));
		}
		return constraints;
	}

	private static ParameterConstraints createConstraints(org.raml.v2.api.model.v08.parameters.Parameter parameter) {
		List<Matcher<Parameter>> matchers = new ArrayList<>();
		if(Boolean.TRUE.equals(parameter.required())) {
			matchers.add(isRequired());
		}
		matchers.add(hasName(equalTo(parameter.name())));
		if(Boolean.TRUE.equals(parameter.repeat())) {
			matchers.add(hasValues(hasSize(greaterThanOrEqualTo(1))));
		}

		switch (parameter.type()) {
		case "integer":
			matchers.addAll(createMatchers((IntegerTypeDeclaration) parameter));
			break;
		case "string":
			matchers.addAll(createMatchers((StringTypeDeclaration) parameter));
			break;
		default:
			System.err.println(parameter.type());
		}
		
		return new ParameterConstraints(parameter.name(), matchers);
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
	
	private static List<Matcher<Parameter>> createMatchers(IntegerTypeDeclaration parameter) {
		List<Matcher<Parameter>> matchers = new ArrayList<>();
		matchers.add(everyValue(isInteger()));
		matchers.add(toDouble(lessThanOrEqualTo(2d)));
		if (parameter.minimum() != null) {
			matchers.add(toDouble(greaterThanOrEqualTo(parameter.minimum())));
		}
		everyItem(itemMatcher)
		if (parameter.maximum() != null) {
			matchers.add(toDouble(lessThanOrEqualTo(parameter.maximum())));
		}

		return matchers;
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
