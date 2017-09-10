package com.github.claasahl.raml.junit.internal.v08;

import static com.github.claasahl.raml.junit.internal.matchers.Matchers.everyValue;
import static com.github.claasahl.raml.junit.internal.matchers.Matchers.hasLength;
import static com.github.claasahl.raml.junit.internal.matchers.Matchers.hasName;
import static com.github.claasahl.raml.junit.internal.matchers.Matchers.hasValues;
import static com.github.claasahl.raml.junit.internal.matchers.Matchers.isInteger;
import static com.github.claasahl.raml.junit.internal.matchers.Matchers.isRequired;
import static com.github.claasahl.raml.junit.internal.matchers.Matchers.pattern;
import static com.github.claasahl.raml.junit.internal.matchers.Matchers.toDouble;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isIn;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hamcrest.Matcher;
import org.raml.v2.api.model.v08.parameters.IntegerTypeDeclaration;
import org.raml.v2.api.model.v08.parameters.StringTypeDeclaration;

import com.github.claasahl.raml.junit.api.model.Parameter;
import com.github.claasahl.raml.junit.api.model.ParameterConstraints;

public final class ParameterConstraintsFactoryImpl {

	public static Collection<ParameterConstraints> createConstraints(
			Collection<org.raml.v2.api.model.v08.parameters.Parameter> parameters) {
		Collection<ParameterConstraints> constraints = new ArrayList<>();
		for (org.raml.v2.api.model.v08.parameters.Parameter parameter : parameters) {
			constraints.add(createConstraints(parameter));
		}
		return constraints;
	}

	private static ParameterConstraints createConstraints(org.raml.v2.api.model.v08.parameters.Parameter parameter) {
		List<Matcher<Parameter>> matchers = new ArrayList<>();
		if (Boolean.TRUE.equals(parameter.required())) {
			matchers.add(isRequired());
		}
		matchers.add(hasName(equalTo(parameter.name())));
		if (Boolean.TRUE.equals(parameter.repeat())) {
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

	private static List<Matcher<Parameter>> createMatchers(IntegerTypeDeclaration parameter) {
		List<Matcher<Parameter>> matchers = new ArrayList<>();
		matchers.add(everyValue(isInteger()));
		if (parameter.minimum() != null) {
			matchers.add(everyValue(toDouble(greaterThanOrEqualTo(parameter.minimum()))));
		}
		if (parameter.maximum() != null) {
			matchers.add(everyValue(toDouble(lessThanOrEqualTo(parameter.maximum()))));
		}
		return matchers;
	}

	private static List<Matcher<Parameter>> createMatchers(StringTypeDeclaration parameter) {
		List<Matcher<Parameter>> matchers = new ArrayList<>();
		if (parameter.pattern() != null) {
			matchers.add(everyValue(pattern(parameter.pattern())));
		}
		if (parameter.enumValues() != null) {
			List<String> enums = new ArrayList<>(parameter.enumValues());
			matchers.add(everyValue(isIn(enums)));
		}
		if (parameter.minLength() != null) {
			matchers.add(everyValue(hasLength(greaterThanOrEqualTo(parameter.minLength()))));
		}
		if (parameter.maxLength() != null) {
			matchers.add(everyValue(hasLength(lessThanOrEqualTo(parameter.maxLength()))));
		}
		return matchers;
	}
}
