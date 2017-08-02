package com.github.claasahl.raml.junit.api.model;

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
	private final boolean required;
	private final boolean repeatable;
	private final Matcher<String> matcher;

	/**
	 * Creates a new constraint parameter.
	 * 
	 * @param name
	 *            the parameter's name
	 * @param required
	 *            whether the parameter is required
	 * @param repeatable
	 *            whether the parameter may be repeated
	 * @param matcher
	 *            the parameter's constraints
	 */
	public ParameterConstraints(String name, boolean required, boolean repeatable, Matcher<String> matcher) {
		this.name = name;
		this.required = required;
		this.repeatable = repeatable;
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
	 * Returns <code>true</code> if the parameter is required.
	 * 
	 * @return <code>true</code> if the parameter is required, otherwise
	 *         <code>false</code>
	 */
	public boolean isRequired() {
		return required;
	}

	/**
	 * Returns <code>true</code> if the parameter may be repeated.
	 * 
	 * @return <code>true</code> if the parameter may be repeated, otherwise
	 *         <code>false</code>
	 */
	public boolean isRepeatable() {
		return repeatable;
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
