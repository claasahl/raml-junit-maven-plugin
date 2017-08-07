package com.github.claasahl.raml.junit.api.model;

import java.util.List;

import javax.annotation.Nonnull;

import org.hamcrest.Matcher;

/**
 * The class {@link ParameterConstraints}.
 * 
 * @author Claas Ahlrichs
 *
 */
public class ParameterConstraints {

	private final String name;
	private final List<Matcher<Parameter>> matchers;

	/**
	 * Creates a new constraint parameter.
	 * 
	 * @param name
	 *            the parameter's name
	 * @param matchers
	 *            the parameter's constraints
	 */
	public ParameterConstraints(String name, List<Matcher<Parameter>> matchers) {
		this.name = name;
		this.matchers = matchers;
	}

	/**
	 * Returns the parameter's name.
	 * 
	 * @return the parameter's name
	 */
	@Nonnull
	public String getName() {
		return name;
	}

	/**
	 * Returns the list of Hamcrest matcher for validating the parameter's
	 * value(s). Each matcher is evaluated individually. Together, the returned
	 * matchers are expected expected to handle all aspects of validation (e.g.
	 * whether the parameter is required or optional, whether minimum and
	 * maximum boundaries are adhered to, whether the parameter accepts empty
	 * values and so forth).
	 * 
	 * @return the list of Hamcrest matcher for validating the parameter's
	 *         value(s)
	 */
	public List<Matcher<Parameter>> getMatchers() {
		return matchers;
	}

}
