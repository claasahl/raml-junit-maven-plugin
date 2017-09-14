package com.github.claasahl.raml.junit.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

/**
 * The class {@link Parameter}.
 * 
 * @author Claas Ahlrichs
 *
 */
public class Parameter {
	private final String name;
	private final List<String> values;

	/**
	 * Creates a new parameter.
	 * 
	 * @param name
	 *            the parameter's name
	 * @param values
	 *            the parameter's value(s)
	 */
	public Parameter(String name, String... values) {
		this.name = name;
		if (values == null)
			values = new String[0];
		this.values = Collections.unmodifiableList(Arrays.asList(values));
	}

	/**
	 * Creates a new parameter.
	 * 
	 * @param name
	 *            the parameter's name
	 * @param values
	 *            the parameter's value(s)
	 */
	public Parameter(String name, List<String> values) {
		this.name = name;
		if (values == null)
			values = new ArrayList<>();
		this.values = Collections.unmodifiableList(new ArrayList<>(values));
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
	 * Returns the parameter's values. The returned list is immutable.
	 * Typically, the list contains a single item (i.e. a direct mapping between
	 * the parameter's name and it's value). The cases where the parameter does
	 * not have a value or where the parameter has several values are covered as
	 * well.
	 * <p/>
	 * <b>Examples:</b>
	 * <ul>
	 * <li>list contains 0 items: the parameter has no value (e.g.
	 * <i>?parameter</i>)</li>
	 * <li>list contains 1 item: the parameter has a single value (e.g.
	 * <i>?parameter=value</i>)</li>
	 * <li>list contains several items: the parameter has a multiple values
	 * (e.g. <i>?parameter=value1&amp;parameter=value2</i>)</li>
	 * </ul>
	 * 
	 * @return the parameter's values
	 */
	@Nonnull
	public List<String> getValues() {
		return values;
	}

}