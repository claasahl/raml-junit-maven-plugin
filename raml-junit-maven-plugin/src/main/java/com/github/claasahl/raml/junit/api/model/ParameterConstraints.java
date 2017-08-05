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
	private final int minValues;
	private final int maxValues;
	private final Matcher<String> matcher;

	/**
	 * Creates a new constraint parameter.
	 * 
	 * @param name
	 *            the parameter's name
	 * @param required
	 *            whether the parameter is required
	 * @param minValues
	 *            the minimum number of values for the parameter
	 * @param maxValues
	 *            the maximum number of values for the parameter
	 * @param matcher
	 *            the parameter's constraints
	 */
	public ParameterConstraints(String name, boolean required, int minValues, int maxValues, Matcher<String> matcher) {
		this.name = name;
		this.required = required;
		this.minValues = minValues;
		this.maxValues = maxValues;
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
	 * Returns the minimum number of values for the parameter. If no values were
	 * required, then 0 (zero) is returned. If at least 1 (or 5) values were
	 * required, then 1 (or 5) is returned. The returned value may not be
	 * negative.
	 * 
	 * @return the minimum number of values for the parameter
	 */
	public int getMinValues() {
		return minValues;
	}

	/**
	 * Returns the maximum number of values for the parameter. If no values were
	 * allowed, then 0 (zero) is returned. If at most 1 (or 5) values were
	 * allowed, then 1 (or 5) is returned. If there were no upper limited, then
	 * {@link Integer#MAX_VALUE} is returned.
	 * 
	 * @return the maximum number of values for the parameter
	 */
	public int getMaxValues() {
		return maxValues;
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
