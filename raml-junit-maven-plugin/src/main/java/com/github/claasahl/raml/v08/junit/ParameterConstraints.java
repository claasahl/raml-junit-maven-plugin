package com.github.claasahl.raml.v08.junit;

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
	private final Matcher<String> matcher;

	/**
	 * Creates a constraint parameter.
	 * 
	 * @param name
	 *            the parameter's name
	 * @param matcher
	 *            the parameter's constraints
	 */
	public ParameterConstraints(String name, Matcher<String> matcher) {
		this.name = name;
		this.matcher = matcher;
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
	 * Returns the Hamcrest matcher for validating the parameter's value(s). The
	 * returned matcher is expected to handle all aspects of validation (e.g.
	 * whether the parameter is required or optional, whether minimum and
	 * maximum boundaries are adhered to, whether the parameter accepts empty
	 * values and so forth).
	 * 
	 * @return the Hamcrest matcher for validating the parameter's value(s)
	 */
	public Matcher<String> getMatcher() {
		return matcher;
	}

}
